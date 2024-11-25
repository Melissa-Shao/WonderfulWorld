package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller.AController;
import javafx.scene.Scene;
import lombok.Getter;
import java.util.Objects;

/**
 * Represents the base class for all views in the application.
 * This abstract class defines the structure and behavior that all
 * specific views must follow, ensuring consistency in the view layer.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
public abstract class AView {
    /**
     * The controller associated with this view.
     */
    protected AController controller;
    /**
     * The JavaFX scene representing this view.
     *
     */
    @Getter protected Scene scene;

    /**
     * Initializes the view.
     */
    public abstract void init();

    /**
     * Renders the view.
     */
    public abstract void render();

    /**
     * Returns a string representation of this AView.
     *
     * @return the representation of the AView as a string
     */
    @Override
    public String toString() {
        return "AView{" + "controller=" + controller + ", scene=" + scene + '}';
    }

    /**
     * Compares this AView with another object for equality.
     * Two HPBox are considered equal if they contain the same controller and scene.
     *
     * @param object the object to compare with this AView
     * @return true if the given object is equal to this AView, false otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AView aView = (AView) object;
        return Objects.equals(controller, aView.controller) && Objects.equals(scene, aView.scene);
    }

    /**
     * Returns the hash code of this AView.
     *
     * @return the hash code value of the AView as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(controller, scene);
    }
}
