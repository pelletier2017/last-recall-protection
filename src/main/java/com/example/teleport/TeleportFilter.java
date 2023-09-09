package com.example.teleport;

import com.example.teleport.search.FullNameSearch;
import com.example.teleport.search.NameSearch;
import com.example.teleport.search.SubstringNameSearch;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ItemID;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public enum TeleportFilter {

    LUMBRIDGE_HOME_TELEPORT(
            new FullNameSearch("lumbridge home teleport"),
            Collections.emptyList(),
            List.of(
                    "Cast"
            )),

    CAMELOT_TELEPORT(
            new FullNameSearch("camelot teleport"),
            Collections.emptyList(),
            List.of(
                    "Cast",
                    "Seers'",
                    "Configure"
            )),

    HOME_PORTAL(
            new FullNameSearch("portal"),
            Collections.emptyList(),
            List.of(
                    "Enter",
                    "Home",
                    "Build mode",
                    "Friend's house"
            )),

    DIG_SITE_PENDANT(
            new SubstringNameSearch("digsite pendant"),
            List.of(
                    ItemID.DIGSITE_PENDANT_1,
                    ItemID.DIGSITE_PENDANT_2,
                    ItemID.DIGSITE_PENDANT_3,
                    ItemID.DIGSITE_PENDANT_4,
                    ItemID.DIGSITE_PENDANT_5
            ),
            List.of(
                    "Fossil Island",
                    "Digsite",
                    "Lithkren Dungeon",
                    "Rub"
            )),

    AMULET_OF_GLORY(
            new SubstringNameSearch("amulet of glory"),
            List.of(
                    ItemID.AMULET_OF_GLORY,
                    ItemID.AMULET_OF_GLORY1,
                    ItemID.AMULET_OF_GLORY2,
                    ItemID.AMULET_OF_GLORY3,
                    ItemID.AMULET_OF_GLORY4,
                    ItemID.AMULET_OF_GLORY5,
                    ItemID.AMULET_OF_GLORY6,
                    ItemID.AMULET_OF_GLORY_T,
                    ItemID.AMULET_OF_GLORY_T1,
                    ItemID.AMULET_OF_GLORY_T2,
                    ItemID.AMULET_OF_GLORY_T3,
                    ItemID.AMULET_OF_GLORY_T4,
                    ItemID.AMULET_OF_GLORY_T5,
                    ItemID.AMULET_OF_GLORY_T6,
                    ItemID.AMULET_OF_ETERNAL_GLORY
            ),
            List.of(
                    "Edgeville",
                    "Karamja",
                    "Draynor Village",
                    "Al Kharid",
                    "Rub"
            ));

    private final NameSearch nameSearch;
    private final List<Integer> itemIds;
    private final List<String> optionsToRemove;

    TeleportFilter(NameSearch nameSearch, List<Integer> itemIds, List<String> optionsToRemove) {
        this.nameSearch = nameSearch;
        this.itemIds = itemIds;
        this.optionsToRemove = optionsToRemove;
    }

    public MenuEntry[] filter(MenuEntry[] menuEntries) {
        return Arrays.stream(menuEntries)
                .filter(menuEntry -> !(isMatchingMenuType(menuEntry) && isMatchingItem(menuEntry) && isMatchingOption(menuEntry)))
                .toArray(MenuEntry[]::new);
    }

    private boolean isMatchingMenuType(MenuEntry menuEntry) {
        List<MenuAction> filterableTypes = List.of(
                MenuAction.CC_OP,
                MenuAction.CC_OP_LOW_PRIORITY,
                MenuAction.GAME_OBJECT_FIRST_OPTION,
                MenuAction.GAME_OBJECT_SECOND_OPTION,
                MenuAction.GAME_OBJECT_THIRD_OPTION,
                MenuAction.GAME_OBJECT_FOURTH_OPTION,
                MenuAction.GAME_OBJECT_FIFTH_OPTION
        );
        return filterableTypes.contains(menuEntry.getType());
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

    public static MenuEntry[] filterAll(MenuEntry[] menuEntries) {
        for (TeleportFilter teleportFilter : TeleportFilter.values()) {
            MenuEntry[] newMenuEntries = teleportFilter.filter(menuEntries);

            // if any entries are removed, filtering is done because only one item will ever filter
            if (newMenuEntries.length != menuEntries.length) {
                return newMenuEntries;
            }
        }
        return menuEntries;
    }
}
