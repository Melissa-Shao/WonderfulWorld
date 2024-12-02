package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lombok.Getter;

import java.util.Objects;

/**
 * The Tile class represents tiles in the game.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public class Tile {
    /**
     * Tile properties.
     */
    private final Image image;
    /**
     * -- GETTER --
     * Checks whether the tile is passable.
     */
    @Getter
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

    /**
     * Compares this Tile with another object for equality.
     * Two Tile objects are considered equal if they have the same values
     * for all properties, including map dimensions and tile arrangement.
     *
     * @param o the object to compare with this Tile
     * @return true if the given object is equal to this Tile, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tile tile = (Tile) o;
        return passable == tile.passable && Objects.equals(image, tile.image);
    }

    /**
     * Returns the hash code of this Tile.
     *
     * @return the hash code value of the Tile as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(image, passable);
    }

    /**
     * Returns a string representation of this Tile.
     *
     * @return the representation of the Tile as a string
     */
    @Override
    public String toString() {
        return "Tile{" + "image=" + image + ", passable=" + passable + '}';
    }
}
