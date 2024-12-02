package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.Config;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.StateManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.nio.file.Path;

/**
 * The main entry point for the Wonderful World game application.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public class Game extends Application {
    /**
     * Starts the JavaFX application.
     *
     *
     * @param stage the primary stage for the application
     */
    @Override
    public void start(final Stage stage) {
        // load game configurations
        Config.init("config/config.json");

        // set up the stage
        stage.setTitle(Config.getWindowName());
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(Path.of("config/icon/image.png").toUri().toString()));

        // initialize the state manager
        StateManager.init(stage);
    }

    /**
     * Returns a string representation of this Game.
     *
     * @return the representation of the Game as a string
     */
    @Override
    public String toString() {
        return "Game{}";
    }
}
