package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.Config;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.StateManager;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.sprite.SpriteManager;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.tile.TileMap;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.GameModel;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Monster;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Player;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Portal;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.AItem;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view.GameView;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The GameController class represents the game controller of the application.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public class GameController extends AController implements IController {
    private ArrayList<String> input = new ArrayList<>();
    private Player player;
    private TileMap tileMap;
    private ArrayList<Monster> monsters;
    private ArrayList<AItem> items;
    private ArrayList<Portal> portals;
    private SpriteManager spriteManager;
    private Pane canvasRoot;

    /**
     * Initializes the GameController.
     */
    @Override
    public void init() {
        if (wasInitialized) {
            return;
        }

        view = new GameView(this);
        view.init();

        // Links to game entitles
        GameModel gameModel = GameModel.getInstance();
        player = gameModel.getPlayer();
        monsters = gameModel.getMonsters();
        items = gameModel.getItems();
        portals = gameModel.getPortals();
        tileMap = gameModel.getTileMap();
        canvasRoot = ((GameView) view).getCanvasRoot();
        spriteManager = gameModel.getSpriteManager();
        setCameraPositionOnLoad();
        wasInitialized = true;
    }

    /**
     * Handles the click event for a key press.
     *
     * @param e the key event triggered by user input
     */
    @Override
    public void keyPress(final KeyEvent e) {
        String code = e.getCode().toString();

        // Track inputs
        if (!input.contains(code)) {
            input.add(code);
        }

        // Player attack
        if (code.equals("J")) {
            playerAttack();
        }

        // Custom ESCAPE behavior
        if (code.equals("ESCAPE")) {
            input = new ArrayList<>();
            StateManager.goToGameMenu();
        }

        // Custom Inventory behavior
        if (code.equals("I")) {
            StateManager.goToInventory();
        }
    }

    /**
     * Handles the click event for a key press.
     *
     * @param e the key event triggered by user input
     */
    @Override
    public void keyRelease(final KeyEvent e) {
        String code = e.getCode().toString();
        input.remove(code);
    }

    /**
     * Attacks the monster by player.
     *
     */
    public void playerAttack() {
        ArrayList<Monster> deadMonsters = new ArrayList<>();

        for (Monster monster : monsters) {
            if (player.intersectsAttackBox(monster)) {
                player.attack(monster);

                if (monster.isDead()) {
                    deadMonsters.add(monster);
                    break;
                }
            }
        }

        // Remove dead monsters
        for (Monster deadMonster : deadMonsters) {
            monsters.remove(deadMonster);
            spriteManager.removeSprite(deadMonster);
        }
    }

    /**
     * Updates the player Position.
     *
     *
     * @param delta the change
     */
    private void updatePlayerPosition(final double delta) {
        Rectangle2D moveBox = player.getMoveBox();
        int path = (int) (player.getSpeed() * delta);

        handleHorizontalMovement(moveBox, path);
        handleVerticalMovement(moveBox, path);
    }

    private void handleHorizontalMovement(final Rectangle2D moveBox, final int path) {
        // Handle left movement
        if (input.contains("A") && canMoveLeft(moveBox, path)) {
            player.moveLeft(path);
        }

        // Handle right movement
        if (input.contains("D") && canMoveRight(moveBox, path)) {
            player.moveRight(path);
        }
    }

    private void handleVerticalMovement(final Rectangle2D moveBox, final int path) {
        // Handle upward movement
        if (input.contains("W") && canMoveUp(moveBox, path)) {
            player.moveUp(path);
        }

        // Handle downward movement
        if (input.contains("S") && canMoveDown(moveBox, path)) {
            player.moveDown(path);
        }
    }

    private boolean canMoveLeft(final Rectangle2D moveBox, final int path) {
        if (player.getPositionX() - path <= 0) {
            return false;
        }

        int tileMinX = TileMap.convertPixelToTile(moveBox.getMinX() - path);
        int tileMinY = TileMap.convertPixelToTile(moveBox.getMinY());
        int tileMaxY = TileMap.convertPixelToTile(moveBox.getMaxY());

        return tileMap.getTile(tileMinX, tileMinY).isPassable()
                && tileMap.getTile(tileMinX, tileMaxY).isPassable();
    }

    private boolean canMoveRight(final Rectangle2D moveBox, final int path) {
        if (player.getPositionX() + player.getWidth() + path >= tileMap.getMapWidth()) {
            return false;
        }

        int tileMinY = TileMap.convertPixelToTile(moveBox.getMinY());
        int tileMaxY = TileMap.convertPixelToTile(moveBox.getMaxY());
        int tileMaxX = TileMap.convertPixelToTile(moveBox.getMaxX() + path);

        return tileMap.getTile(tileMaxX, tileMinY).isPassable()
                && tileMap.getTile(tileMaxX, tileMaxY).isPassable();
    }

    private boolean canMoveUp(final Rectangle2D moveBox, final int path) {
        if (player.getPositionY() - path <= 0) {
            return false;
        }

        int tileMinX = TileMap.convertPixelToTile(moveBox.getMinX());
        int tileMinY = TileMap.convertPixelToTile(moveBox.getMinY() - path);
        int tileMaxX = TileMap.convertPixelToTile(moveBox.getMaxX());

        return tileMap.getTile(tileMinX, tileMinY).isPassable()
                && tileMap.getTile(tileMaxX, tileMinY).isPassable();
    }

    private boolean canMoveDown(final Rectangle2D moveBox, final int path) {
        if (player.getPositionY() + player.getHeight() + path >= tileMap.getMapHeight()) {
            return false;
        }

        int tileMinX = TileMap.convertPixelToTile(moveBox.getMinX());
        int tileMaxY = TileMap.convertPixelToTile(moveBox.getMaxY() + path);
        int tileMaxX = TileMap.convertPixelToTile(moveBox.getMaxX());

        return tileMap.getTile(tileMinX, tileMaxY).isPassable()
                && tileMap.getTile(tileMaxX, tileMaxY).isPassable();
    }

    /**
     * Sets the camera position.
     */
    private void setCameraPositionOnLoad() {
        double offsetX = ((player.getPositionX() - (double) (Config.getWindowWidth() / 2))
                + player.getWidth() / 2);
        double offsetY = ((player.getPositionY() - (double) (Config.getWindowHeight() / 2))
                + player.getHeight() / 2);

        // X camera
        if (offsetX > 0 && offsetX > tileMap.getMapWidth() - Config.getWindowWidth()) {
            canvasRoot.setTranslateX((offsetX - (offsetX - (tileMap.getMapWidth()
                    - Config.getWindowWidth()))) * -1);
        }

        // Y camera
        if (offsetY > 0 && offsetY > tileMap.getMapHeight() - Config.getWindowHeight()) {
            canvasRoot.setTranslateY((offsetY - (offsetY - (tileMap.getMapHeight()
                    - Config.getWindowHeight()))) * -1);
        }
    }

    /**
     * Updates the camera position.
     */
    private void updateCameraPosition() {
        double offsetX = ((player.getPositionX() - (double) (Config.getWindowWidth() / 2))
                + player.getWidth() / 2);
        double offsetY = ((player.getPositionY() - (double) (Config.getWindowHeight() / 2))
                + player.getHeight() / 2);

        // X camera
        if (offsetX > 0 && offsetX < tileMap.getMapWidth() - Config.getWindowWidth()) {
            canvasRoot.setTranslateX(offsetX * -1);
        }

        // Y camera
        if (offsetY > 0 && offsetY < tileMap.getMapHeight() - Config.getWindowHeight()) {
            canvasRoot.setTranslateY(offsetY * -1);
        }
    }

    /**
     * Checks the intersections.
     */
    private void checkIntersections() {
        handleItemIntersections();
        handleMonsterIntersections();
        handlePortalIntersections();
    }

    private void handleItemIntersections() {
        ArrayList<AItem> takenItems = new ArrayList<>();

        for (AItem item : items) {
            if (player.intersectsMoveBox(item) && item.take(player)) {
                takenItems.add(item);
            }
        }

        // Remove Taken Items from game world
        for (AItem item : takenItems) {
            spriteManager.removeSprite(item);
            items.remove(item);
        }
    }

    private void handleMonsterIntersections() {
        for (Monster monster : monsters) {
            if (monster.intersectsRadiusViewBox(player)) {
                monster.setAim(player);
            }
        }
    }

    private void handlePortalIntersections() {
        for (Portal portal : portals) {
            if (player.intersectsMoveBox(portal)) {
                handlePortalActivation(portal);
                return;
            }
        }
    }

    private void handlePortalActivation(final Portal portal) {
        portal.activate();
        monsters.forEach(Monster::offCombat);
        wasInitialized = false;
        init();
        StateManager.resetScene();
    }

    /**
     * A method is called by the main timer every frame to update the state of the controller.
     *
     * @param delta the time elapsed since the last tick (in seconds)
     */
    @Override
    public void tick(final double delta) {
        // Update
        updatePlayerPosition(delta);

        // Camera
        updateCameraPosition();

        // Check intersections
        checkIntersections();

        // Sprite Update
        spriteManager.update(delta);

        // Render
        view.render();
    }

    /**
     * Compares this GameController with another object for equality.
     * Two GameController objects are considered equal
     * if they have the same values for all properties,
     * as well as superclass properties.
     *
     * @param o the object to compare with this GameController
     * @return true if the given object is equal to this GameController, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        GameController that = (GameController) o;
        return Objects.equals(input, that.input) && Objects.equals(player, that.player)
                && Objects.equals(tileMap, that.tileMap)
                && Objects.equals(monsters, that.monsters)
                && Objects.equals(items, that.items)
                && Objects.equals(portals, that.portals)
                && Objects.equals(spriteManager, that.spriteManager)
                && Objects.equals(canvasRoot, that.canvasRoot);
    }

    /**
     * Returns the hash code of this GameController.
     *
     * @return the hash code value of the GameController as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), input, player, tileMap,
                monsters, items, portals, spriteManager, canvasRoot);
    }

    /**
     * Returns a string representation of this GameController.
     *
     * @return the representation of the GameController as a string
     */
    @Override
    public String toString() {
        return "GameController{"
                + "input=" + input
                + ", player=" + player
                + ", tileMap=" + tileMap
                + ", monsters=" + monsters
                + ", items=" + items
                + ", portals=" + portals
                + ", spriteManager=" + spriteManager
                + ", canvasRoot=" + canvasRoot
                + '}';
    }
}
