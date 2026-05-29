package de.itsgraphax.grphxLib.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * A simple class that allows for converting MiniMessage format to Components, statically or with the instance
 */
public class RichText {
    protected final MiniMessage miniMessage;

    public RichText() {
        miniMessage = MiniMessage.miniMessage();
    }

    /**
     * Converts MiniMessage format to Components
     * @param richMessage A String in MiniMessage Format
     * @return The parsed String
     */
    public @NotNull Component parse(@NotNull String richMessage) {
        return miniMessage.deserialize(richMessage);
    }

    /**
     * Parses MiniMessage text with params
     * @param params Arguments in the order of (key, value), for example: `paramsParse("key", "POINTS", points, "NAME", name)`
     * @return The parsed string with all instances of "{{key}}" replaced with the value
     */
    public @NotNull Component paramsParse(@NotNull String richMessage, String... params) {
        for (int i = 0; i < params.length; i += 2) {
            String key = params[i];
            String val = params[i + 1];
            richMessage = richMessage.replace(String.format("{{%s}}", key), val);
        }
        return parse(richMessage);
    }

    /**
     * A dereviation of RichText with another shorthand for getting a string direct out of the plugin config.
     */
    public static class RichConfigText extends RichText {
        protected final FileConfiguration config;

        public RichConfigText(JavaPlugin plugin) {
            this.config = plugin.getConfig();
        }

        /**
         * Parses MiniMessage text from config
         * @param subkey A key in config that gets automatically prefixed by "strings."
         * @return The parsed String
         */
        public @NotNull Component fromConfig(@NotNull String subkey) {
            return parse(config.getString("strings." + subkey, String.format("{{ string not found in config: '%s' }}", subkey)));
        }

        /**
         * Parses MiniMessage text from config with params
         * @param subkey A key in config that gets automatically prefixed by "strings."
         * @param params Arguments in the order of (key, value), for example: `paramsFromConfig("key", "POINTS", points, "NAME", name)`
         * @return The parsed string with all instances of "{{key}}" replaced with the value
         */
        public @NotNull Component paramsFromConfig(@NotNull String subkey, String... params) {
            if (params.length % 2 != 0) throw new IllegalArgumentException("The number of params needs to consist of a key, then value; for example ('POINTS', points, 'NAME', name)");
            String richText = config.getString("strings." + subkey, String.format("{{ string not found in config: '%s' }}", subkey));
            return paramsParse(richText);
        }
    }
}
