package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;
import lombok.Getter;
import java.util.Objects;

/**
 * ASprite is an abstract class used as a superclass for various sprite entities in the game.
 * It provides basic properties such as position, dimensions, status, and speed for sprite objects.
 * Each sprite has attributes like position, width, height,
 * and status indicators for health and attacks.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
@Getter
public abstract class ASprite {

    /**
     * The X coordinate of the sprite's position.
     */
    protected double positionX;

    /**
     * The Y coordinate of the sprite's position.
     */
    protected double positionY;

    /**
     * The width of the sprite.
     */
    protected double width;

    /**
     * The height of the sprite.
     */
    protected double height;

    /**
     * Indicates if the sprite is dead.
     */
    protected boolean isDead;

    /**
     * The time of the last attack.
     */
    protected double lastAttack;

    /**
     * The movement speed of the sprite.
     */
    protected double speed;

    /**
     * The attack speed of the sprite.
     */
    protected double attackSpeed;
    /**
     * The image of the sprite.
     */
    protected Image image;

    /**
     * Constructs a new ASprite object with initial position set to (0, 0) and alive status.
     *
     */
    public ASprite() {
        this.positionX = 0;
        this.positionY = 0;
        this.isDead = false;
    }

    /**
     * Sets the position of the sprite.
     *
     * @param x the X coordinate to set
     * @param y the Y coordinate to set
     */
    public void setPosition(final double x, final double y) {
        this.positionX = x;
        this.positionY = y;
    }

    /**
     * Sets image.
     *
     * @param image The image.
     */
    public void setImage(final Image image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
    }

    /**
     * Sets image.
     *
     * @param filename The filename.
     */
    public void setImage(final String filename) {
        Image newImage = new Image(filename);
        setImage(newImage);
    }

    /**
     * Updates the sprite's state.
     * This method can be overridden by subclasses to implement specific behavior during updates.
     *
     * @param delta the time since the last update call
     */
    public void update(final double delta) {
    }

    /**
     * Checks if this sprite intersects with another sprite's collision box.
     *
     * @param s the sprite to check for intersection with the current sprite
     * @return True if the collision box of the two sprites intersects, false otherwise.
     */
    public boolean intersectsCollisionBox(final ASprite s) {
        return s.getCollisionBox().intersects(this.getCollisionBox());
    }

    /**
     * Returns the collision box of the sprite as a Rectangle2D object.
     *
     * @return A Rectangle2D object representing the collision box of the sprite.
     */
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    /**
     * Returns a string representation of this ASprite object.
     *
     * @return the representation of the ASprite as a string
     */
    @Override
    public String toString() {
        return "ASprite{"
                + "positionX=" + positionX
                + ", positionY=" + positionY
                + ", width=" + width
                + ", height=" + height
                + ", isDead=" + isDead
                + ", lastAttack=" + lastAttack
                + ", speed=" + speed
                + ", attackSpeed=" + attackSpeed
                + "image=" + image
                + '}';
    }

    /**
     * Compares this ASprite with another object for equality.
     * Two ASprites are considered equal if they have the same position, size,
     * status, last attack time, speed, and attack speed.
     *
     * @param object the object to compare with this ASprite
     * @return true if the given object is equal to this ASprite, false otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ASprite aSprite = (ASprite) object;
        return Double.compare(positionX, aSprite.positionX) == 0
                && Double.compare(positionY, aSprite.positionY) == 0
                && Double.compare(width, aSprite.width) == 0
                && Double.compare(height, aSprite.height) == 0
                && isDead == aSprite.isDead && Double.compare(lastAttack, aSprite.lastAttack) == 0
                && Double.compare(speed, aSprite.speed) == 0
                && Double.compare(attackSpeed, aSprite.attackSpeed) == 0
                && Objects.equals(image, aSprite.image);
    }

    /**
     * Returns the hash code of this ASprite.
     *
     * @return the hash code value of the ASprite as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY, width,
                height, isDead, lastAttack, speed, attackSpeed, image);
    }

    /**
     * Render.
     * <p>
     * The render method is called every frame. Serves for drawing sprites.
     *
     * @param gc The graphics context of the canvas.
     */
    public void render(final GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }
}
