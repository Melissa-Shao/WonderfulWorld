package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.component;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.AItem;
import java.util.ArrayList;
import java.util.Objects;
import lombok.Getter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents an inventory for managing items with a defined capacity.
 * The inventory can store, add, and remove items while keeping track of its maximum capacity.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
@Getter
public class Inventory {
    /**
     * The maximum capacity of the inventory.
     */
    private final int capacity;

    /**
     * The list of items currently in the inventory.
     */
    private final ArrayList<AItem> items = new ArrayList<>();

    /**
     * Properties for quantity and capacity.
     */
    private final IntegerProperty quantityProperty = new SimpleIntegerProperty(0);
    private final IntegerProperty capacityProperty = new SimpleIntegerProperty();

    /**
     * Constructs an Inventory with the specified maximum capacity.
     *
     * @param capacity the maximum number of items the inventory can hold
     */
    public Inventory(final int capacity) {
        this.capacity = capacity;
        this.capacityProperty.set(capacity);
    }

    /**
     * Adds an item to the inventory if there is space and the item
     * is not already in the inventory.
     *
     * @param item the item to add
     * @return true if the item was successfully added, false otherwise
     */
    public boolean addItem(final AItem item) {
        if (!isFull() && !isInInventory(item)) {
            items.add(item);
            quantityProperty.set(items.size()); // Update quantity property
            return true;
        }
        return false;
    }

    /**
     * Removes the specified item from the inventory.
     *
     * @param item the item to remove
     * @return true if the item was successfully removed, false otherwise
     */
    public boolean removeItem(final AItem item) {
        if (items.remove(item)) {
            quantityProperty.set(items.size()); // Update quantity property
            return true;
        }
        return false;
    }

    /**
     * Checks if the specified item is present in the inventory.
     *
     * @param item the item to check
     * @return true if the item is in the inventory, false otherwise
     */
    public boolean isInInventory(final AItem item) {
        return items.contains(item);
    }

    /**
     * Checks if the inventory has reached its maximum capacity.
     *
     * @return true if the inventory is full, false otherwise
     */
    public boolean isFull() {
        return capacity == items.size();
    }

    /**
     * Gets the current number of items in the inventory.
     *
     * @return the quantity of items currently in the inventory
     */
    public int getQuantity() {
        return items.size();
    }

    /**
     * Property for quantity, used for data binding.
     *
     * @return the quantity property
     */
    public IntegerProperty quantityProperty() {
        return quantityProperty;
    }

    /**
     * Property for capacity, used for data binding.
     *
     * @return the capacity property
     */
    public IntegerProperty capacityProperty() {
        return capacityProperty;
    }

    /**
     * Returns a string representation of this Inventory.
     *
     * @return the representation of the Inventory as a string
     */
    @Override
    public String toString() {
        return "Inventory{" + "capacity=" + capacity + ", items=" + items + '}';
    }

    /**
     * Compares this Inventory with another object for equality.
     * Two Inventory objects are considered equal if they have
     * the same capacity and contain the same items.
     *
     * @param object the object to compare with this Inventory
     * @return true if the given object is equal to this Inventory, false otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Inventory inventory = (Inventory) object;
        return capacity == inventory.capacity
                && Objects.equals(items, inventory.items);
    }

    /**
     * Returns the hash code of this Inventory.
     *
     * @return the hash code value of the Inventory as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(capacity, items);
    }
}
