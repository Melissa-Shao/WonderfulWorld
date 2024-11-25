package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller.GameController;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.sprite.SpriteManager;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.tile.TileMap;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.GameModel;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.Config;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Player;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view.component.game.HPBox;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.util.Objects;

/**
 * Represents the main game view in the application.
 * This class extends AView and defines the behavior and layout
 * of the game's world representation. It includes the rendering
 * of the map, entities, and player information such as HP.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
public class GameView extends AView {
    /**
     * The tile map for the game, representing the game's world layout.
     */
    private TileMap tileMap;

    /**
     * The graphics context used to render graphics onto the canvas.
     */
    private GraphicsContext gc;

    /**
     * The root pane containing the canvas.
     */
    @Getter
    private Pane canvasRoot;

    /**
     * The sprite manager responsible for managing and rendering game entities.
     */
    private SpriteManager spriteManager;

    /**
     * Constructs a new GameView with the specified controller.
     *
     * @param controller the GameController responsible for handling game interactions
     */
    public GameView(final GameController controller) {
        this.controller = controller;
    }

    /**
     * Initializes the game view.
     */
    @Override
    public void init() {
        GameModel gameModel = GameModel.getInstance();
        tileMap = gameModel.getTileMap();
        spriteManager = gameModel.getSpriteManager();
        Player player = gameModel.getPlayer();

        // Main Pane
        Pane root = new Pane();

        // Canvas
        // Init main pane and canvas
        canvasRoot = new Pane();
        Canvas canvas = new Canvas(tileMap.getMapWidth(), tileMap.getMapHeight());
        canvasRoot.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvasRoot);

        // GUI
        BorderPane borderPane = new BorderPane();
        root.getChildren().add(borderPane);

        HPBox health = new HPBox(new javafx.beans.property.
                SimpleDoubleProperty(player.getHP().getHealth()));

        player.getHP().healthProperty().addListener((observable, oldValue, newValue) ->
                health.getText().setText("HP: " + newValue.intValue()));
        borderPane.getChildren().add(health.getText());

        // Scene Creation
        scene = new Scene(root, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);

        // Attaching Event Listeners
        scene.setOnKeyPressed(((GameController) controller)::keyPress);
        scene.setOnKeyReleased(((GameController) controller)::keyRelease);
    }

    /**
     * Renders the game view.
     */
    @Override
    public void render() {
        gc.clearRect(0, 0, tileMap.getMapWidth(), tileMap.getMapHeight());
        tileMap.render(gc);
        spriteManager.render(gc);
    }

    /**
     * Returns a string representation of this GameView.
     *
     * @return the representation of the GameView as a string
     */
    @Override
    public String toString() {
        return "GameView{" + "tileMap=" + tileMap + ", gc=" + gc
                + ", canvasRoot=" + canvasRoot + ", spriteManager=" + spriteManager + '}';
    }

    /**
     * Compares this GameView with another object for equality.
     * Two GameView objects are considered equal if they have the same states
     * and the superclass properties are also equal.
     *
     * @param object the object to compare with this GameView
     * @return true if the given object is equal to this GameView, false otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        GameView gameView = (GameView) object;
        return Objects.equals(tileMap, gameView.tileMap) && Objects.equals(gc, gameView.gc)
                && Objects.equals(canvasRoot, gameView.canvasRoot)
                && Objects.equals(spriteManager, gameView.spriteManager);
    }

    /**
     * Returns the hash code of this GameView.
     *
     * @return the hash code value of the GameView as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tileMap, gc, canvasRoot, spriteManager);
    }
}
