package de.itsgraphax.grphxLib.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.Vector;

/**
 * A set of utilities for working with Locations and Vectors
 */
public class LocationUtils {
    /**
     * Converts a vector to a location
     * @param world The world of the Location
     * @param vector Holds the coordinates
     * @return A built Location out of the world and vector
     */
    public static Location vectorToLocation(World world, Vector vector) {
        return new Location(world, vector.getX(), vector.getY(), vector.getZ());
    }

    /**
     * Converts a location to a vector
     * @param location The location where the coordinates get pulled out
     * @return A vector out of the location
     */
    public static Vector locationToVector(Location location) {
        return new Vector(location.getX(), location.getY(), location.getZ());
    }

    /**
     * Gets a vector from a ConfigurationSection via "x", "y" and "z"
     * @param config The config
     * @return A vector
     */
    public static Vector vectorFromConfig(ConfigurationSection config) {
        return new Vector(config.getDouble("x"), config.getDouble("y"), config.getDouble("z"));
    }
}
