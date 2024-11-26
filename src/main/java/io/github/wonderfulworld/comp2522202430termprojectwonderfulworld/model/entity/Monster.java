package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.sprite.ASprite;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.IDamageable;
import javafx.geometry.Rectangle2D;
import java.util.List;
import java.util.Objects;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import lombok.Getter;

/**
 * The Monster class represents a damageable entity that can engage
 * in combat and pursue targets in the game world.
 * It defines the behavior for attacking, receiving damage, and switching between random movement
 * and targeted pursuit of a player.
 *
 * @author Melissa Shao
 * @version 2024
 */
public class Monster extends ASprite implements IDamageable  {
    private static final int MOVEMENT_DIRECTION_TOP = 1;
    private static final int MOVEMENT_DIRECTION_RIGHT = 2;
    private static final int MOVEMENT_DIRECTION_BOTTOM = 3;
    private static final int MOVEMENT_DIRECTION_LEFT = 4;
    private static final double RANDOM_WAITING_TIME_MAX = 3.0;
    private static final double MINIMUM_DISTANCE = 10.0;
    private static final double RESET_WAITING_TIME = 3.0;
    private static final double RESET_MOVING_TIME = 2.0;

    /**
     * The target player that the monster is currently pursuing.
     */
    private Player aim;

    /**
     * Monster attributes.
     */
    @Getter
    private final String name;
    @Getter private final double damage;
    @Getter private final double damageRadius;
    @Getter private final double viewingRadius;
    @Getter private double health;
    private final double speed; // Movement speed in pixels per second
    private final double attackSpeed; // Attack cooldown in milliseconds

    /**
     * Timers for movement and combat mode.
     */
    private double movingTimer = 2; // Time to move in one direction in seconds
    private double waitingTimer; // Time to wait before moving in seconds

    /**
     * Directional attributes for random movement.
     */
    private int currentDirectionIndex = 0;
    private final Integer[] directionSequence = {1, 2, 3, 4};

    /**
     * Combat-related flags.
     */
    private boolean inCombat = false;
    private boolean isDead;
    private double lastAttack;

    /**
     * Constructs a Monster with specified attributes.
     *
     * @param name          the name of the monster
     * @param health        the health points of the monster
     * @param damage        the damage points of the monster
     * @param damageRadius  the radius within which the monster can deal damage
     * @param viewingRadius the viewing radius of the monster
     * @param speed         the movement speed of the monster
     * @param attackSpeed   the cooldown time between attacks
     */
    public Monster(final String name, final double health, final double damage,
                   final double damageRadius, final double viewingRadius,
                   final double speed, final double attackSpeed) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.damageRadius = damageRadius;
        this.viewingRadius = viewingRadius;
        this.speed = speed;
        this.attackSpeed = attackSpeed;

        // Initialize a random direction sequence
        List<Integer> intList = Arrays.asList(directionSequence);
        Collections.shuffle(intList);
        intList.toArray(directionSequence);

