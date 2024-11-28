package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view.GameOverView;

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
        view = new GameOverView(this);
        view.init();
    }

    /**
     * A method is called by the main timer every frame to update the state of the controller.
     *
     *
     * @param delta the time elapsed since the last tick (in seconds)
     */
    @Override
    public void tick(final double delta) {
        view.render();
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
