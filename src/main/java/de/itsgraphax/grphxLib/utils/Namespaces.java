package de.itsgraphax.grphxLib.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Namespaces {
    private final JavaPlugin plugin;

    private NamespacedKey key(String key) {
        return new NamespacedKey(plugin, key);
    }

    public Namespaces(JavaPlugin plugin) {
        this.plugin = plugin;
    }
}

