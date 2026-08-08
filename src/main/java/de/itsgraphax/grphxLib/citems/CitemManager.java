package de.itsgraphax.grphxLib.citems;

import de.itsgraphax.grphxLib.citems.exceptions.DuplicateCitemException;
import it.unimi.dsi.fastutil.Hash;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class CitemManager {
    protected static final NamespacedKey customItemNamespace = new NamespacedKey("grphxlib", "customitem");

    protected final Map<NamespacedKey, Citem> citems = new HashMap<>();
    protected final Map<NamespacedKey, Set<CrecipeOverride>> crecipeOverrides = new HashMap<>();

    public CitemManager() {
    }

    public void register(@NotNull Citem citem) {
        if (citems.containsKey(citem.key())) throw new DuplicateCitemException("This Citem already exists!");
        citems.put(citem.key(), citem);
    }

    public void override(@NotNull NamespacedKey recipeKey, @NotNull CrecipeOverride override) {
        if (!crecipeOverrides.containsKey(recipeKey)) crecipeOverrides.put(recipeKey, new HashSet<>());
        crecipeOverrides.get(recipeKey).add(override);
    }

    public @Nullable Set<CrecipeOverride> getOverrides(@NotNull NamespacedKey recipeKey) {
        return crecipeOverrides.get(recipeKey);
    }
    public Collection<Citem> values() {
        return citems.values();
    }

    public @Nullable Citem get(@NotNull NamespacedKey key) {
        return citems.get(key);
    }

    public @Nullable Citem fromItem(@NotNull ItemStack item) {
        return citems.get(NamespacedKey.fromString(
                item.getPersistentDataContainer()
                        .getOrDefault(customItemNamespace, PersistentDataType.STRING, "")
        ));
    }

    public boolean contains(NamespacedKey key) {
        return citems.containsKey(key);
    }
}
