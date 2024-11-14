package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.recovery;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.AItem;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Player;
import java.util.Objects;
import lombok.Getter;

/**
 * Represents a HealthBottle item that can restore health to a player.
 * This item is used to increase the health of a player when consumed.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
@Getter
public class HealthBottle extends AItem {

    /**
     * The amount of health the HealthBottle can restore.
     */
    private final double health;

    /**
     * Constructs a new HealthBottle with the specified ID, name, and health value.
     *
     * @param id     the unique identifier for the HealthBottle
     * @param name   the name of the HealthBottle
     * @param health the amount of health this bottle can restore
     */
    public HealthBottle(final int id, final String name, final double health) {
        super(id, name);
        this.health = health;
    }

    /**
     * Uses the HealthBottle to restore health to the specified player.
     *
     * @param player the player who uses the HealthBottle
     */
    public void use(final Player player) {
        player.inHealth(this);
    }

    /**
     * Returns a string representation of this HealthBottle.
     *
     * @return the representation of the HealthBottle as a string
     */
    @Override
    public String toString() {
        return "HealthBottle{" + "health=" + health + '}';
    }

    /**
     * Compares this HealthBottle with another object for equality.
     * Two HealthBottle objects are considered equal if they have the same health value
     * and the superclass properties are also equal.
     *
     * @param object the object to compare with this HealthBottle
     * @return true if the given object is equal to this HealthBottle, false otherwise
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
        HealthBottle that = (HealthBottle) object;
        return Double.compare(health, that.health) == 0;
    }

    /**
     * Returns the hash code of this HealthBottle.
     *
     * @return the hash code value of the HealthBottle as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), health);
    }
}
