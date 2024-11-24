package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller;

/**
 * The InventoryController class represents the inventory in the game.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public class InventoryController extends AController implements IController {
    /**
     * Initializes the InventoryController.
     */
    public void init() {
        if (wasInitialized) {
            return;
        }
        wasInitialized = true;
    }

    /**
     * Returns a string representation of this InventoryController.
     *
     * @return the representation of the InventoryController as a string
     */
    @Override
    public String toString() {
        return "InventoryController{}";
    }
}
