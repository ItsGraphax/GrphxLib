package de.itsgraphax.grphxLib.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * A simple class that allows for converting MiniMessage format to Components, statically or with the instance
 */
public class RichText {
    protected final MiniMessage miniMessage;

    public RichText() {
        miniMessage = MiniMessage.miniMessage();
    }

    /**
     * Parses MiniMessage text with params
     * @param params Arguments in the order of (key, value), for example: `paramsParse("key", "POINTS", points, "NAME", name)`
     * @return The parsed string with all instances of "{{key}}" replaced with the value
     */
    public @NotNull Component parse(@NotNull String richMessage, String... params) {
        for (int i = 0; i < params.length; i += 2) {
            String key = params[i];
            String val = params[i + 1];
            richMessage = richMessage.replace(String.format("{{%s}}", key), val);
        }
        return miniMessage.deserialize(richMessage);
    }

    /**
     * @deprecated This is now an alias for parse, with the same function
     */
    @Deprecated(since="1.1.0", forRemoval = true)
    public @NotNull Component paramsParse(@NotNull String richMessage, String... params) {
        return parse(richMessage, params);
    }

    public @NotNull String serialize(@NotNull Component component) {
        return miniMessage.serialize(component);
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
         * Parses MiniMessage text from config with params
         * @param subkey A key in config that gets automatically prefixed by "strings."
         * @param params Arguments in the order of (key, value), for example: `paramsFromConfig("key", "POINTS", points, "NAME", name)`
         * @return The parsed string with all instances of "{{key}}" replaced with the value
         */
        public @NotNull Component fromConfig(@NotNull String subkey, String... params) {
            if (params.length % 2 != 0) throw new IllegalArgumentException("The number of params needs to consist of a key, then value; for example ('POINTS', points, 'NAME', name)");
            String richText = config.getString("strings." + subkey, String.format("!%s%s", subkey, Arrays.toString(params)));
            return parse(richText, params);
        }

        /**
         * @deprecated This is now an alias for RichConfigText#fromConfig
         */
        @Deprecated(since="1.1.0", forRemoval = true)
        public @NotNull Component paramsFromConfig(@NotNull String subkey, String... params) {
            return fromConfig(subkey, params);
        }
    }
}
