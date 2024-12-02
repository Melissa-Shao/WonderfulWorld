package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.PlayerConfig;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.factory.ItemFactory;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.location.Location;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.location.LocationManager;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.sprite.SpriteManager;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.tile.TileMap;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.AItem;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.recovery.AEquipment;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Monster;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Player;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Portal;
import lombok.Getter;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * The GameModel class is responsible for managing the state of the game.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public class GameModel {

    /**
     * Player properties.
     */
    private static GameModel instance;
    @Getter private Player player;

    /**
     * Location properties.
     */
    @Getter private Location currentLocation;
    private LocationManager locationManager;

    /**
     * Initializes the game world, loading locations and game objects.
     *
     * @param fromSave whether to load from a saved state
     */
    public void init(final boolean fromSave) {
        System.out.println("Initializing game world...");
        JSONObject playerConfig = PlayerConfig.getPlayerConfig(fromSave);
        locationManager = new LocationManager();

        player = new Player(
                playerConfig.getString("name"),
                playerConfig.getDouble("health"),
                playerConfig.getDouble("damage"),
                playerConfig.getDouble("armor"),
                playerConfig.getDouble("damageRadius")
        );

        player.getHP().setInitialHealth(playerConfig.getDouble("initialHealth"));

        int equippedWeaponId = playerConfig.getInt("equippedWeaponId");
        if (equippedWeaponId != -1) {
            AItem item = ItemFactory.getItem(equippedWeaponId);
            if (item instanceof AEquipment) {
                player.setEquipment((AEquipment) item);
            }
        }

        int equippedArmorId = playerConfig.getInt("equippedArmorId");
        if (equippedArmorId != -1) {
            AItem item = ItemFactory.getItem(equippedArmorId);
            if (item instanceof AEquipment) {
                player.setEquipment((AEquipment) item);
            }
        }

        for (Object item : playerConfig.getJSONArray("inventory")) {
            player.getInventory().addItem(ItemFactory.getItem((int) item));
        }

        player.setPosition(
                TileMap.convertTileToPixel(playerConfig.getInt("positionX")),
                TileMap.convertTileToPixel(playerConfig.getInt("positionY"))
        );

        setLocation(playerConfig.getInt("locationId"));
        System.out.println("Game world initialized.");
    }

    /**
     * Returns the singleton instance of the GameModel.
     *
     * @return the instance of GameModel
     */
    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    /**
     * Sets the current location of the game.
     *
     * @param locationId the location id to set
     */
    public void setLocation(final int locationId) {
        if (currentLocation != null) {
            currentLocation.unsetPlayer();
        }

        currentLocation = locationManager.getLocation(locationId);
        currentLocation.init();
        currentLocation.setPlayer(player);

        System.out.println("Location set to: " + currentLocation.getName());
    }

    /**
     * Returns the current tile map.
     *
     * @return the tile map of the current location
     */
    public TileMap getTileMap() {
        return currentLocation.getTileMap();
    }

    /**
     * Returns the list of monsters in the current location.
     *
     * @return the list of monsters
     */
    public ArrayList<Monster> getMonsters() {
        return currentLocation.getMonsters();
    }

    /**
     * Returns the list of items in the current location.
     *
     * @return the list of items
     */
    public ArrayList<AItem> getItems() {
        return currentLocation.getItems();
    }

    /**
     * Returns the list of portals in the current location.
     *
     * @return the list of portals
     */
    public ArrayList<Portal> getPortals() {
        return currentLocation.getPortals();
    }

    /**
     * Returns the sprite manager for the current location.
     *
     * @return the sprite manager
     */
    public SpriteManager getSpriteManager() {
        return currentLocation.getSpriteManager();
    }
}

