package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.StateManager;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.GameModel;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.component.Inventory;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Player;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.AItem;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.recovery.AEquipment;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.recovery.HealthBottle;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view.InventoryView;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view.component.inventory.ItemView;
import javafx.scene.input.KeyEvent;

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
        view = new InventoryView(this);
        view.init();
    }

    /**
     * A method is called by the main timer every frame to update the state of the controller.
     *
     * @param delta the time elapsed since the last tick (in seconds)
     */
    @Override
    public void tick(final double delta) {
    }

    /**
     * Handles the click event for a key press.
     *
     * @param e the key event triggered by user input
     */
    @Override
    public void keyPress(final KeyEvent e) {
        Player player = GameModel.getInstance().getPlayer();
        Inventory inventory = player.getInventory();
        String code = e.getCode().toString();

        // Extend default behavior with additional key handling
        switch (code) {
            case "ESCAPE" -> StateManager.goToMainMenu();
            case "I" -> StateManager.goToInventory();
            case "J" -> {
                if (e.getTarget() instanceof ItemView) {
                    AItem item = ((ItemView) e.getTarget()).getItem();
                    if (item instanceof AEquipment) {
                        if (inventory.isInInventory(item)) {
                            ((AEquipment) item).equip(player);
                        } else {
                            ((AEquipment) item).unequip(player);
                        }
                    }
                }
            }
            case "K" -> {
                if (e.getTarget() instanceof ItemView) {
                    AItem item = ((ItemView) e.getTarget()).getItem();
                    if (item != null) {
                        ((HealthBottle) item).use(player);
                    }
                }
            }
            case "L" -> {
                if (e.getTarget() instanceof ItemView) {
                    AItem item = ((ItemView) e.getTarget()).getItem();
                    if (item != null) {
                        item.drop(player);
                    }
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + code);
        }
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
