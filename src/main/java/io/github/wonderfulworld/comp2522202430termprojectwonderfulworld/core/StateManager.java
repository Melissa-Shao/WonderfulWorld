package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller.AController;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller.MainMenuController;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller.GameMenuController;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller.InventoryController;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller.GameOverController;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller.GameController;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.GameModel;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.util.HashMap;

/**
 * Manages the overall state of the game, providing methods to control global game flow.
 *
 *
 * @author Melissa Shao, Candice Wei
 * @version 2024
 */
public final class StateManager {
    /**
     * The enumeration for GameState.
     */
    private enum GameState {
        MENU,
        GAME_MENU,
        GAME,
        INVENTORY,
        GAME_OVER
    }

    private static final HashMap<GameState, AController> STATES = new HashMap<>();
    private static AController currentController;
    private static Stage stage;
    private static GameLoop gameLoop;

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws UnsupportedOperationException if an attempt is made to instantiate the class
     */
    private StateManager() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * Init.
     * <p>
     * Initialization method.
     *
     * @param currentStage The stage.
     */
    public static void init(final Stage currentStage) {
        StateManager.stage = currentStage;

        // Init States
        STATES.put(GameState.MENU, new MainMenuController());
        STATES.put(GameState.GAME_MENU, new GameMenuController());
        STATES.put(GameState.GAME, new GameController());
        STATES.put(GameState.INVENTORY, new InventoryController());
        STATES.put(GameState.GAME_OVER, new GameOverController());

        // Init Game Loop
        StateManager.gameLoop = new GameLoop();
        // Initial State
        goToMainMenu();
        // Open and Start game
        stage.show();
    }

    /**
     * Starts the game.
     *
     * @param fromSave whether to start the game from the save
     */
    public static void startGame(final boolean fromSave) {
        currentController = STATES.get(GameState.GAME);

        // Reset all controls and views when restarting the game
        STATES.forEach((key, value) -> value.reset());
        GameModel gameModel = GameModel.getInstance();
        gameModel.init(fromSave);
        currentController.init();
        System.out.println("Game starts.");
    }

    /**
     * Continues the game.
     */
    public static void continueGame() {
        currentController = STATES.get(GameState.GAME);
        currentController.init();
        stage.setScene(currentController.getView().getScene());
        System.out.println("Game continues.");
    }

    /**
     * Goes to main menu.
     */
    public static void goToMainMenu() {
        currentController = STATES.get(GameState.MENU);
        currentController.init();
        stage.setScene(currentController.getView().getScene());
        System.out.println("Go to Main Menu.");
    }

    /**
     * Goes to game menu.
     */
    public static void goToGameMenu() {
        currentController = STATES.get(GameState.GAME_MENU);
        currentController.init();
        stage.setScene(currentController.getView().getScene());
        System.out.println("Go to Game Menu.");
    }


    /**
     * Goes to inventory.
     */
    public static void goToInventory() {
        currentController = STATES.get(GameState.INVENTORY);
        currentController.init();
        stage.setScene(currentController.getView().getScene());
        System.out.println("Go to Inventory.");
    }

    /**
     * Triggers the game-over sequence, typically indicating the player's defeat.
     */
    public static void gameOver() {
        currentController = STATES.get(GameState.GAME_OVER);
        currentController.init();
        stage.setScene(currentController.getView().getScene());
        System.out.println("Game Over! The player has died.");
    }

    /**
     * Resets the scene (like moving in between portals).
     */
    public static void resetScene() {
        stage.setScene(currentController.getView().getScene());
        System.out.println("Resetting game.");
    }

    /**
     * Exits the game.
     */
    public static void exitGame() {
        Platform.exit();
        System.exit(0);
        System.out.println("You exits the game.");
    }

    /**
     * Gets the current state.
     *
     * @return the current controller
     */
    public static AController getCurrentState() {
        return currentController;
    }

    /**
     * Returns a string representation of this StateManager.
     *
     * @return the representation of the StateManager as a string
     */
    @Override
    public String toString() {
        return "StateManager{}";
    }
}
