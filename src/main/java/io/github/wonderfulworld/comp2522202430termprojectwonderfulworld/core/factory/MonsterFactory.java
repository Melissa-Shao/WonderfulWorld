package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.factory;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Monster;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The TileMapManager class represents a factory for creating Monster objects based on their IDs.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public final class MonsterFactory {
    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws UnsupportedOperationException if an attempt is made to instantiate the class
     */
    private MonsterFactory() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }
    /**
     * Gets monster.
     *
     * @param id the monster id
     * @return the monster
     * @throws RuntimeException if the monster's configuration file cannot be loaded
     *
     */
    public static Monster getMonster(final int id) {
        try {
            String content = new String(Files.readAllBytes(Path.of("config/monster/"
                    + id + "/config.json")));
            Monster monster = getMonster(content);
            monster.setImage(Path.of("config/monster/"
                    + id + "/image.png").toUri().toString());

            return monster;
        } catch (IOException e) {
            throw new RuntimeException("Error while reading monster config", e);
        }
    }

    private static Monster getMonster(final String content) {
        JSONObject config = new JSONObject(content);
        String name = config.getString("name");
        double health = config.getDouble("health");
        double damage = config.getDouble("damage");
        double damageRadius = config.getDouble("damageRadius");
        double viewingRadius = config.getDouble("viewingRadius");
        double speed = config.getDouble("speed");
        double attackSpeed = config.getDouble("attackSpeed");
        return new Monster(name, health, damage, damageRadius,
                viewingRadius, speed, attackSpeed);
    }

    /**
     * Returns a string representation of this MonsterFactory.
     *
     * @return the representation of the MonsterFactory as a string
     */
    @Override
    public String toString() {
        return "MonsterFactory{}";
    }
}
