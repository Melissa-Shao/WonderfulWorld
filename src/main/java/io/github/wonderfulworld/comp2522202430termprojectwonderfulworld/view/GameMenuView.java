package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller.GameMenuController;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.Config;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Represents the game menu view in the application.
 * This class extends AView and defines the specific behavior and layout
 * of the game's menu. It includes buttons for various menu options such as
 * "Continue Game", "Save Game", "To Main Menu", and "Exit Game".
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
public class GameMenuView extends AView {
    // Constants for layout
    private static final int BUTTON_SPACING = 16; // Spacing between buttons in VBox

    /**
     * The constant BACKGROUND_STYLE defines the CSS styling applied to the menu background.
     */
    private static final String BACKGROUND_STYLE = "-fx-background-color: #232933; ";

    /**
     * Constructs a new GameMenuView with the specified controller.
     *
     * @param controller the GameMenuController responsible for handling menu interactions
     */
    public GameMenuView(final GameMenuController controller) {
        this.controller = controller;
    }

    /**
     * Initializes the game menu view.
     *
     */
    @Override
    public void init() {
        // JavaFX init
        VBox vBox = new VBox();
        ArrayList<Button> buttons = new ArrayList<>();

        // Tile Pane Init
        vBox.setStyle(BACKGROUND_STYLE);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(BUTTON_SPACING);

        // Create Buttons
        Button continueGame = new Button("Continue Game");
        Button saveGame = new Button("Save Game");
        Button toMainMenu = new Button("To Main Menu");
        Button exitGame = new Button("Exit Game");

        // Add buttons to the list
        buttons.add(continueGame);
        buttons.add(saveGame);
        buttons.add(toMainMenu);
        buttons.add(exitGame);

        // Configure buttons and add them to VBox
        configureAndAddButtonsToVBox(buttons, vBox);

        // Create Scene
        scene = new Scene(vBox, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);

        // Attaching Event Listeners
        scene.setOnKeyPressed(((GameMenuController) controller)::keyPress);
        continueGame.setOnMouseClicked(((GameMenuController) controller)
                ::gameContinueButton);
        saveGame.setOnMouseClicked(((GameMenuController) controller)::gameSaveButton);
        toMainMenu.setOnMouseClicked(((GameMenuController) controller)
                ::mainMenuButton);
        exitGame.setOnMouseClicked(((GameMenuController) controller)::exitGameButton);
    }

    /**
     * Renders the game menu view.
     *
     */
    @Override
    public void render() {
    }

    /**
     * Returns a string representation of this GameMenuView.
     *
     * @return the representation of the GameMenuView as a string.
     */
    @Override
    public String toString() {
        return "GameMenuView{}";
    }
}
