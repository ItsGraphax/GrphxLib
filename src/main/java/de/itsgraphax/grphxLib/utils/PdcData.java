package de.itsgraphax.grphxLib.utils;

import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;

/**
 *  A boilerplate class for making getters and setters for PersistentDataContainer operations
 */
public abstract class PdcData {
    protected final NamespacesBase namespaces;

    /**
     * Returns the PDC of a PersistentDataHolder
     * @param holder The holder
     * @return The PDC
     */
    public static PersistentDataContainer pdc(PersistentDataHolder holder) {
        return holder.getPersistentDataContainer();
    }

    public PdcData(NamespacesBase namespaces) {
        this.namespaces = namespaces;
    }
}
