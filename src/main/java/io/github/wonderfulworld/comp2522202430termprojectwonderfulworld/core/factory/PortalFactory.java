package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.factory;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Portal;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The ItemFactory class represents a factory for creating items based on their IDs.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public final class PortalFactory {
    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws UnsupportedOperationException if an attempt is made to instantiate the class
     */
    private PortalFactory() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }
    /**
     * Gets portal.
     *
     * @param id the portal id
     * @return the portal
     * @throws RuntimeException if an error occurs while saving the file
     */
    public static Portal getPortal(final int id) {
        try {
            String content = new String(Files.readAllBytes(Path.of("config/portal/"
                    + id + "/config.json")));
            JSONObject config = new JSONObject(content);

            // Creating Portal
            Portal portal = new Portal(
                    id,
                    config.getInt("locationId"),
                    config.getInt("playerX"),
                    config.getInt("playerY")
            );
            portal.setImage(Path.of("config/portal/" + id + "/image.png").toUri().toString());

            return portal;
        } catch (IOException e) {
            throw new RuntimeException("Error while reading portal config", e);
        }
    }

    /**
     * Returns a string representation of this PortalFactory.
     *
     * @return the representation of the PortalFactory as a string
     */
    @Override
    public String toString() {
        return "PortalFactory{}";
    }
}


