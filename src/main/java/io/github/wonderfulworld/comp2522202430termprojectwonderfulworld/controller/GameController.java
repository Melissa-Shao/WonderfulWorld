package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.StateManager;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.GameModel;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;

/**
 * The GameController class represents the game controller of the application.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public class GameController extends AController implements IController {

    private ArrayList<String> input = new ArrayList<>();

    /**
     * Initializes the GameController.
     */
    @Override
    public void init() {
        if (wasInitialized) {
            return;
        }

        // Links to game entitles
        GameModel gameModel = GameModel.getInstance();
        wasInitialized = true;
    }

    /**
     * Handles the click event for a key press.
     *
     * @param e the key event triggered by user input
     */
    @Override
    public void keyPress(final KeyEvent e) {
        String code = e.getCode().toString();

        // Track inputs
        if (!input.contains(code)) {
            input.add(code);
        }

        // Player attack
        if (code.equals("J")) {
            playerAttack();
        }

        // Custom ESCAPE behavior
        if (code.equals("ESCAPE")) {
            input = new ArrayList<>();
            StateManager.goToGameMenu();
        }

        // Custom Inventory behavior
        if (code.equals("I")) {
            StateManager.goToInventory();
        }
    }

    /**
     * Handles the click event for a key press.
     *
     * @param e the key event triggered by user input
     */
    @Override
    public void keyRelease(final KeyEvent e) {
        String code = e.getCode().toString();
        input.remove(code);
    }

    /**
     * Player attack.
     */
    public void playerAttack() {
    }
}
