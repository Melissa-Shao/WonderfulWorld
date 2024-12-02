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
        String code = e.getCode().toString();

        switch (code) {
            case "ESCAPE" -> StateManager.goToMainMenu();
            case "I" -> StateManager.continueGame();
            case "J" -> handleEquipmentToggle(e);
            case "K" -> handleHealthBottleUse(e);
            case "L" -> handleItemDrop(e);
            default -> {
                // Do nothing for unhandled keys
            }
        }
    }

    /**
     * Handles equipping and un equipping items.
     *
     * @param e the key event containing the target item view
     */
    private void handleEquipmentToggle(final KeyEvent e) {
        if (!(e.getTarget() instanceof ItemView)) {
            return;
        }

        AItem item = ((ItemView) e.getTarget()).getItem();
        if (!(item instanceof AEquipment equipment)) {
            return;
        }

        Player player = GameModel.getInstance().getPlayer();
        Inventory inventory = player.getInventory();

        if (inventory.isInInventory(item)) {
            equipment.equip(player);
        } else {
            equipment.unequip(player);
        }
    }

    /**
     * Handles using health bottles.
     *
     * @param e the key event containing the target item view
     */
    private void handleHealthBottleUse(final KeyEvent e) {
        if (!(e.getTarget() instanceof ItemView)) {
            return;
        }

        AItem item = ((ItemView) e.getTarget()).getItem();
        if (item instanceof HealthBottle) {
            ((HealthBottle) item).use(GameModel.getInstance().getPlayer());
        }
    }

    /**
     * Handles dropping items.
     *
     * @param e the key event containing the target item view
     */
    private void handleItemDrop(final KeyEvent e) {
        if (!(e.getTarget() instanceof ItemView)) {
            return;
        }

        AItem item = ((ItemView) e.getTarget()).getItem();
        if (item != null) {
            item.drop(GameModel.getInstance().getPlayer());
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
