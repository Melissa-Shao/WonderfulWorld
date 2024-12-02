package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.tile.TileMap;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.GameModel;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.AItem;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The PlayerConfig class provides utilities for loading and saving player configuration.
 *
 * @author Candice Wei
 * @version 2024
 */
public final class PlayerConfig {
    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws UnsupportedOperationException if an attempt is made to instantiate the class
     */
    private PlayerConfig() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }
    /**
     * Loads the player configuration.
     *
     * @param fromSave whether to load the configuration from a saved file
     * @return a JSONObject representing the player configuration
     * @throws RuntimeException if an error occurs while reading the file
     */
    public static JSONObject getPlayerConfig(final boolean fromSave) {
        String path;
        if (fromSave) {
            path = "save/player.json";
        } else {
            path = "config/player.json";
        }

        try {
            String content = new String(Files.readAllBytes(Path.of(path)));
            return new JSONObject(content);
        } catch (IOException e) {
            System.err.println("Error while reading player config: " + e.getMessage());
            throw new RuntimeException("Error while reading player config", e);
        }
    }

    /**
     * Saves the current player configuration.
     *
     * @throws RuntimeException if an error occurs while saving the file
     */
    public static void savePlayerConfig() {
        JSONObject playerConfig = createBasicPlayerConfig();
        addEquipmentConfig(playerConfig);
        addInventoryConfig(playerConfig);
        writeConfigToFile(playerConfig);
    }

    /**
     * Creates the basic player configuration.
     *
     * @return JSONObject containing basic player stats and position
     */
    private static JSONObject createBasicPlayerConfig() {
        JSONObject playerConfig = new JSONObject();
        GameModel gameModel = GameModel.getInstance();
        Player player = gameModel.getPlayer();

        playerConfig.put("name", player.getName());
        playerConfig.put("initialHealth", player.getHP().getInitialHealth());
        playerConfig.put("health", player.getHealth());
        playerConfig.put("damage", player.getBasicDamage());
        playerConfig.put("armor", player.getBasicArmor());
        playerConfig.put("damageRadius", player.getBasicDamageRadius());
        playerConfig.put("locationId", gameModel.getCurrentLocation().getLocationId());
        playerConfig.put("positionX", TileMap.convertPixelToTile(player.getPositionX()));
        playerConfig.put("positionY", TileMap.convertPixelToTile(player.getPositionY()));

        return playerConfig;
    }

    /**
     * Adds equipment configuration to the player config.
     *
     * @param playerConfig the config object to add to
     */
    private static void addEquipmentConfig(final JSONObject playerConfig) {
        Player player = GameModel.getInstance().getPlayer();

        AItem currentWeapon = player.getCurrentWeapon();
        if (currentWeapon != null) {
            playerConfig.put("equippedWeaponId", currentWeapon.getId());
        } else {
            playerConfig.put("equippedWeaponId", -1);
        }

        AItem currentArmor = player.getCurrentArmor();
        if (currentArmor != null) {
            playerConfig.put("equippedArmorId", currentArmor.getId());
        } else {
            playerConfig.put("equippedArmorId", -1);
        }
    }

    /**
     * Adds inventory configuration to the player config.
     *
     * @param playerConfig the config object to add to
     */
    private static void addInventoryConfig(final JSONObject playerConfig) {
        Player player = GameModel.getInstance().getPlayer();
        JSONArray inventory = new JSONArray();

        for (AItem item : player.getInventory().getItems()) {
            inventory.put(item.getId());
        }
        playerConfig.put("inventory", inventory);
    }

    /**
     * Writes the configuration to file.
     *
     * @param playerConfig the config to write
     * @throws RuntimeException if an error occurs while writing the file
     */
    private static void writeConfigToFile(final JSONObject playerConfig) {
        Path path = Path.of("save/player.json");
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            Files.write(path, playerConfig.toString().getBytes());
        } catch (IOException e) {
            System.err.println("Error while saving player config: " + e.getMessage());
            throw new RuntimeException("Error while saving player config", e);
        }
    }

    /**
     * Checks if a saved player configuration exists.
     *
     * @return true if a saved player configuration exists, otherwise false
     */
    public static boolean isExistPlayerSave() {
        return Path.of("save/player.json").toFile().exists();
    }

    /**
     * Returns a string representation of this PlayerConfig object.
     *
     * @return the representation of the PlayerConfig as a string
     */
    @Override
    public String toString() {
        return "PlayerConfig{}";
    }
}
