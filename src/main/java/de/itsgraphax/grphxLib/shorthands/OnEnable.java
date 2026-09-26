package de.itsgraphax.grphxLib.shorthands;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * A class with multiple utility shorthands for doing stuff in the JavaPlugin onEnable
 */
public class OnEnable {
    public static void registerEvents(JavaPlugin plugin, Listener... listeners) {
        registerEvents(plugin, Arrays.asList(listeners));
    }

    public static void registerEvents(JavaPlugin plugin, Collection<Listener> listeners) {
        PluginManager pm = plugin.getServer().getPluginManager();
        listeners.forEach(listener -> pm.registerEvents(listener, plugin));
    }

    @SafeVarargs
    public static void registerCommands(JavaPlugin plugin, Consumer<Commands>... consumers) {
        registerCommands(plugin, Arrays.asList(consumers));
    }

    public static void registerCommands(JavaPlugin plugin, Collection<Consumer<Commands>> consumers) {
        plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS.newHandler(event -> {
            Commands r = event.registrar();
           consumers.forEach(consumer -> consumer.accept(r));
        }));
    }
}