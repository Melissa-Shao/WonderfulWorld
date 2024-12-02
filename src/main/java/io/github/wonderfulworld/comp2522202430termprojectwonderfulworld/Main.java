package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld;

/**
 * The Main class serves as the entry point for the Wonderful World game application.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public final class Main {
    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws UnsupportedOperationException if an attempt is made to instantiate the class
     */
    private Main() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * The main method serves as the starting point of the game application.
     *
     *
     * @param args the command-line arguments passed to the application
     */
    public static void main(final String[] args) {
        Game.launch(Game.class, args);
    }

    /**
     * Returns a string representation of this Main.
     *
     * @return the representation of the Main as a string
     */
    @Override
    public String toString() {
        return "Main{}";
    }
}
