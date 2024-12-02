package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.component;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.recovery.AEquipment;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment.Armor;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment.Weapon;
import java.util.Objects;
import lombok.Getter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

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
     * Property for the weapon currently equipped by the character.
     */
    private final ObjectProperty<Weapon> weaponProperty = new SimpleObjectProperty<>();

    /**
     * Property for the armor currently equipped by the character.
     */
    private final ObjectProperty<Armor> armorProperty = new SimpleObjectProperty<>();

    /**
     * Gets the currently equipped weapon.
     *
     * @return the equipped weapon, or null if no weapon is equipped
     */
    public Weapon getWeapon() {
        return weaponProperty.get();
    }

    /**
     * Gets the currently equipped armor.
     *
     * @return the equipped armor, or null if no armor is equipped
     */
    public Armor getArmor() {
        return armorProperty.get();
    }

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
        if (item instanceof Weapon weapon) {
            // If the player has a weapon before, put the old weapon into inventory
            if (weaponProperty.get() != null) {
                inventory.addItem(weaponProperty.get());
            }
            // Put on the new weapon
            weaponProperty.set(weapon);
        }

        // If the item is an armor
        if (item instanceof Armor armor) {
            // If the player has an armor before, put the old armor into inventory
            if (armorProperty.get() != null) {
                inventory.addItem(armorProperty.get());
            }
            // Put on the new armor
            armorProperty.set(armor);
        }
    }

    /**
     * Un equips the specified item from the character and returns it to the inventory.
     * If the item is currently equipped, it is added back to the inventory
     * and the corresponding equipment slot (weapon or armor) is cleared.
     *
     * @param item      the item to be unequipped
     * @param inventory the inventory where the unequipped item will be stored
     */
    public void unsetEquipment(final AEquipment item, final Inventory inventory) {
        // If the item is a weapon
        if (item instanceof Weapon weapon) {
            // If the player has no current weapon, return none
            if (weaponProperty.get() == null) {
                return;
            }
            // If the weapon is the same with item, put the weapon back into inventory
            if (weaponProperty.get().equals(weapon)) {
                if (inventory.addItem(item)) {
                    // Set the current weapon to be null
                    weaponProperty.set(null);
                }
            }
        }

        // If the item is an armor
        if (item instanceof Armor armor) {
            // If the player has no current armor, return none
            if (armorProperty.get() == null) {
                return;
            }
            // If the armor is the same with item, put the armor back into inventory
            if (armorProperty.get().equals(armor)) {
                if (inventory.addItem(item)) {
                    // Set the current armor to be null
                    armorProperty.set(null);
                }
            }
        }
    }

    /**
     * Property for weapon, used for data binding.
     *
     * @return the weapon property
     */
    public ObjectProperty<Weapon> weaponProperty() {
        return weaponProperty;
    }

    /**
     * Property for armor, used for data binding.
     *
     * @return the armor property
     */
    public ObjectProperty<Armor> armorProperty() {
        return armorProperty;
    }

    /**
     * Returns a string representation of this Equipment.
     *
     * @return the representation of the Equipment as a string
     */
    @Override
    public String toString() {
        return "Equipment{" + "weapon=" + weaponProperty.get() + ", armor="
                + armorProperty.get() + '}';
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
        return Objects.equals(weaponProperty.get(), equipment.weaponProperty.get())
                && Objects.equals(armorProperty.get(), equipment.armorProperty.get());
    }

    /**
     * Returns the hash code of this Equipment.
     *
     * @return the hash code value of the Equipment as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(weaponProperty.get(), armorProperty.get());    }
}
