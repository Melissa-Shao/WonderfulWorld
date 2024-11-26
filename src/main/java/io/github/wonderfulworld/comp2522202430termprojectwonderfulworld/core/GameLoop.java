package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core;

import javafx.animation.AnimationTimer;

import java.util.Objects;

/**
 * The GameLoop class represents the control of the main timer.
 */
class GameLoop extends AnimationTimer {
    // CONSTANTS
    private static final double TARGET_FRAME_RATE = 60.0;
    private static final double MAX_DELTA_TIME = 1.0 / TARGET_FRAME_RATE;
    private static final double NANOSECONDS_TO_SECONDS = 1000000000.0;

    private long lastNanoTime = System.nanoTime();

    /**
     * Handles the animation timer's update logic.
     *
     *
     * @param now the current time in nanoseconds
     */
    @Override
    public void handle(final long now) {
        double delta = (now - lastNanoTime) / NANOSECONDS_TO_SECONDS;

        // Frame rate cap
        if (delta > 1 / MAX_DELTA_TIME) {
            lastNanoTime = now;
            StateManager.getCurrentState().tick(delta);
        }
    }

    /**
     * Compares this GameLoop with another object for equality.
     * Two GameLoop objects are considered equal if they have the same values for all properties,
     * as well as superclass properties.
     *
     * @param o the object to compare with this GameLoop
     * @return true if the given object is equal to this GameLoop, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameLoop gameLoop = (GameLoop) o;
        return lastNanoTime == gameLoop.lastNanoTime;
    }

    /**
     * Returns the hash code of this GameLoop.
     *
     * @return the hash code value of the GameLoop as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(lastNanoTime);
    }

    /**
     * Returns a string representation of this GameLoop.
     *
     * @return the representation of the GameLoop as a string
     */
    @Override
    public String toString() {
        return "GameLoop{" + "lastNanoTime=" + lastNanoTime + '}';
    }
}
