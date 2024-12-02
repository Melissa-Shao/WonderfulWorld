package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller.GameMenuController;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.Config;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

/**
 * Represents the game menu view in the application.
 * This class extends AView and defines the specific behavior and layout
 * of the game's menu. It includes buttons for various menu options such as
 * "Continue Game", "Save Game", "To Main Menu", and "Exit Game".
 *
 *
 * @author Melissa Shao, Candice Wei
 * @version 2024
 */
public class GameMenuView extends AView {
    // CONSTANTS
    private static final int VBOX_SPACING = 20;
    private static final int VBOX_PADDING = 30;
    private static final int BUTTON_PREF_HEIGHT = 60;
    private static final int BUTTON_PREF_WIDTH = 250;
    private static final double SHADOW_OPACITY = 0.3;
    private static final int SHADOW_OFFSET_X = 3;
    private static final int SHADOW_OFFSET_Y = 3;
    private static final double HEART_SCALE = 0.7;
    private static final double HEART_CONTROL_X1 = 0;
    private static final double HEART_CONTROL_Y1 = -4;
    private static final double HEART_CONTROL_X2 = 4;
    private static final double HEART_CONTROL_Y2 = -6;
    private static final double HEART_FINAL_X = 16;
    private static final double HEART_FINAL_Y = 0;
    private static final double HEART_QUAD_CONTROL_X = 8;
    private static final double HEART_QUAD_CONTROL_Y = 8;

    private static final String BUTTON_STYLE = """
            -fx-background-color: linear-gradient(to bottom, #FFB6C1, #FF69B4);
            -fx-background-radius: 20;
            -fx-border-color: #FFD700;
            -fx-border-width: 2;
            -fx-border-radius: 20;
            -fx-text-fill: #FFFFFF;
            -fx-font-size: 18px;
            -fx-font-family: 'Comic Sans MS';
            -fx-font-weight: bold;
            -fx-cursor: hand;
            """;

    private static final String BUTTON_HOVER_STYLE = """
            -fx-background-color: linear-gradient(to bottom, #FF69B4, #FF1493);
            -fx-background-radius: 20;
            -fx-border-color: #FFD700;
            -fx-border-width: 3;
            -fx-border-radius: 20;
            -fx-text-fill: #FFFFFF;
            -fx-font-size: 18px;
            -fx-font-family: 'Comic Sans MS';
            -fx-font-weight: bold;
            -fx-cursor: hand;
            """;

    /**
     * Constructs a new GameMenuView with the specified controller.
     *
     * @param controller the GameMenuController responsible for handling game menu interactions
     */
    public GameMenuView(final GameMenuController controller) {
        this.controller = controller;
    }

    /**
     * Initializes the game menu view.
     */
    @Override
    public void init() {
        VBox vBox = createVBox();

        DropShadow shadow = createDropShadow();

        // Create individual buttons
        Button continueGame = createPrincessButton("Continue Game");
        Button saveGame = createPrincessButton("Save Progress");
        Button toMainMenu = createPrincessButton("Return to Main Menu");
        Button exitGame = createPrincessButton("Exit");

        // Configure buttons
        configureButton(continueGame, shadow, vBox);
        configureButton(saveGame, shadow, vBox);
        configureButton(toMainMenu, shadow, vBox);
        configureButton(exitGame, shadow, vBox);

        // Create scene
        scene = new Scene(vBox, Config.getWindowWidth(), Config.getWindowHeight());
        scene.setOnKeyPressed(((GameMenuController) controller)::keyPress);

        // Attach actions
        continueGame.setOnMouseClicked(((GameMenuController) controller)::gameContinueButton);
        saveGame.setOnMouseClicked(((GameMenuController) controller)::gameSaveButton);
        toMainMenu.setOnMouseClicked(((GameMenuController) controller)::mainMenuButton);
        exitGame.setOnMouseClicked(((GameMenuController) controller)::exitGameButton);
    }

    private VBox createVBox() {
        VBox vBox = new VBox();
        String backgroundStyle = """
            -fx-background-color: linear-gradient(to bottom, #FFE4E1, #FFC0CB);
            -fx-background-repeat: repeat;
            """;
        vBox.setStyle(backgroundStyle);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(VBOX_SPACING);
        vBox.setPadding(new javafx.geometry.Insets(VBOX_PADDING));
        return vBox;
    }

    private DropShadow createDropShadow() {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.rgb(0, 0, 0, SHADOW_OPACITY));
        shadow.setOffsetX(SHADOW_OFFSET_X);
        shadow.setOffsetY(SHADOW_OFFSET_Y);
        return shadow;
    }

    private void configureButton(final Button button, final DropShadow shadow, final VBox vBox) {
        button.setPrefHeight(BUTTON_PREF_HEIGHT);
        button.setPrefWidth(BUTTON_PREF_WIDTH);
        button.setEffect(shadow);
        button.setOnMouseEntered(_ -> button.setStyle(BUTTON_HOVER_STYLE));
        button.setOnMouseExited(_ -> button.setStyle(BUTTON_STYLE));
        vBox.getChildren().add(button);
    }


    private Button createPrincessButton(final String text) {
        Button button = new Button(text);
        button.setStyle(BUTTON_STYLE);

        // Add heart decoration to button text
        button.setGraphic(createHeartDecoration());
        button.setContentDisplay(javafx.scene.control.ContentDisplay.RIGHT);

        return button;
    }

    private javafx.scene.shape.Path createHeartDecoration() {
        javafx.scene.shape.Path heart = new javafx.scene.shape.Path();
        heart.setFill(Color.GOLD);
        heart.getElements().addAll(
                new javafx.scene.shape.MoveTo(0, 0),
                new javafx.scene.shape.CubicCurveTo(
                        HEART_CONTROL_X1, HEART_CONTROL_Y1,
                        HEART_CONTROL_X2, HEART_CONTROL_Y2,
                        HEART_FINAL_X / 2, HEART_FINAL_Y),
                new javafx.scene.shape.CubicCurveTo(
                        HEART_CONTROL_X2, HEART_CONTROL_Y2,
                        HEART_FINAL_X, HEART_CONTROL_Y2,
                        HEART_FINAL_X, HEART_FINAL_Y),
                new javafx.scene.shape.QuadCurveTo(
                        HEART_QUAD_CONTROL_X, HEART_QUAD_CONTROL_Y,
                        HEART_CONTROL_X1, HEART_CONTROL_Y1)
        );
        heart.setScaleX(HEART_SCALE);
        heart.setScaleY(HEART_SCALE);
        return heart;
    }

    @Override
    public void render() {
    }

    /**
     * Returns a string representation of this GameMenuView.
     *
     * @return the representation of the GameMenuView as a string
     */
    @Override
    public String toString() {
        return "GameMenuView{}";
    }
}
