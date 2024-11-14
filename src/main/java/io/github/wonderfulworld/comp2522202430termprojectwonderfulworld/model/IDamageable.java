package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model;

/**
 * Interface representing an entity that can be damaged and can engage in combat.
 * This interface provides the basic structure for any object that can attack
 * other damageable objects and can check if it is still alive.
 *
 * @author Melissa Shao
 * @version 2024
 */
public interface IDamageable {
    /**
     * Performs an attack on the specified target.
     *
     * @param target the target to attack, which must also implement the IDamageable interface
     */
    void attack(IDamageable target);
    /**
     * Checks if the entity is dead.
     *
     * @return true if the entity's health is zero or below, indicating it is dead; false otherwise
     */
    boolean isDead();
}
