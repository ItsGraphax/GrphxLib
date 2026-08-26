package de.itsgraphax.grphxLib.citems;

import org.bukkit.inventory.CraftingInventory;

import java.util.function.Function;

public class CrecipeOverride {
    protected final Function<CraftingInventory, Boolean> isValid;

    public CrecipeOverride(Function<CraftingInventory, Boolean> isValid) {
        this.isValid = isValid;
    }
}
