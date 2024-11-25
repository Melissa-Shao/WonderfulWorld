package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.component;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

/**
 * Represents a character's health (HP) and provides methods to manage it.
 * This class allows adding and reducing health when the health changes.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
public class HP {

    /**
     * The maximum (initial) health of the character.
     */
    @Getter @Setter private double initialHealth;

    /**
     * The current health of the character, represented as a DoubleProperty.
     */
    private final DoubleProperty currentHealth;

    /**
     * Constructs an HP instance with a specified initial health value.
     *
     * @param health the initial health value
     */
    public HP(final double health) {
        this.initialHealth = health;
        this.currentHealth = new SimpleDoubleProperty(health);
    }

    /**
     * Adds health to the character up to the maximum initial health.
     *
     * @param health the amount of health to add
     */
    public void addHealth(final double health) {
        currentHealth.set(Math.min(currentHealth.get() + health, initialHealth));
    }

    /**
     * Reduces health from the character down to a minimum of zero.
     *
     * @param health the amount of health to reduce
     */
    public void reduceHealth(final double health) {
        currentHealth.set(Math.max(currentHealth.get() - health, 0));
    }

    /**
     * Gets the current health property for data binding.
     *
     * @return the current health property
     */
    public DoubleProperty healthProperty() {
        return currentHealth;
    }

    /**
     * Gets the current health of the character.
     *
     * @return the current health value, rounded to the nearest whole number
     */
    public double getHealth() {
        return currentHealth.get();
    }

    /**
     * Sets the current health value.
     *
     * @param health the new health value
     */
    public void setHealth(final double health) {
        currentHealth.set(Math.min(health, initialHealth));
    }

    /**
     * Returns a string representation of this HP.
     *
     * @return the representation of the HP as a string
     */
    @Override
    public String toString() {
        return "HP{" + "initialHealth=" + initialHealth
                + ", currentHealth=" + currentHealth.get() + '}';
    }

    /**
     * Compares this HP with another object for equality.
     * Two HP objects are considered equal if they have the same initial and current health values.
     *
     * @param object the object to compare with this HP
     * @return true if the given object is equal to this HP, false otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        HP hp = (HP) object;
        return Double.compare(initialHealth, hp.initialHealth) == 0
                && Double.compare(currentHealth.get(), hp.currentHealth.get()) == 0;
    }

    /**
     * Returns the hash code of this HP.
     *
     * @return the hash code value of the HP as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(initialHealth, currentHealth.get());
    }
}
