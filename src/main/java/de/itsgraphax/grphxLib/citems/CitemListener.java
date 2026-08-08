package de.itsgraphax.grphxLib.citems;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;

import java.util.Set;

public class CitemListener implements Listener {
    protected final CitemManager manager;

    public CitemListener(CitemManager manager) {
        this.manager = manager;
    }

    @EventHandler
    void onInteract(PlayerInteractEvent event) {
        if (event.useInteractedBlock() == Event.Result.ALLOW) return;

        if (event.getHand() != EquipmentSlot.HAND) return;
        ItemStack item = event.getItem();
        if (item == null) return;

        Citem citem = manager.fromItem(event.getItem());
        if (citem == null) return;

        citem.onInteract(event);
    }

    @EventHandler
    void onPrepareCraft(PrepareItemCraftEvent event) {
        if (event.getRecipe() instanceof CraftingRecipe recipe) {
            Set<CrecipeOverride> overrides = manager.getOverrides(recipe.getKey());
            if (overrides == null) return;
            for (CrecipeOverride override : overrides) {
                ItemStack item = event.getInventory().getItem(override.slot());
                if (!override.citem().isItem(item)) {
                    event.getInventory().setResult(null);
                    return;
                }
            }
        }
    }
}
