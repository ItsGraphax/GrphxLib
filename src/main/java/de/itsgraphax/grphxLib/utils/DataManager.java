package de.itsgraphax.grphxLib.utils;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class DataManager {
    protected final String path;
    protected final JavaPlugin plugin;
    protected final YamlConfiguration data;
    protected final File file;

    public DataManager(String path, JavaPlugin plugin) {
        this.path = path;
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), this.path);

        if (!file.exists()) plugin.saveResource(path, false);

        data = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        plugin.getServer().getAsyncScheduler().runNow(plugin, (ScheduledTask task) -> {
            try {
                data.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}