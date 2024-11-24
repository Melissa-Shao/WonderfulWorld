package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.PlayerConfig;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.StateManager;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * The IController Interface represents common methods for controllers.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public interface IController {
    // Default methods in Interface share behavior across controllers
    // unless a class explicitly overrides the default behavior.
    /**
     * Handles the click event for navigating to the main menu.
     *
     * @param e the mouse event triggered by the button click
     */
    default void mainMenuButton(MouseEvent e) {
        StateManager.goToMainMenu();
    }

    /**
     * Handles the click event for exiting the game.
     *
     * @param e the mouse event triggered by the button click
     */
    default void exitGameButton(MouseEvent e) {
        StateManager.exitGame();
    }

    /**
     * Handles the click event for a key press.
     *
     * @param e the key event triggered by user input
     */
    default void keyPress(final KeyEvent e) {
        String code = e.getCode().toString();
        if (code.equals("ESCAPE")) {
            StateManager.goToMainMenu();
        }

        if (code.equals("I")) {
            StateManager.goToInventory();
        }
    }

    /**
     * Handles the click event for a key press.
     *
     * @param e the key event triggered by user input
     */
    default void keyRelease(final KeyEvent e) {
        String code = e.getCode().toString();
    }
    /**
     * Handles the click event for loading the game.
     *
     * @param e the mouse event triggered by the button click
     */
    default void gameLoadButton(final MouseEvent e) {
        StateManager.startGame(true);
    }

    /**
     * Handles the click event for continuing the game.
     *
     * @param e the mouse event triggered by the button click
     */
    default void gameContinueButton(final MouseEvent e) {
        StateManager.continueGame();
    }

    /**
     * Handles the click event for saving the game.
     *
     * @param e the mouse event triggered by the button click
     */
    default void gameSaveButton(final MouseEvent e) {
        PlayerConfig.savePlayerConfig();
    }
}

