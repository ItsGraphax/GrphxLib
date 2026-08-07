package de.itsgraphax.grphxLib.citems;

import de.itsgraphax.grphxLib.citems.exceptions.DuplicateCitemException;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CitemManager {
    protected static final NamespacedKey customItemNamespace = new NamespacedKey("grphxlib", "customitem");

    protected final Map<NamespacedKey, Citem> citems = new HashMap<>();

    public CitemManager() {
    }

    public void register(Citem citem) {
        if (citems.containsKey(citem.key())) throw new DuplicateCitemException("This Citem already exists!");
        citems.put(citem.key(), citem);
    }

    public @Nullable Citem get(NamespacedKey key) {
        return citems.get(key);
    }

    public @Nullable Citem fromItem(ItemStack item) {
        return citems.get(NamespacedKey.fromString(
                item.getPersistentDataContainer()
                        .getOrDefault(customItemNamespace, PersistentDataType.STRING, "")
        ));
    }

    public boolean contains(NamespacedKey key) {
        return citems.containsKey(key);
    }

    public Collection<Citem> values() {
        return citems.values();
    }
}