        // Set a random initial waiting time
        Random random = new Random();
        waitingTimer = random.nextDouble() * RANDOM_WAITING_TIME_MAX;
    }

    /**
     * Attacks the specified target if the cooldown period has passed.
     *
     * @param target the target player to attack
     */
    public void attack(final IDamageable target) {
        if ((System.currentTimeMillis() - lastAttack) < attackSpeed) {
            return;
        }

        lastAttack = System.currentTimeMillis(); // Reset the cooldown timer

        if (!target.isDead()) {
            ((Player) target).inAttack(this);
        }
    }

    /**
     * Receives an attack from the player and reduces health accordingly.
     *
     * @param player the player attacking this monster
     */
    public void inAttack(final Player player) {
        double incomingDamage = player.getDamage();
        health = Math.max(health - incomingDamage, 0);

        if (health == 0) {
            isDead = true;
        }

        // Counterattack
        attack(player);
    }

    /**
     * Checks if the monster is dead.
     *
     * @return true if health is zero, false otherwise
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Checks if the target is within the monster's view radius.
     *
     * @param s the target to check
     * @return true if the target is within the viewing radius, false otherwise
     */
    public boolean intersectsRadiusViewBox(final ASprite s) {
        return s.getCollisionBox().intersects(this.getRadiusViewCollisionBox());
    }

    /**
     * Checks if the target is within the monster's attack radius.
     *
     * @param s the target to check
     * @return true if the target is within the damage radius, false otherwise
     */
    public boolean intersectsDamageBox(final ASprite s) {
        return s.getCollisionBox().intersects(this.getDamageRadiusBox());
    }

    /**
     * Gets damage radius box.
     *
     * @return The damage radius box.
     */
    public Rectangle2D getDamageRadiusBox() {

        return new Rectangle2D(
                positionX - damageRadius,
                positionY - damageRadius,
                width + (2 * damageRadius),
                height + (2 * damageRadius));
    }

    /**
     * Gets radius view collision box.
     *
     * @return The radius view collision box.
     */
    public Rectangle2D getRadiusViewCollisionBox() {

        return new Rectangle2D(
                positionX - viewingRadius,
                positionY - viewingRadius,
                width + (2 * viewingRadius),
                height + (2 * viewingRadius));
    }

    /**
     * Sets a target player for the monster to pursue.
     *
     * @param player the target player to pursue
     */
    public void setAim(final Player player) {
        if (aim == null) {
            aim = player;
        }
        inCombat = true;
    }

    /**
     * Moves toward the target if in combat, and attacks if within range.
     *
     * @param delta the time delta for movement calculations
     */
    private void moveToAim(final double delta) {
        int path = (int) (speed * delta);

        if (intersectsDamageBox(aim)) {
            attack(aim);
            return;
        }

        double aimX = aim.getPositionX();
        double aimY = aim.getPositionY();

        if (Math.abs(aimX - positionX) > MINIMUM_DISTANCE) {
            if (aim.getPositionX() > positionX) {
                positionX += path;
            } else {
                positionX -= path;
            }
        }

        if (Math.abs(aimY - positionY) > MINIMUM_DISTANCE) {
            if (aim.getPositionY() > positionY) {
                positionY += path;
            } else {
                positionY -= path;
            }
        }
    }

    /**
     * Executes random movement when not in pursuit of a target.
     *
     * @param delta the time delta for movement calculations
     * @throws IllegalStateException if an unexpected direction number is encountered
     */
    private void randomMoving(final double delta) {
        waitingTimer -= delta;
        if (waitingTimer < 0) {
            movingTimer -= delta;
            if (movingTimer > 0) {
                int directionNumber = directionSequence[currentDirectionIndex];
                switch (directionNumber) {
                    case MOVEMENT_DIRECTION_TOP -> positionX += (int) (speed * delta);
                    case MOVEMENT_DIRECTION_RIGHT -> positionX -= (int) (speed * delta);
                    case MOVEMENT_DIRECTION_BOTTOM -> positionY += (int) (speed * delta);
                    case MOVEMENT_DIRECTION_LEFT -> positionY -= (int) (speed * delta);
                    default -> throw new IllegalStateException("Unexpected direction: "
                            + directionNumber);
                }
            } else {
                // Reset timers and change direction
                waitingTimer = RESET_WAITING_TIME;
                movingTimer = RESET_MOVING_TIME;
                currentDirectionIndex++;
                if (currentDirectionIndex > directionSequence.length - 1) {
                    currentDirectionIndex = 0;
                }
            }
        }
    }

    /**
     * Clears the target and exits combat mode.
     */
    public void offCombat() {
        inCombat = false;
    }

    /**
     * Updates the monster's movement and behavior, selecting between
     * pursuing the target or random movement.
     *
     * @param delta the time delta for movement calculations
     */
    public void update(final double delta) {
        if (!inCombat) {
            randomMoving(delta);
        } else {
            moveToAim(delta);
        }
    }

    /**
     * Returns a string representation of this Monster.
     *
     * @return the representation of the Monster as a string
     */
    @Override
    public String toString() {
        return "Monster{"
                + "aim=" + aim
                + ", name='" + name
                + '\'' + ", damage=" + damage
                + ", damageRadius=" + damageRadius
                + ", viewingRadius=" + viewingRadius
                + ", health=" + health
                + ", speed=" + speed
                + ", attackSpeed=" + attackSpeed
                + ", movingTimer=" + movingTimer
                + ", waitingTimer=" + waitingTimer
                + ", currentDirectionIndex=" + currentDirectionIndex
                + ", directionSequence=" + Arrays.toString(directionSequence)
                + ", inCombat=" + inCombat
                + ", isDead=" + isDead
                + ", lastAttack=" + lastAttack
                + '}';
    }

    /**
     * Compares this Monster with another object for equality.
     * Two Monster objects are considered equal if they have the same values for all properties,
     * as well as superclass properties.
     *
     * @param object the object to compare with this Monster
     * @return true if the given object is equal to this Monster, false otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        Monster monster = (Monster) object;
        return Double.compare(damage, monster.damage) == 0
                && Double.compare(damageRadius, monster.damageRadius) == 0
                && Double.compare(viewingRadius, monster.viewingRadius) == 0
                && Double.compare(health, monster.health) == 0
                && Double.compare(speed, monster.speed) == 0
                && Double.compare(attackSpeed, monster.attackSpeed) == 0
                && Double.compare(movingTimer, monster.movingTimer) == 0
                && Double.compare(waitingTimer, monster.waitingTimer) == 0
                && currentDirectionIndex == monster.currentDirectionIndex
                && inCombat == monster.inCombat && isDead == monster.isDead
                && Double.compare(lastAttack, monster.lastAttack) == 0
                && Objects.equals(aim, monster.aim)
                && Objects.equals(name, monster.name)
                && Objects.deepEquals(directionSequence, monster.directionSequence);
    }

    /**
     * Returns the hash code of this Monster.
     *
     * @return the hash code value of the Monster as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), aim, name, damage, damageRadius, viewingRadius,
                health, speed, attackSpeed, movingTimer, waitingTimer, currentDirectionIndex,
                Arrays.hashCode(directionSequence), inCombat, isDead, lastAttack);
    }
}
