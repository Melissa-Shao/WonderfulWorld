package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.location;

import java.util.HashMap;
import java.util.Objects;

/**
 * The LocationManager class represents the management of creation and caching of the locations.
 *
 *
 * @author Candice Wei
 * @version 2024
 */
public class LocationManager {
    /**
     * The locations in the game.
     */
    private final HashMap<Integer, Location> locations = new HashMap<>();

    /**
     * Gets location.
     *
     * @param locationId the location id
     * @return the location
     */
    public Location getLocation(final int locationId) {
        if (locations.containsKey(locationId)) {
            return locations.get(locationId);
        }

        locations.put(locationId, new Location(locationId));
        return locations.get(locationId);
    }

    /**
     * Compares this LocationManager with another object for equality.
     * Two LocationManager objects are considered equal
     * if they have the same values for all properties,
     * as well as superclass properties.
     *
     * @param o the object to compare with this LocationManager
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
        LocationManager that = (LocationManager) o;
        return Objects.equals(locations, that.locations);
    }

    /**
     * Returns a string representation of this LocationManager.
     *
     * @return the representation of the LocationManager as a string
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(locations);
    }

    /**
     * Returns a string representation of this LocationManager.
     *
     * @return the representation of the LocationManager as a string
     */
    @Override
    public String toString() {
        return "LocationManager{" + "locations=" + locations + '}';
    }
}
