package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view.component.game;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.beans.property.DoubleProperty;

import java.util.Objects;

/**
 * The type Hp box.
 * A GUI element that displays the character's actual lives.
 *
 * @author Melissa Shao
 * @version 2024
 */
public class HPBox {
    /**
     * The initial x position of the HP text.
     */
    private static final double INITIAL_X_POSITION = 50;
    /**
     * The initial y position of the HP text.
     */
    private static final double INITIAL_Y_POSITION = 50;
    /**
     * The Health text element.
     */
    private final Text health;

    /**
     * Instantiates a new HP box.
     *
     * @param healthProperty the health property to bind to
     */
    public HPBox(final DoubleProperty healthProperty) {
        this.health = new Text(INITIAL_X_POSITION, INITIAL_Y_POSITION,
                "HP: " + healthProperty.get());
        this.health.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        this.health.setFill(Color.WHITE);
        healthProperty.addListener((observable, oldValue, newValue) ->
            this.health.setText("HP: " + newValue.intValue()));
    }

    /**
     * Returns the health text element of this HPBox.
     *
     * @return the health text element
     */
    public Text getText() {
        return health;
    }

    /**
     * Returns a string representation of this HPBox.
     *
     * @return the representation of the HPBox as a string
     */
    @Override
    public String toString() {
        return "HPBox{" + "health=" + health + '}';
    }

    /**
     * Compares this HPBox with another object for equality.
     * Two HPBox are considered equal if they contain the same health.
     *
     * @param object the object to compare with this HPBox
     * @return true if the given object is equal to this HPBox, false otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        HPBox hpBox = (HPBox) object;
        return Objects.equals(health, hpBox.health);
    }

    /**
     * Returns the hash code of this HPBox.
     *
     * @return the hash code value of the HPBox as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(health);
    }
}
