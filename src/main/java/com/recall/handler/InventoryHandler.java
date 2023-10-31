package com.recall.handler;

import net.runelite.api.InventoryID;
import net.runelite.api.ItemID;
import net.runelite.api.events.ItemContainerChanged;

public class InventoryHandler {

    private boolean hasCrystalOfMemories;

    public void onItemContainerChanged(ItemContainerChanged event) {
        if (event.getContainerId() == InventoryID.INVENTORY.getId()) {
            hasCrystalOfMemories = event.getItemContainer().contains(ItemID.CRYSTAL_OF_MEMORIES);
        }
    }

    public boolean hasCrystalOfMemories() {
        return hasCrystalOfMemories;
    }
}
