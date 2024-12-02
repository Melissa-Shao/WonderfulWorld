package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller.MainMenuController;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.PlayerConfig;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.Config;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

import java.util.ArrayList;

/**
 * Represents the main menu view in the application.
 * This class extends AView and defines the behavior and layout
 * of the main menu, including buttons for starting, loading,
 * and exiting the game.
 *
 * @author Melissa Shao, Candice Wei
 * @version 2024
 */
public class MainMenuView extends AView {
    // CONSTANTS
    private static final int VBOX_SPACING = 20;
    private static final int BUTTON_PREF_HEIGHT = 60;
    private static final int BUTTON_PREF_WIDTH = 250;
    private static final int SHADOW_OFFSET_X = 3;
    private static final int SHADOW_OFFSET_Y = 3;
    private static final double SHADOW_OPACITY = 0.3;
    private static final double HEART_SCALE = 0.7;
    private static final int HEART_MOVE_TO_X = 0;
    private static final int HEART_MOVE_TO_Y = 0;
    private static final int HEART_CUBIC_CONTROL_X1 = 0;
    private static final int HEART_CUBIC_CONTROL_Y1 = -4;
    private static final int HEART_CUBIC_CONTROL_X2 = 4;
    private static final int HEART_CUBIC_CONTROL_Y2 = -6;
    private static final int HEART_CUBIC_CONTROL_X3 = 8;
    private static final int HEART_CUBIC_CONTROL_Y3 = 0;
    private static final int HEART_QUAD_CONTROL_X = 8;
    private static final int HEART_QUAD_CONTROL_Y = 8;
    private static final int HEART_CUBIC_FINAL_X = 16;
    private static final int HEART_CUBIC_FINAL_Y = 0;


    private static final String BACKGROUND_STYLE = """
            -fx-background-color: linear-gradient(to bottom, #FFE4E1, #FFC0CB);
            -fx-background-repeat: repeat;
            """;
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
     * Constructs a new MainMenuView with the specified controller.
     *
     * @param controller the MainMenuController responsible for handling main menu interactions
     */
    public MainMenuView(final MainMenuController controller) {
        this.controller = controller;
    }

    /**
     * Initializes the main menu view.
     */
    @Override
    public void init() {
        // Initialize VBox layout
        VBox vBox = new VBox();
        ArrayList<Button> buttons = new ArrayList<>();

        // Configure VBox properties
        vBox.setStyle(BACKGROUND_STYLE);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(VBOX_SPACING);

        // Create and configure "New Game" button
        Button newGame = createPrincessButton("New Game");
        buttons.add(newGame);

        // Create and configure "Load Game" button
        Button loadGame = createPrincessButton("Load Game");
        buttons.add(loadGame);
        if (!PlayerConfig.isExistPlayerSave()) {
            loadGame.setVisible(false);
            loadGame.setManaged(false);
        }

        // Create and configure "Exit Game" button
        Button exitGame = createPrincessButton("Exit Game");
        buttons.add(exitGame);

        // Configure buttons and add them to VBox
        configureAndAddButtonsToVBox(buttons, vBox);

        // Create the scene with the configured VBox
        scene = new Scene(vBox, Config.getWindowWidth(), Config.getWindowHeight());

        // Attach event listeners to buttons
        newGame.setOnMouseClicked(((MainMenuController) controller)::gameStartButton);
        loadGame.setOnMouseClicked(((MainMenuController) controller)::gameLoadButton);
        exitGame.setOnMouseClicked(((MainMenuController) controller)::exitGameButton);
    }

    /**
     * Creates a princess-themed styled button for the menu with hover effects.
     *
     * @param text the text displayed on the button
     * @return the configured button
     */
    private Button createPrincessButton(final String text) {
        Button button = new Button(text);
        button.setStyle(BUTTON_STYLE);

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.rgb(0, 0, 0, SHADOW_OPACITY));
        shadow.setOffsetX(SHADOW_OFFSET_X);
        shadow.setOffsetY(SHADOW_OFFSET_Y);

        button.setEffect(shadow);

        // Add heart decoration to button text
        button.setGraphic(createHeartDecoration());
        button.setContentDisplay(javafx.scene.control.ContentDisplay.RIGHT);

        // Add hover effects
        button.setOnMouseEntered(_ -> button.setStyle(BUTTON_HOVER_STYLE));
        button.setOnMouseExited(_ -> button.setStyle(BUTTON_STYLE));


        return button;
    }


    /**
     * Creates a heart-shaped decoration for buttons.
     *
     * @return the heart shape
     */
    private Path createHeartDecoration() {
        Path heart = new Path();
        heart.setFill(Color.GOLD);
        heart.getElements().addAll(
                new javafx.scene.shape.MoveTo(HEART_MOVE_TO_X, HEART_MOVE_TO_Y),
                new javafx.scene.shape.CubicCurveTo(
                        HEART_CUBIC_CONTROL_X1, HEART_CUBIC_CONTROL_Y1,
                        HEART_CUBIC_CONTROL_X2, HEART_CUBIC_CONTROL_Y2,
                        HEART_CUBIC_CONTROL_X3, HEART_CUBIC_CONTROL_Y3),
                new javafx.scene.shape.CubicCurveTo(
                        HEART_CUBIC_CONTROL_X2, HEART_CUBIC_CONTROL_Y2,
                        HEART_CUBIC_FINAL_X, HEART_CUBIC_CONTROL_Y2,
                        HEART_CUBIC_FINAL_X, HEART_CUBIC_FINAL_Y),
                new javafx.scene.shape.QuadCurveTo(
                        HEART_QUAD_CONTROL_X, HEART_QUAD_CONTROL_Y,
                        HEART_MOVE_TO_X, HEART_MOVE_TO_Y)
        );
        heart.setScaleX(HEART_SCALE);
        heart.setScaleY(HEART_SCALE);
        return heart;
    }

    /**
     * Configures and adds the buttons to the VBox layout.
     *
     * @param buttons the list of buttons to be added
     * @param vBox    the VBox layout to which buttons are added
     */
    private void configureAndAddButtonsToVBox(final ArrayList<Button> buttons, final VBox vBox) {
        for (Button button : buttons) {
            button.setPrefHeight(BUTTON_PREF_HEIGHT);
            button.setPrefWidth(BUTTON_PREF_WIDTH);
            vBox.getChildren().add(button);
        }
    }

    /**
     * Renders the main menu view.
     */
    @Override
    public void render() {
    }

    /**
     * Returns a string representation of this MainMenuView.
     *
     * @return the representation of the MainMenuView as a string
     */
    @Override
    public String toString() {
        return "MainMenuView{}";
    }
}
