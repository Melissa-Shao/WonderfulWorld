package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Player;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.sprite.ASprite;
import java.util.Objects;
import lombok.Getter;

/**
 * AItem is an abstract class representing items in the game.
 * It serves as a base class for various items that can be interacted with by players,
 * such as being picked up or dropped.
 * Each item has an ID and a name, which uniquely identifies it.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
@Getter
public abstract class AItem extends ASprite {

    /**
     * The item id.
     */
    protected final int id;

    /**
     * The item name.
     */
    protected final String name;

    /**
     * Instantiates a new item.
     *
     * @param id   the item id
     * @param name the item name
     */
    public AItem(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * A method that allows a character to take the item.
     *
     * @param player the player who takes the item
     * @return the boolean that indicates whether the item was taken
     */
    public boolean take(final Player player) {
        return player.getInventory().addItem(this);
    }

    /**
     * A method that allows a character to drop an item.
     *
     * @param player the player who drops the item
     * @return the boolean that indicates whether the item was dropped
     */
    public boolean drop(final Player player) {
        return player.getInventory().removeItem(this);
    }

    /**
     * Returns a string representation of this AItem.
     *
     * @return the representation of the AItem as a string
     */
    @Override
    public String toString() {
        return "AItem{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    /**
     * Compares this AItem with another object for equality.
     * Two AItem objects are considered equal if they have the same id and name,
     * and the superclass properties are also equal.
     *
     * @param object the object to compare with this AItem
     * @return true if the given object is equal to this AItem, false otherwise
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
        AItem aItem = (AItem) object;
        return id == aItem.id && Objects.equals(name, aItem.name);
    }

    /**
     * Returns the hash code of this AItem.
     *
     * @return the hash code value of the AItem as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name);
    }
}
