package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.recovery;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.AItem;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Player;

/**
 * The abstract class AEquipment represents equipment
 * that can be equipped or unequipped by a player.
 * Extends the base item class AItem to inherit common item properties and methods.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
public abstract class AEquipment extends AItem {

    /**
     * Constructs a new AEquipment object.
     *
     * @param id   the unique identifier of the equipment
     * @param name the name of the equipment
     */
    public AEquipment(final int id, final String name) {
        super(id, name);
    }

    /**
     * Equips this item for the specified player.
     * Calls the player's setEquipment method to assign this equipment to them.
     *
     * @param player the player who will equip the item
     */
    public void equip(final Player player) {
        player.setEquipment(this);
    }

    /**
     * Unequips this item from the specified player.
     * Calls the player's unsetEquipment method to remove this equipment from them.
     *
     * @param player the player who will unequip the item
     */
    public void unequip(final Player player) {
        player.unsetEquipment(this);
    }
}
