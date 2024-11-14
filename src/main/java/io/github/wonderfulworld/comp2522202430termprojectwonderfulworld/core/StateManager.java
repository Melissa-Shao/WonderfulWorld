package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core;

/**
 * Manages the overall state of the game, providing methods to control global game flow.
 *
 *
 * @author Melissa Shao
 * @version 2024
 */
public final class StateManager {
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private StateManager() {
    }

    /**
     * Triggers the game-over sequence, typically indicating the player's defeat.
     */
    public static void gameOver() {
        System.out.println("Game Over! The player has died.");
    }
}
