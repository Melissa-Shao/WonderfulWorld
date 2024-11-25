package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view.component.inventory;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.AItem;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view.InventoryView;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Represents a GUI element for an item in the inventory.
 * This class extends JavaFX Button to visually represent items
 * in the inventory system with customizable styles and focus behaviors.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
public class ItemView extends Button {

    /**
     * The constant ITEM_SIZE represents the width and height of the ItemView.
     */
    private static final int ITEM_SIZE = 64;

    /**
     * The Item reference.
     */
    private AItem itemRef;

    /**
     * Constructs a new ItemView instance and sets up its default styles and focus listeners.
     *
     * @param inventoryView the parent that manages this item view
     */
    public ItemView(final InventoryView inventoryView) {
        super();

        setPrefWidth(ITEM_SIZE);
        setPrefHeight(ITEM_SIZE);
        setDefaultStyle();

        // Focus Events
        focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                setFocusedStyle();
                inventoryView.setItemInfo(itemRef);
            } else {
                setDefaultStyle();
            }
        });
    }

    /**
     * Sets the focused style for the item view.
     * This style is applied when the item view gains focus.
     */
    private void setFocusedStyle() {
        setStyle(
                "-fx-background-color: #232933;"
                        + "-fx-border-radius: 2px;"
                        + "-fx-background-radius: 2px;"
                        + "-fx-border-width: 1px;"
                        + "-fx-border-style: solid;"
                        + "-fx-border-color: #20ADB6;"
                        + "-fx-focus-color: transparent;"
                        + "-fx-faint-focus-color: transparent;"
        );
    }

    /**
     * Sets the default style for the item view.
     * This style is applied when the item view loses focus.
     */
    private void setDefaultStyle() {
        setStyle(
                "-fx-background-color: #232933;"
                        + "-fx-border-radius: 2px;"
                        + "-fx-background-radius: 2px;"
                        + "-fx-border-width: 1px;"
                        + "-fx-border-style: solid;"
                        + "-fx-border-color: #13171E;"
                        + "-fx-focus-color: transparent;"
                        + "-fx-faint-focus-color: transparent;"
        );
    }

    /**
     * Associates an AItem with this ItemView.
     * Updates the graphical representation of the item using its image.
     *
     * @param item the AItem to be associated with this view
     */
    public void setItem(final AItem item) {
        itemRef = item;
        setGraphic(new ImageView(item.getImage()));
    }

    /**
     * Returns the item associated with this ItemView.
     *
     * @return the AItem associated with this view
     */
    public AItem getItem() {
        return itemRef;
    }

    /**
     * Returns a string representation of this ItemView.
     *
     * @return the representation of the ItemView as a string
     */
    @Override
    public String toString() {
        return "ItemView{" + "itemRef=" + itemRef + '}';
    }

    /**
     * Compares this ItemView with another object for equality.
     * Two HPBox are considered equal if they contain the same health.
     *
     * @param object the object to compare with this ItemView
     * @return true if the given object is equal to this ItemView, false otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ItemView itemView = (ItemView) object;
        return Objects.equals(itemRef, itemView.itemRef);
    }

    /**
     * Returns the hash code of this ItemView.
     *
     * @return the hash code value of the ItemView as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(itemRef);
    }
}
