package com.example.teleport.filter;

import com.example.teleport.search.SubstringNameSearch;
import net.runelite.api.MenuAction;

import java.util.List;

public class TeleportItem extends TeleportFilter {

    public TeleportItem(String itemNameSearch, List<Integer> itemIds, List<String> optionsToRemove) {
        super(new SubstringNameSearch(itemNameSearch), itemIds, optionsToRemove);
    }

    @Override
    List<MenuAction> matchingActionTypes() {
        return List.of(
                MenuAction.CC_OP,
                MenuAction.CC_OP_LOW_PRIORITY
        );
    }
}
