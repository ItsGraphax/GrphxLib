package de.itsgraphax.grphxLib.citems;

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
        System.out.println("1");
        if (event.getHand() != EquipmentSlot.HAND) return;
        System.out.println("2");
        ItemStack item = event.getItem();
        if (item == null) return;
        System.out.println("3");
        Citem citem = manager.fromItem(event.getItem());
        if (citem == null) return;
        System.out.println("4");
        citem.onInteract(event);
    }
}
