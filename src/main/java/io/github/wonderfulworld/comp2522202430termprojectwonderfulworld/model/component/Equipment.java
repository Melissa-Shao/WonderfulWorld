package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.component;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.recovery.AEquipment;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment.Armor;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment.Weapon;
import java.util.Objects;
import lombok.Getter;

/**
 * Represents a character's equipped items, including a weapon and armor.
 * This class allows for managing the character's weapon and armor by setting
 * and unsetting these items, and updating the inventory accordingly.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
@Getter
public class Equipment {

    /**
     * The weapon currently equipped by the character.
     */
    private Weapon weapon;

    /**
     * The armor currently equipped by the character.
     */
    private Armor armor;

    /**
     * Equips the specified item for the character, replacing any existing item of the same type.
     * If a weapon or armor is already equipped, it is added back to the inventory
     * before equipping the new item.
     *
     * @param item      the item to be equipped
     * @param inventory the inventory where unequipped items are stored
     */
    public void setEquipment(final AEquipment item, final Inventory inventory) {
        // Remove item from inventory to be equipment
        inventory.removeItem(item);

        // If the item is a weapon
        if (item instanceof Weapon) {
            // If the player has a weapon before, put the old weapon into inventory
            if (weapon != null) {
                inventory.addItem(weapon);
            }
            // Put on the new weapon
            weapon = (Weapon) item;
        }

        // If the item is an armor
        if (item instanceof Armor) {
            // If the player has an armor before, put the old armor into inventory
            if (armor != null) {
                inventory.addItem(armor);
            }
            // Put on the new armor
            armor = (Armor) item;
        }
    }

    /**
     * Unequips the specified item from the character and returns it to the inventory.
     * If the item is currently equipped, it is added back to the inventory
     * and the corresponding equipment slot (weapon or armor) is cleared.
     *
     * @param item      the item to be unequipped
     * @param inventory the inventory where the unequipped item will be stored
     */
    public void unsetEquipment(final AEquipment item, final Inventory inventory) {
        // If the item is a weapon
        if (item instanceof Weapon) {
            // If the player has no current weapon, return none
            if (weapon == null) {
                return;
            }
            // If the weapon is the same with item, put the weapon back into inventory
            if (weapon.equals(item)) {
                if (inventory.addItem(item)) {
                    // Set the current weapon to be null
                    weapon = null;
                }
            }
        }

        // If the item is an armor
        if (item instanceof Armor) {
            // If the player has no current armor, return none
            if (armor == null) {
                return;
            }
            // If the armor is the same with item, put the armor back into inventory
            if (armor.equals(item)) {
                if (inventory.addItem(item)) {
                    // Set the current armor to be null
                    armor = null;
                }
            }
        }
    }

    /**
     * Returns a string representation of this Equipment.
     *
     * @return the representation of the Equipment as a string
     */
    @Override
    public String toString() {
        return "Equipment{" + "weapon=" + weapon + ", armor=" + armor + '}';
    }

    /**
     * Compares this Equipment with another object for equality.
     * Two Equipment are considered equal if they contain the same type object.
     *
     * @param object the object to compare with this Equipment
     * @return true if the given object is equal to this Equipment, false otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Equipment equipment = (Equipment) object;
        return Objects.equals(weapon, equipment.weapon) && Objects.equals(armor, equipment.armor);
    }

    /**
     * Returns the hash code of this Equipment.
     *
     * @return the hash code value of the Equipment as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(weapon, armor);
    }
}
