package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller;

/**
 * The MainMenuController class represents the main menu of the application.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public class MainMenuController extends AController implements IController {
    /**
     * Initializes the MainMenuController.
     */
    public void init() {
        if (wasInitialized) {
            return;
        }
        wasInitialized = true;
    }

    /**
     * Returns a string representation of this MainMenuController.
     *
     * @return the representation of the MainMenuController as a string
     */
    @Override
    public String toString() {
        return "MainMenuController{}";
    }
}
