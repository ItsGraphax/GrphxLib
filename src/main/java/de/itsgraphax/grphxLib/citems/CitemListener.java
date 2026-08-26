package de.itsgraphax.grphxLib.citems;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.CraftingRecipe;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class CitemListener implements Listener {
    protected final CitemManager manager;

    public CitemListener(CitemManager manager) {
        this.manager = manager;
    }

    @EventHandler
    void onInteract(PlayerInteractEvent event) {
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
            CraftingInventory inv = event.getInventory();

            Set<CrecipeOverride> overrides = manager.getOverrides(recipe.getKey());
            if (overrides == null) return;

            for (CrecipeOverride override : overrides) {
                if (!override.isValid.apply(inv)) {
                    inv.setResult(null);
                    return;
                }
            }
        }
    }
}
