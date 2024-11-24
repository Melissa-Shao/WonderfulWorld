package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.StateManager;
import javafx.scene.input.KeyEvent;

/**
 * The GameMenuController class represents the game menu of the application.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public class GameMenuController extends AController implements IController {
    /**
     * Initializes the GameMenuController.
     */
    public void init() {
        if (wasInitialized) {
            return;
        }
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
        if (code.equals("ESCAPE")) {
            StateManager.continueGame();
        }
    }

    /**
     * Returns a string representation of this GameMenuController.
     *
     * @return the representation of the GameMenuController as a string
     */
    @Override
    public String toString() {
        return "GameMenuController{}";
    }
}
