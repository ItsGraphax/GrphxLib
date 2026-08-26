package de.itsgraphax.grphxLib.citems;

import org.bukkit.inventory.ItemStack;

public class RequireCitemOverride extends CrecipeOverride {
    public RequireCitemOverride(Citem citem, int slot) {
        super((inv) -> {
            ItemStack item = inv.getItem(slot);

            return citem.isItem(item);
        });
    }
}
