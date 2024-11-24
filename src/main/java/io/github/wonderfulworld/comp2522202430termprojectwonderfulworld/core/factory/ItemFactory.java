package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.factory;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.AItem;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.recovery.HealthBottle;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment.Armor;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment.Weapon;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The ItemFactory class represents a factory for creating items based on their IDs.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public final class ItemFactory {
    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws UnsupportedOperationException if an attempt is made to instantiate the class
     */
    private ItemFactory() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }
    /**
     * Retrieves an item based on its ID.
     *
     * @param id the unique identifier of the item
     * @return AItem corresponding to the given ID
     * @throws RuntimeException if the item's configuration file cannot be loaded
     */
    public static AItem getItem(final int id) {
        try {
            // Read item configuration from the JSON file
            String content = new String(Files.readAllBytes(Path.of("config/item/"
                    + id + "/config.json")));
            JSONObject config = new JSONObject(content);

            // Determine the item type and create the corresponding object
            String itemType = config.getString("type");
            return switch (itemType) {
                case "bottle" -> createBottle(config);
                case "weapon" -> createWeapon(config);
                case "armor" -> createArmor(config);
                default -> null;
            };
        } catch (IOException e) {
            throw new RuntimeException("Error while reading item config", e);
        }
    }

    /**
     * Creates bottle.
     *
     * @param config the health bottle config
     * @return a bottle
     */
    private static AItem createBottle(final JSONObject config) {
        HealthBottle healthBottle = new HealthBottle(
                config.getInt("id"),
                config.getString("name"),
                config.getDouble("health"));
        healthBottle.setImage(Path.of("config/item/" + config.getInt("id")
                + "/image.png").toUri().toString());
        return healthBottle;
    }

    /**
     * Creates weapon.
     *
     * @param config the weapon config
     * @return a weapon
     */
    private static AItem createWeapon(final JSONObject config) {
        Weapon weapon = new Weapon(
                config.getInt("id"),
                config.getString("name"),
                config.getDouble("damage"),
                config.getDouble("radius"));
        weapon.setImage(Path.of("config/item/" + config.getInt("id")
                + "/image.png").toUri().toString());
        return weapon;
    }

    /**
     * Creates armor.
     *
     * @param config the armor config
     * @return an armor
     */
    private static AItem createArmor(final JSONObject config) {
        Armor armor = new Armor(
                config.getInt("id"),
                config.getString("name"),
                config.getDouble("armor"));
        armor.setImage(Path.of("config/item/" + config.getInt("id")
                + "/image.png").toUri().toString());
        return armor;
    }

    /**
     * Returns a string representation of this ItemFactory.
     *
     * @return the representation of the ItemFactory as a string
     */
    @Override
    public String toString() {
        return "ItemFactory{}";
    }
}
