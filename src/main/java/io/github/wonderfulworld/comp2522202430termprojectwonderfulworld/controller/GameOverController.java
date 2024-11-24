package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller;

/**
 * The GameOverController class represents the game over of the application.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public class GameOverController extends AController implements IController {
    /**
     * Initializes the GameOverController.
     */
    public void init() {
        if (wasInitialized) {
            return;
        }
        wasInitialized = true;
    }

    /**
     * Returns a string representation of this GameOverController.
     *
     * @return the representation of the GameOverController as a string
     */
    @Override
    public String toString() {
        return "GameOverController{}";
    }
}
