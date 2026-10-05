package de.itsgraphax.grphxLib.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class ConfigUpdater {
    protected final JavaPlugin plugin;

    protected final String resourcePath;

    public ConfigUpdater(JavaPlugin plugin, String resourcePath) {
        this.plugin = plugin;

        this.resourcePath = resourcePath;
    }

    // Code directly copied and slightly modified from PaperMC JavaPlugin
    protected File getTemporaryResource() {
        try {
            File outFile = File.createTempFile("tmp", "");

            InputStream in = plugin.getResource(resourcePath);
            if (in == null) {
                throw new RuntimeException("That resource does not exist!");
            }
            OutputStream out = new FileOutputStream(outFile);

            byte[] buf = new byte[1024];

            int len;
            while((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            in.close();
            out.close();

            return outFile;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateConfig() {
        try {
            File savedFile = new File(plugin.getDataFolder(), resourcePath);
            // Ignore unnecessary calculation if the file dosent even exist
            if (!savedFile.exists()) {
                plugin.saveResource(resourcePath, false);
                return;
            }

            File defaultFile = getTemporaryResource();

            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultFile);
            YamlConfiguration savedConfig = YamlConfiguration.loadConfiguration(savedFile);

            for (String key : defaultConfig.getKeys(true)) {
                if (savedConfig.contains(key)) continue;

                savedConfig.set(key, defaultConfig.get(key));
            }

            savedConfig.save(savedFile);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
