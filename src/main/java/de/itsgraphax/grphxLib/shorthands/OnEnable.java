package de.itsgraphax.grphxLib.shorthands;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * A class with multiple utility shorthands for doing stuff in the JavaPlugin onEnable
 */
public class OnEnable {
    /**
     * Registers all given listeners
     *
     * @param listeners A collection of Listener classes to register
     * @param plugin    The plugin they get registered from
     */
    public static void registerEvents(JavaPlugin plugin, Listener... listeners) {
        PluginManager pm = plugin.getServer().getPluginManager();
        Arrays.stream(listeners).forEach(listener -> pm.registerEvents(listener, plugin));
    }

    /**
     * Registers all given commands
     *
     * @param consumers A collection of Consumers that accept a Commands instance
     * @param plugin    The plugin they get registered from
     */
    @SafeVarargs
    public static void registerCommands(JavaPlugin plugin, Consumer<Commands>... consumers) {
        plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS.newHandler(event -> {
            Commands r = event.registrar();
            Arrays.stream(consumers).forEach(consumer -> consumer.accept(r));
        }));
    }
}