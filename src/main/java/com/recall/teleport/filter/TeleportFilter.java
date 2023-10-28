package com.recall.teleport.filter;

import com.recall.teleport.search.NameSearch;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;

import java.util.Arrays;
import java.util.List;

@Slf4j
@ToString
public abstract class TeleportFilter {

    private final NameSearch nameSearch;
    private final List<Integer> itemIds;
    private final List<String> optionsToRemove;

    TeleportFilter(NameSearch nameSearch, List<Integer> itemIds, List<String> optionsToRemove) {
        this.nameSearch = nameSearch;
        this.itemIds = itemIds;
        this.optionsToRemove = optionsToRemove;
    }

    abstract List<MenuAction> matchingActionTypes();

    public MenuEntry[] filterOutTeleports(MenuEntry[] menuEntries) {
        return Arrays.stream(menuEntries)
                .filter(menuEntry -> !(isMatchingMenuType(menuEntry) && isMatchingItem(menuEntry) && isMatchingOption(menuEntry)))
                .toArray(MenuEntry[]::new);
    }

    private boolean isMatchingMenuType(MenuEntry menuEntry) {
        return matchingActionTypes().contains(menuEntry.getType());
    }

    private boolean isMatchingOption(MenuEntry menuEntry) {
        String option = menuEntry.getOption();
        return optionsToRemove.contains(option);
    }

    private boolean isMatchingItem(MenuEntry menuEntry) {
        int inventoryItemId = menuEntry.getItemId();
        String equipmentItemName = menuEntry.getTarget();

        return itemIds.contains(inventoryItemId) || nameSearch.matches(equipmentItemName);
    }


}
