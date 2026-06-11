package de.itsgraphax.grphxLib.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class NamespacesBase {
    protected final JavaPlugin plugin;

    protected NamespacedKey key(String key) {
        return new NamespacedKey(plugin, key);
    }

    public NamespacesBase(JavaPlugin plugin) {
        this.plugin = plugin;
    }
}

