package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.location;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.factory.ItemFactory;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.factory.MonsterFactory;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.factory.PortalFactory;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.sprite.SpriteManager;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.tile.TileMap;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.tile.TileMapManager;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.AItem;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Monster;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Player;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Portal;
import lombok.Getter;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The Location class represents a specific location.
 *
 * @author Candice Wei
 * @version 2024
 */
public class Location {
    /**
     * Constants.
     */
    private static final int TILE_SIZE = 64;
    /**
     * The config.
     */
    private final JSONObject config;

    /**
     * Location properties.
     */
    @Getter private final int locationId;
    @Getter private final String name;
    private boolean wasInitialized;

    /**
     * The Player.
     */
    @Getter private Player player;

    /**
     * The Monster.
     */
    @Getter private final ArrayList<Monster> monsters = new ArrayList<>();

    /**
     * The items.
     */
    @Getter private final ArrayList<AItem> items = new ArrayList<>();

    /**
     * The portals.
     */
    @Getter private final ArrayList<Portal> portals = new ArrayList<>();

    /**
     * The tile map.
     */
    @Getter private TileMap tileMap;

    /**
     * The sprite manager.
     */
    @Getter private SpriteManager spriteManager;


    /**
     * Instantiates a new location.
     *
     * @param locationId the location id
     * @throws RuntimeException if an error occurs while saving the file
     */
    public Location(final int locationId) {
        this.locationId = locationId;

        try {
            String content = new String(Files.readAllBytes(Path.of("config/location/"
                    + locationId + "/config.json")));
            config = new JSONObject(content);
        } catch (IOException e) {
            System.err.println("Error while reading location config: " + e.getMessage());
            throw new RuntimeException("Error while reading location config", e);
        }

        this.name = config.getString("name");
        System.out.println("Location \"" + name + "\" was created.");
    }

    /**
     * Initializes the location.
     */
    public void init() {
        if (wasInitialized) {
            return;
        }
        spriteManager = new SpriteManager();
        tileMap = TileMapManager.createTileMap("config/location/" + locationId
                + "/map.txt", TILE_SIZE);
        for (Object monsterConfig : config.getJSONArray("monsters")) {
            Monster monster = MonsterFactory.getMonster(((JSONObject) monsterConfig).getInt("id"));
            monster.setPosition(
                    TileMap.convertTileToPixel(((JSONObject) monsterConfig).getInt("positionX")),
                    TileMap.convertTileToPixel(((JSONObject) monsterConfig).getInt("positionY")));
            monsters.add(monster);
            spriteManager.addSprite(monster);
        }
        for (Object itemConfig : config.getJSONArray("items")) {
            AItem item = ItemFactory.getItem(((JSONObject) itemConfig).getInt("id"));
            assert item != null;
            item.setPosition(
                    TileMap.convertTileToPixel(((JSONObject) itemConfig).getInt("positionX")),
                    TileMap.convertTileToPixel(((JSONObject) itemConfig).getInt("positionY")));
            items.add(item);
            spriteManager.addSprite(item);
        }

        for (Object portalConfig : config.getJSONArray("portals")) {
            Portal portal = PortalFactory.getPortal(((JSONObject) portalConfig).getInt("id"));
            portal.setPosition(
                    TileMap.convertTileToPixel(((JSONObject) portalConfig).getInt("positionX")),
                    TileMap.convertTileToPixel(((JSONObject) portalConfig).getInt("positionY")));
            portals.add(portal);
            spriteManager.addSprite(portal);
        }
        wasInitialized = true;
        System.out.println("Location \"" + name + "\" was initialized.");
    }

    /**
     * Sets the player.
     *
     * @param player the player
     */
    public void setPlayer(final Player player) {
        this.player = player;
        spriteManager.addSprite(player);
    }

    /**
     * Unsets player.
     */
    public void unsetPlayer() {
        spriteManager.removeSprite(player);
        this.player = null;
    }

    /**
     * Compares this Location with another object for equality.
     * Two Location objects are considered equal if they have the same values for all properties,
     * as well as superclass properties.
     *
     * @param o the object to compare with this Location
     * @return true if the given object is equal to this Location, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return locationId == location.locationId && wasInitialized == location.wasInitialized
                && Objects.equals(config, location.config)
                && Objects.equals(name, location.name)
                && Objects.equals(player, location.player)
                && Objects.equals(monsters, location.monsters)
                && Objects.equals(items, location.items)
                && Objects.equals(portals, location.portals)
                && Objects.equals(tileMap, location.tileMap)
                && Objects.equals(spriteManager, location.spriteManager);
    }

    /**
     * Returns a string representation of this Location.
     *
     * @return the representation of the Location as a string
     */
    @Override
    public int hashCode() {
        return Objects.hash(config, locationId, name, wasInitialized, player, monsters,
                items, portals, tileMap, spriteManager);
    }

    /**
     * Returns a string representation of this Location.
     *
     * @return the representation of the Location as a string
     */
    @Override
    public String toString() {
        return "Location{"
                + "config=" + config
                + ", locationId=" + locationId
                + ", name='" + name + '\''
                + ", wasInitialized=" + wasInitialized
                + ", player=" + player
                + ", monsters=" + monsters
                + ", items=" + items
                + ", portals=" + portals
                + ", tileMap=" + tileMap
                + ", spriteManager=" + spriteManager + '}';
    }
}
