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
 * @author Melissa Shao, Candice Wei
 * @version 2024
 */
public class ItemView extends Button {

    /**
     * The constant ITEM_SIZE represents the width and height of the ItemView.
     */
    private static final int ITEM_SIZE = 64;
    private static final int TILE_SIZE = 16;

    /**
     * Style constants for better maintainability.
     */
    private static final String BASE_STYLE =
            "-fx-background-radius: 8px;" + "-fx-border-radius: 8px;"
                    + "-fx-border-width: 2px;" + "-fx-border-style: solid;"
                    + "-fx-focus-color: transparent;" + "-fx-faint-focus-color: transparent;";

    private static final String DEFAULT_BACKGROUND = "-fx-background-color:"
            + " rgba(255, 255, 255, 0.3);";
    private static final String DEFAULT_BORDER = "-fx-border-color: rgba(255, 255, 255, 0.5);";

    private static final String FOCUSED_BACKGROUND = "-fx-background-color:"
            + "rgba(255, 255, 255, 0.5);";
    private static final String FOCUSED_BORDER = "-fx-border-color: #FFD700;";

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
        setMinWidth(ITEM_SIZE);
        setMinHeight(ITEM_SIZE);
        setMaxWidth(ITEM_SIZE);
        setMaxHeight(ITEM_SIZE);

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
        setStyle(BASE_STYLE + FOCUSED_BACKGROUND + FOCUSED_BORDER);
    }

    /**
     * Sets the default style for the item view.
     * This style is applied when the item view loses focus.
     */
    private void setDefaultStyle() {
        setStyle(BASE_STYLE + DEFAULT_BACKGROUND + DEFAULT_BORDER);
    }

    /**
     * Associates an AItem with this ItemView.
     * Updates the graphical representation of the item using its image.
     *
     * @param item the AItem to be associated with this view
     */
    public void setItem(final AItem item) {
        itemRef = item;
        if (item != null) {
            ImageView imageView = new ImageView(item.getImage());
            imageView.setFitWidth(ITEM_SIZE - TILE_SIZE);
            imageView.setFitHeight(ITEM_SIZE - TILE_SIZE);
            imageView.setPreserveRatio(true);
            setGraphic(imageView);
        } else {
            setGraphic(null);
        }
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
