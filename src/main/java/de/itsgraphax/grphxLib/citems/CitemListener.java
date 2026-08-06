package de.itsgraphax.grphxLib.citems;

import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

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
}
