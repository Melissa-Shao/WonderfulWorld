package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lombok.Getter;

/**
 * The Tile class represents tiles in the game.
 * Each tile has an associated image for rendering to indicate whether entities
 * can move through it.
 *
 * @author Candice Wei
 * @version 2024
 */
public class Tile {
    /**
     * Tile properties.
     */
    @Getter
    private final Image image;
    /**
     * -- GETTER --
     * Checks whether the tile is passable.
     */
    private final boolean passable;

    /**
     * Constructs a new Tile with the specified image and whether it is passable.
     *
     * @param imagePath the file path of the image to represent the tile
     * @param passable  true if the tile is passable; false otherwise
     */
    public Tile(final String imagePath, final boolean passable) {
        this.passable = passable;
        this.image = new Image(imagePath);
    }

    /**
     * Renders the tile on the screen at the specified coordinates.
     *
     * @param gc the graphics context used for rending
     * @param x  x-coordinate of the tile
     * @param y  y-coordinate of the tile
     */
    public void render(final GraphicsContext gc, final double x, final double y) {
        gc.drawImage(image, x, y);
    }
}
