package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.sprite.ASprite;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.tile.TileMap;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.GameModel;
import lombok.Getter;

import java.util.Objects;

/**
 * The Portal class represents a specific portal in the game.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public class Portal extends ASprite {

    /**
     * Portal properties.
     */
    @Getter private final int portalId;
    @Getter  private final int locationId;

    /**
     * Player position properties.
     */
    private final int playerX;
    private final int playerY;

    /**
     * Constructs a new Portal with the specified properties.
     *
     * @param portalId   the unique identifier for the portal
     * @param locationId the unique identifier for the associated location
     * @param playerX    the x-coordinate of the player after activation
     * @param playerY    the y-coordinate of the player after activation
     */
    public Portal(final int portalId, final int locationId, final int playerX, final int playerY) {
        this.portalId = portalId;
        this.locationId = locationId;
        this.playerX = playerX;
        this.playerY = playerY;
    }

    /**
     * Activates the portal, and transports the player to next map.
     */
    public void activate() {
        GameModel gameModel = GameModel.getInstance();
        Player player = gameModel.getPlayer();
        gameModel.setLocation(locationId);
        player.setPosition(
                TileMap.convertTileToPixel(playerX),
                TileMap.convertTileToPixel(playerY)
        );
    }

    /**
     * Compares this Portal with another object for equality.
     * Two Portal objects are considered equal if they have the same values for all properties,
     * as well as superclass properties.
     *
     * @param o the object to compare with this Portal
     * @return true if the given object is equal to this Portal, false otherwise
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
        Portal portal = (Portal) o;
        return portalId == portal.portalId && locationId == portal.locationId
                && playerX == portal.playerX && playerY == portal.playerY;
    }

    /**
     * Returns the hash code of this Portal.
     *
     * @return the hash code value of the Portal as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), portalId, locationId, playerX, playerY);
    }

    /**
     * Returns a string representation of this Portal.
     *
     * @return the representation of the Portal as a string
     */
    @Override
    public String toString() {
        return "Portal{"
                + "portalId=" + portalId
                + ", locationId=" + locationId
                + ", playerX=" + playerX
                + ", playerY=" + playerY + '}';
    }
}
