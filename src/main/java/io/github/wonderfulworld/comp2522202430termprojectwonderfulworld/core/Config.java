package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core;

import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The Config class handles loading and providing access to the main application configuration.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public final class Config {
    /**
     * The JSON object storing the configuration data.
     */
    private static JSONObject jo;

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws UnsupportedOperationException if an attempt is made to instantiate the class
     */
    private Config() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * Initializes the configuration by reading from the specified file path.
     *
     * @param path the path to the configuration file
     * @throws RuntimeException if the configuration file cannot be loaded
     */
    public static void init(final String path) {
        File file = new File(path);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            jo = new JSONObject(content);
        } catch (IOException e) {
            System.err.println("Failed to load configuration file: " + path);
            throw new RuntimeException("Failed to load configuration file: " + path, e);
        }
    }

    /**
     * Gets window width.
     *
     * @return The window width.
     */
    public static int getWindowWidth() {
        return jo.getInt("windowWidth");
    }

    /**
     * Gets window height.
     *
     * @return The window height.
     */
    public static int getWindowHeight() {
        return jo.getInt("windowHeight");
    }

    /**
     * Gets tile size.
     *
     * @return The tile size.
     */
    public static int getTileSize() {
        return jo.getInt("tileSize");
    }

    /**
     * Gets window name.
     *
     * @return The window name.
     */
    public static String getWindowName() {
        return jo.getString("windowName");
    }
}
