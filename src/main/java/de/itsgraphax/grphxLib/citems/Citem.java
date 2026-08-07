package de.itsgraphax.grphxLib.citems;

import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

public abstract class Citem {
    protected static final NamespacedKey customItemNamespace = new NamespacedKey("grphxlib", "customitem");

    protected final NamespacedKey key;
    protected ItemStack item;

    protected Citem(@NotNull NamespacedKey key) {
        this.key = key;

        ItemStack defaultItem = ItemStack.of(Material.POISONOUS_POTATO);
        defaultItem.unsetData(DataComponentTypes.CONSUMABLE);
        setItem(defaultItem);
    }

    public @NotNull NamespacedKey key() {
        return key;
    }

    public @NotNull ItemStack createItem() {
        return item.clone();
    }

    public void setItem(@NotNull ItemStack item) {
        ItemStack copy = item.clone();

        copy.setAmount(1);
        copy.setData(DataComponentTypes.ITEM_MODEL, key);
        Component translation = Component.translatable(key.getNamespace() + "." + key.getKey()).decorate();
        copy.setData(DataComponentTypes.CUSTOM_NAME, translation);
        copy.editPersistentDataContainer(pdc -> pdc.set(customItemNamespace, PersistentDataType.STRING, key.toString()));

        this.item = copy;
    }

    public void editItem(@NotNull Consumer<ItemStack> consumer) {
        ItemStack copy = item.clone();
        consumer.accept(copy);
        setItem(copy); // verify values
    }

    public void onInteract(@NotNull PlayerInteractEvent event) {
    }

    protected void consume(@NotNull PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null) throw new RuntimeException("Item from PlayerInteractEvent is null");
        item.setAmount(item.getAmount() - 1);
    }

    public boolean isItem(@Nullable ItemStack item) {
        if (item == null) return false;
        return Objects.equals(item.getPersistentDataContainer()
                        .get(customItemNamespace, PersistentDataType.STRING),
                key.toString());
    }
}
