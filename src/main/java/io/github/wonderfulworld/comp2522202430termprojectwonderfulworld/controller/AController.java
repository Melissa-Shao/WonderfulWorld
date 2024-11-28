package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view.AView;
import lombok.Getter;

import java.util.Objects;

/**
 * AController is an abstract class used as a superclass for various controllers in the game.
 *
 * @author Candice Wei
 * @version 2024
 */
public abstract class AController {
    /**
     * Initializes the controllers.
     */
    protected boolean wasInitialized;

    /**
     * Refers to the view associated with this controller.
     */
    @Getter protected AView view;

    /**
     * Resets controller state.
     */
    public void reset() {
        wasInitialized = false;
    }

    /**
     * An abstract method to initialize the controllers.
     */
    public abstract void init();

    /**
     * A method is called by the main timer every frame to update the state of the controller.
     *
     * @param delta the time elapsed since the last tick (in seconds)
     */
    public abstract void tick(double delta);

    /**
     * Compares this AController with another object for equality.
     * Two AController objects are considered equal if they have the same values for all properties,
     * as well as superclass properties.
     *
     * @param o the object to compare with this AController
     * @return true if the given object is equal to this AController, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AController that = (AController) o;
        return wasInitialized == that.wasInitialized;
    }

    /**
     * Returns the hash code of this AController.
     *
     * @return the hash code value of the AController as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(wasInitialized);
    }

    /**
     * Returns a string representation of this AController.
     *
     * @return the representation of the AController as a string
     */
    @Override
    public String toString() {
        return "AController{" + "wasInitialized=" + wasInitialized + '}';
    }
}
