package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.tile;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.Config;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * The TileMap class represents the tile map of the game.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public class TileMap {
    /**
     * Tile properties.
     */
    private final Tile[][] tiles;

    /**
     * Map properties.
     */
    @Getter private final int mapWidth;
    @Getter  private final int mapHeight;

    /**
     * Constructs a new TileMap with the specified dimensions and tile size.
     *
     * @param width    the number of tiles in the width of the map
     * @param height   the number of tiles in the height of the map
     * @param tileSize the size of each tile in pixels
     */
    public TileMap(final int width, final int height, final int tileSize) {
        this.tiles = new Tile[width][height];
        this.mapWidth = getWidth() * tileSize;
        this.mapHeight = getHeight() * tileSize;
    }

    /**
     * Gets the width of the map in tiles.
     *
     * @return the number of tiles in the width of the map
     */
    public int getWidth() {
        return tiles.length;
    }

    /**
     * Gets the height of the map in tiles.
     *
     * @return the number of tiles in the height of the map
     */
    public int getHeight() {
        return tiles[0].length;
    }

    /**
     * Retrieves a specific tile by its coordinates.
     *
     * @param x     the x-coordinate of the tile
     * @param y     the y-coordinate of the tile
     * @return      the tile at the specified coordinates, or null if out of bounds
     */
    public Tile getTile(final int x, final int y) {
        if (x < 0 || x >= getWidth()
                || y < 0 || y >= getHeight()) {
            return null;
        } else {
            return tiles[x][y];
        }
    }

    /**
     * Sets a specific tile at the given coordinates.
     *
     * @param x    the x-coordinate where the tile is to be placed
     * @param y    the y-coordinate where the tile is to be placed
     * @param tile the tile to set at the specified coordinates
     */
    public void setTile(final int x, final int y, final Tile tile) {
        tiles[x][y] = tile;
    }

    /**
     * Renders the entire tile map on the screen.
     *
     * @param gc the graphics context used for rendering the tiles
     */
    public void render(final GraphicsContext gc) {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Tile tile = getTile(j, i);
                if (tile != null) {
                    tile.render(gc, convertTileToPixel(j), convertTileToPixel(i));
                }
            }
        }
    }

    /**
     * Converts tile coordinates to pixel coordinates.
     *
     * @param coordinate the tile coordinate
     * @return the corresponding pixel coordinate
     */
    public static int convertTileToPixel(final int coordinate) {
        return coordinate * Config.getTileSize();
    }

    /**
     * Converts pixel coordinates to tile coordinates.
     *
     * @param coordinate the pixel coordinate
     * @return the corresponding tile coordinate
     */
    public static int convertPixelToTile(final double coordinate) {
        return (int) (coordinate / Config.getTileSize());
    }

    /**
     * Compares this TileMap with another object for equality.
     * Two TileMap objects are considered equal if they have the same values
     * for all properties, including map dimensions and tile arrangement.
     *
     * @param o the object to compare with this TileMap
     * @return true if the given object is equal to this TileMap, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TileMap tileMap = (TileMap) o;
        return mapWidth == tileMap.mapWidth && mapHeight == tileMap.mapHeight
                && Objects.deepEquals(tiles, tileMap.tiles);
    }

    /**
     * Returns the hash code of this TileMap.
     *
     * @return the hash code value of the TileMap as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(tiles), mapWidth, mapHeight);
    }

    /**
     * Returns a string representation of this TileMap.
     *
     * @return the representation of the TileMap as a string
     */
    @Override
    public String toString() {
        return "TileMap{" + "tiles=" + Arrays.toString(tiles) + ", mapWidth=" + mapWidth
                + ", mapHeight=" + mapHeight + '}';
    }
}
