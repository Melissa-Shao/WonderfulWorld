package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller.GameOverController;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.Config;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Represents the game menu view in the application.
 * This class extends AView and defines the specific behavior and layout
 * of the game's menu. It includes buttons for various menu options such as
 * "Continue Game", "Save Game", "To Main Menu", and "Exit Game".
 *
 * @author Melissa Shao, Candice Wei
 * @version 2024
 */
public class GameOverView extends AView {
    // Constants for layout and styling
    private static final double BUTTON_PREF_WIDTH = 200;
    private static final double BUTTON_PREF_HEIGHT = 56;
    private static final double TITLE_FONT_SIZE = 64;
    private static final double VBOX_SPACING = 32;
    private static final String BACKGROUND_STYLE = "-fx-background-color: #FFB6C1;";
    private static final String BUTTON_STYLE = "-fx-background-color: #FFD700;"
            + " -fx-text-fill: #FFFFFF;"
            + " -fx-font-size: 16px;"
            + " -fx-font-weight: bold;"
            + " -fx-border-radius: 10px;"
            + " -fx-background-radius: 10px;"
            + " -fx-cursor: hand;";

    /**
     * Constructs a new GameOverView with the specified controller.
     *
     * @param controller the GameOverController responsible for handling game over interactions
     */
    public GameOverView(final GameOverController controller) {
        this.controller = controller;
    }

    /**
     * Initializes the game menu view.
     */
    @Override
    public void init() {
        // JavaFX initialization
        VBox vBox = new VBox();

        // VBox initialization
        vBox.setStyle(BACKGROUND_STYLE);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(VBOX_SPACING);

        // Title text
        Text title = new Text("GAME OVER");
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, TITLE_FONT_SIZE));
        title.setFill(Color.WHITE);

        // Description text
        Text description = new Text("\n"
                + "Your path was very long and difficult,"
                + " unfortunately, you fell from the hands of darkness.");
        description.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, TITLE_FONT_SIZE));
        description.setFill(Color.WHITE);

        // Main Menu button
        Button mainMenu = new Button("To Main Menu");
        mainMenu.setPrefHeight(BUTTON_PREF_HEIGHT);
        mainMenu.setPrefWidth(BUTTON_PREF_WIDTH);
        mainMenu.setStyle(BUTTON_STYLE);

        // Add components to VBox
        vBox.getChildren().add(title);
        vBox.getChildren().add(description);
        vBox.getChildren().add(mainMenu);

        // Create scene
        scene = new Scene(vBox, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);

        // Attaching Event Listeners
        mainMenu.setOnMouseClicked(((GameOverController) controller)::mainMenuButton);
    }

    /**
     * Renders the game over view.
     */
    @Override
    public void render() {
    }

    /**
     * Returns a string representation of this GameOverView.
     *
     * @return the representation of the GameOverView as a string.
     */
    @Override
    public String toString() {
        return "GameOverView{}";
    }
}
