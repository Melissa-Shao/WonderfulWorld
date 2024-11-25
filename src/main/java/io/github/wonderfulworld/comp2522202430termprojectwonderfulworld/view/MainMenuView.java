package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller.MainMenuController;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.PlayerConfig;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.Config;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * Represents the main menu view in the application.
 * This class extends AView and defines the behavior and layout
 * of the main menu, including buttons for starting, loading,
 * and exiting the game.
 *
 * @author Melissa Shao
 * @version 2024
 */
public class MainMenuView extends AView {
    private static final int VBOX_SPACING = 16; // Spacing between buttons
    private static final String BACKGROUND_COLOR = "#232933"; // Background color for VBox

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
        vBox.setStyle("-fx-background-color: " + BACKGROUND_COLOR + ";");
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(VBOX_SPACING);

        // Create and configure "New Game" button
        Button newGame = new Button();
        newGame.setText("New Game"); buttons.add(newGame);

        // Create and configure "Load Game" button
        Button loadGame = new Button();
        loadGame.setText("Load Game");
        buttons.add(loadGame);
        if (!PlayerConfig.isExistPlayerSave()) {
            loadGame.setVisible(false);
            loadGame.setManaged(false);
        }

        // Create and configure "Exit Game" button
        Button exitGame = new Button();
        buttons.add(exitGame);
        exitGame.setText("Exit Game");

        // Configure buttons and add them to VBox
        configureAndAddButtonsToVBox(buttons, vBox);

        // Create the scene with the configured VBox
        scene = new Scene(vBox, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);

        // Attach event listeners to buttons
        newGame.setOnMouseClicked(((MainMenuController) controller)::gameStartButton);
        loadGame.setOnMouseClicked(((MainMenuController) controller)::gameLoadButton);
        exitGame.setOnMouseClicked(((MainMenuController) controller)::exitGameButton);
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
