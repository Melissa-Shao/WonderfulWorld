package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.tile;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The TileMapManager class represents the management of creation and caching of tile maps.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public final class TileMapManager {
    /**
     * A map of cashed tiles, identified by their IDs.
     */
    private static final HashMap<String, Tile> CACHED_TILES = new HashMap<>();

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws UnsupportedOperationException if an attempt is made to instantiate the class
     */
    private TileMapManager() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * Creates a TileMap object by reading a map file and associating characters
     * in the file with Tile objects.
     *
     * @param filename  the name of the map file
     * @param tileSize  the size of each tile in pixels
     * @return a TileMap
     * @throws RuntimeException if the map file cannot be loaded
     */
    public static TileMap createTileMap(final String filename, final int tileSize) {
        int mapWidth = 0;
        int mapHeight;
        ArrayList<String> lines = new ArrayList<>();

        // Map Parsing
        try {
            Reader fileReader = new FileReader(filename);
            BufferedReader br = new BufferedReader(fileReader);

            String fileLine;
            while ((fileLine = br.readLine()) != null) {
                if (fileLine.length() > mapWidth) {
                    mapWidth = fileLine.length();
                }
                lines.add(fileLine);
            }

            br.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        mapHeight = lines.size();
        TileMap tileMap = new TileMap(mapWidth, mapHeight, tileSize);
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                char c = lines.get(i).charAt(j);
                tileMap.setTile(j, i, getTile(String.valueOf(c)));
            }
        }

        return tileMap;
    }

    /**
     * Retrieves or creates a Tile based on the given ID.
     *
     * @param id the ID of the tile
     * @return Tile associated with the specified ID
     * @throws RuntimeException if the tile configuration or image cannot be loaded
     */
    private static Tile getTile(final String id) {
        if (CACHED_TILES.containsKey(id)) {
            return CACHED_TILES.get(id);
        }

        try {
            String content = new String(Files.readAllBytes(Path.of("config/tile/"
                    + id + "/config.json")));
            JSONObject config = new JSONObject(content);

            Tile tile = new Tile(Path.of("config/tile/" + id + "/image.png").toUri().toString(),
                    config.getBoolean("passable"));
            CACHED_TILES.put(id, tile);

            return tile;

        } catch (IOException e) {
            throw new RuntimeException("Tile not found: " + id);
        }
    }

    /**
     * Returns a string representation of this TileMapManager.
     *
     * @return the representation of the TileMapManager as a string
     */
    @Override
    public String toString() {
        return "TileMapManager{}";
    }
}
