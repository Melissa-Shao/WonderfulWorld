package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.recovery.AEquipment;
import java.util.Objects;
import lombok.Getter;

/**
 * Represents an Armor item that provides protection to a character.
 * Armor items reduce the damage taken by a character based on their armor value.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
@Getter
public class Armor extends AEquipment {
    /**
     * The armor value that determines the amount of protection provided.
     */
    private final double armor;

    /**
     * Constructs a new Armor item with the specified ID, name, and armor value.
     *
     * @param id    the unique identifier for the Armor item
     * @param name  the name of the Armor item
     * @param armor the protective value of the Armor
     */
    public Armor(final int id, final String name, final double armor) {
        super(id, name);
        this.armor = armor;
    }

    /**
     * Returns a string representation of this Armor.
     *
     * @return the representation of the Armor as a string
     */
    @Override
    public String toString() {
        return "Armor{" + "armor=" + armor + '}';
    }

    /**
     * Compares this Armor with another object for equality.
     * Two Armor objects are considered equal if they have the same armor value
     * and the superclass properties are also equal.
     *
     * @param object the object to compare with this Armor
     * @return true if the given object is equal to this Armor, false otherwise
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
        Armor armor1 = (Armor) object;
        return Double.compare(armor, armor1.armor) == 0;
    }

    /**
     * Returns the hash code of this Armor.
     *
     * @return the hash code value of the Armor as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), armor);
    }
}
