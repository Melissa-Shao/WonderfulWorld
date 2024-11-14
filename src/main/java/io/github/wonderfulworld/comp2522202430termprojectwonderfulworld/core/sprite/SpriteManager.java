package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.sprite;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * Manages all sprites in a location, including updating their state.
 * This class handles adding, removing, and updating sprites, ensuring
 * they are displayed in the correct order based on their position.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
public class SpriteManager {

    /**
     * The list of sprites managed by this class.
     */
    private final ArrayList<ASprite> sprites;

    /**
     * Constructs a new SpriteManager.
     */
    public SpriteManager() {
        sprites = new ArrayList<>();
    }

    /**
     * Updates all sprites in the manager.
     * This method sorts the sprites based on their Y-coordinate, so that sprites
     * with lower Y-coordinates are managed behind those with higher Y-coordinates.
     *
     * @param delta the time between the last frame and the current one
     */
    public void update(final double delta) {
        // Sort sprites to manage those with higher Y-coordinates on top
        sprites.sort(Comparator.comparing(ASprite::getPositionY));

        // Updated each sprite with the given time delta
        for (ASprite sprite : sprites) {
            sprite.update(delta);
        }
    }

    /**
     * Adds a sprite to the manager.
     *
     * @param sprite the sprite to be added
     */
    public void addSprite(final ASprite sprite) {
        sprites.add(sprite);
    }

    /**
     * Removes a sprite from the manager.
     *
     * @param sprite the sprite to be removed
     */
    public void removeSprite(final ASprite sprite) {
        sprites.remove(sprite);
    }

    /**
     * Returns a string representation of this SpriteManager.
     *
     * @return the representation of the SpriteManager as a string
     */
    @Override
    public String toString() {
        return "SpriteManager{" + "sprites=" + sprites + '}';
    }

    /**
     * Compares this SpriteManager with another object for equality.
     * Two SpriteManagers are considered equal if they contain the same sprites.
     *
     * @param object the object to compare with this SpriteManager
     * @return true if the given object is equal to this SpriteManager, false otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        SpriteManager that = (SpriteManager) object;
        return Objects.equals(sprites, that.sprites);
    }

    /**
     * Returns the hash code of this SpriteManager.
     *
     * @return the hash code value of the SpriteManager as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(sprites);
    }
}
