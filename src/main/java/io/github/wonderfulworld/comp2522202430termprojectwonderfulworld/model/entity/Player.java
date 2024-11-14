package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.sprite.ASprite;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.IDamageable;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.component.Equipment;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.component.HP;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.component.Inventory;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.AItem;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.recovery.AEquipment;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.StateManager;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.recovery.HealthBottle;
import java.util.Objects;
import lombok.Getter;

/**
 * The Player class represents a character in the game.
 * It manages the player's attributes, combat abilities, inventory, and equipment.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
public class Player extends ASprite implements IDamageable {
    /**
     * Combat-related constants.
     */
    private static final int MAX_INVENTORY_SIZE = 14; // Maximum number of items in the inventory
    private static final double ATTACK_SPEED = 300; // Attack speed in milliseconds
    // Base percentage for damage reduction calculations
    private static final int PERCENTAGE_BASE = 100;

    /**
     * Player status variables.
     */
    private boolean isDead; // Flag to indicate if the player is dead
    private double lastAttack; // Timestamp of the last attack to manage cooldowns

    /**
     * Character properties.
     */
    @Getter private final String name; // Player's name
    private final HP health; // Health points (HP) for the player
    @Getter private final double basicDamage; // Basic damage without equipment
    @Getter private final double basicArmor; // Basic armor without equipment
    private final double damageRadius; // Damage radius for attacks

    /**
     * Inventory and equipment.
     */
    @Getter private final Inventory inventory; // Inventory for storing items
    @Getter private final Equipment equipment; // Equipment for combat enhancements

    /**
     * Constructs a new Player instance with specified attributes.
     *
     * @param name         the player's name
     * @param health       initial health points
     * @param damage       basic damage
     * @param armor        basic armor
     * @param damageRadius the radius within which the player can attack
     */
    public Player(final String name, final double health, final double damage,
                  final double armor, final double damageRadius) {
        this.name = name;
        this.health = new HP(health); // Initialize health using HP class
        this.basicDamage = damage;
        this.basicArmor = armor;
        this.damageRadius = damageRadius;
        this.inventory = new Inventory(MAX_INVENTORY_SIZE); // Inventory with a capacity of 14 items
        this.equipment = new Equipment();
        this.isDead = false;
    }

    /**
     * Attacks a specified target if the attack cooldown has expired.
     * Checks if the target is still alive before proceeding.
     *
     * @param target the target entity to attack
     */
    @Override
    public void attack(final IDamageable target) {
        if ((System.currentTimeMillis() - lastAttack) < ATTACK_SPEED) {
            return; // Attack is on cooldown, so exit method
        }

        lastAttack = System.currentTimeMillis(); // Reset cooldown timer

        if (!target.isDead()) {
            ((Monster) target).inAttack(this);
        }
    }

    /**
     * Processes the attack on this player from a specified monster.
     * Reduces player's health based on the monster's damage and player's armor.
     *
     * @param monster the attacking monster
     */
    public void inAttack(final Monster monster) {
        double incomingDamage = monster.getDamage()
                * (PERCENTAGE_BASE / (PERCENTAGE_BASE + getArmor())); // Calculate effective damage
        health.reduceHealth(incomingDamage); // Reduce health by calculated damage
        if (health.getHealth() == 0) {
            isDead = true;
            StateManager.gameOver(); // Trigger game over sequence
        }
    }

    /**
     * Checks if the player is dead.
     *
     * @return true if the player's health has reached zero; false otherwise
     */
    @Override
    public boolean isDead() {
        return isDead;
    }

    /**
     * Heals the player by using a HealthBottle, adding health points.
     *
     * @param healthBottle the health bottle item to use
     */
    public void inHealth(final HealthBottle healthBottle) {
        if (!inventory.isInInventory(healthBottle)) {
            return; // Check if item is in inventory
        }

        health.addHealth(healthBottle.getHealth()); // Add health points
        inventory.removeItem(healthBottle); // Remove the used item from inventory
    }

    /**
     * Equips an item, adding its stats to the player's attributes.
     *
     * @param item the equipment to add
     */
    public void setEquipment(final AEquipment item) {
        equipment.setEquipment(item, inventory); // Equip item and adjust stats
    }

    /**
     * Unequips an item, removing its stats from the player's attributes.
     *
     * @param item the equipment to remove
     */
    public void unsetEquipment(final AEquipment item) {
        equipment.unsetEquipment(item, inventory); // Unequip item and adjust stats
    }

    /**
     * Calculates the total damage based on basic damage and weapon.
     *
     * @return the player's total damage with weapon equipped (if any)
     */
    public double getDamage() {
        double totalDamage = basicDamage;
        if (equipment.getWeapon() != null) {
            totalDamage += equipment.getWeapon().getDamage();
        }
        return totalDamage;
    }

    /**
     * Calculates the total armor based on basic armor and equipped armor.
     *
     * @return the player's total armor with equipped armor (if any)
     */
    public double getArmor() {
        double totalArmor = basicArmor;
        if (equipment.getArmor() != null) {
            totalArmor += equipment.getArmor().getArmor();
        }
        return totalArmor;
    }

    /**
     * Calculates the total damage radius based on basic radius and weapon's radius.
     *
     * @return the player's total damage radius with weapon equipped (if any)
     */
    public double getDamageRadius() {
        double totalDamageRadius = damageRadius;
        if (equipment.getWeapon() != null) {
            totalDamageRadius += equipment.getWeapon().getRadius();
        }
        return totalDamageRadius;
    }

    /**
     * Gets basic damage radius.
     *
     * @return the basic damage radius
     */
    public double getBasicDamageRadius() {
        return damageRadius;
    }

    /**
     * Gets health.
     *
     * @return the health of the player
     */
    public double getHealth() {
        return health.getHealth();
    }

    /**
     * Gets HP.
     *
     * @return the player's health
     */
    public HP getHP() {
        return health;
    }

    /**
     * Gets current weapon.
     *
     * @return the current weapon of the player
     */
    public AItem getCurrentWeapon() {
        return equipment.getWeapon();
    }

    /**
     * Gets current armor.
     *
     * @return The current armor.
     */
    public AItem getCurrentArmor() {
        return equipment.getArmor();
    }

    /**
     * Returns a string representation of this Player.
     *
     * @return the representation of the Player as a string
     */
    @Override
    public String toString() {
        return "Player{"
                + "isDead=" + isDead
                + ", lastAttack=" + lastAttack
                + ", name='" + name
                + '\'' + ", health="
                + health + ", basicDamage="
                + basicDamage + ", basicArmor="
                + basicArmor + ", damageRadius="
                + damageRadius + ", inventory="
                + inventory + ", equipment="
                + equipment
                + '}';
    }

    /**
     * Compares this Player with another object for equality.
     * Two Player objects are considered equal if they have the same values for all properties,
     * as well as superclass properties.
     *
     * @param object the object to compare with this Player
     * @return true if the given object is equal to this Player, false otherwise
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
        Player player = (Player) object;
        return isDead == player.isDead
                && Double.compare(lastAttack, player.lastAttack) == 0
                && Double.compare(basicDamage, player.basicDamage) == 0
                && Double.compare(basicArmor, player.basicArmor) == 0
                && Double.compare(damageRadius, player.damageRadius) == 0
                && Objects.equals(name, player.name)
                && Objects.equals(health, player.health)
                && Objects.equals(inventory, player.inventory)
                && Objects.equals(equipment, player.equipment);
    }

    /**
     * Returns the hash code of this Player.
     *
     * @return the hash code value of the Player as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isDead, lastAttack, name, health, basicDamage,
                basicArmor, damageRadius, inventory, equipment);
    }
}
