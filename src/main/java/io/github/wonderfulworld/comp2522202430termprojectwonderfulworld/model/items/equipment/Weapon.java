package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.recovery.AEquipment;
import java.util.Objects;
import lombok.Getter;

/**
 * Represents a Weapon item that can be used to deal damage within a specific radius.
 * Weapon items have a damage value that determines the amount of damage they deal
 * and a radius that defines the area of effect.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
@Getter
public class Weapon extends AEquipment {

    /**
     * The amount of damage this weapon can deal.
     */
    private final double damage;

    /**
     * The radius within which this weapon can deal damage.
     */
    private final double radius;

    /**
     * Constructs a new Weapon with the specified ID, name, damage, and damage radius.
     *
     * @param id     the unique identifier for the Weapon
     * @param name   the name of the Weapon
     * @param damage the damage value of the Weapon
     * @param radius the effective radius of the Weapon's damage
     */
    public Weapon(final int id, final String name, final double damage, final double radius) {
        super(id, name);
        this.damage = damage;
        this.radius = radius;
    }

    /**
     * Returns a string representation of this Weapon.
     *
     * @return the representation of the Weapon as a string
     */
    @Override
    public String toString() {
        return "Weapon{" + "damage=" + damage + ", radius=" + radius + '}';
    }

    /**
     * Compares this Weapon with another object for equality.
     * Two Weapon objects are considered equal if they have the same damage and radius values,
     * and the superclass properties are also equal.
     *
     * @param object the object to compare with this Weapon
     * @return true if the given object is equal to this Weapon, false otherwise
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
        Weapon weapon = (Weapon) object;
        return Double.compare(damage, weapon.damage) == 0
                && Double.compare(radius, weapon.radius) == 0;
    }

    /**
     * Returns the hash code of this Weapon.
     *
     * @return the hash code value of the Weapon as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), damage, radius);
    }
}
