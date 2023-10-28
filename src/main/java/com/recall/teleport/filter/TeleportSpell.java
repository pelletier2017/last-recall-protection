package com.recall.teleport.filter;

import com.recall.teleport.search.FullNameSearch;
import net.runelite.api.MenuAction;

import java.util.Collections;
import java.util.List;

public class TeleportSpell extends TeleportFilter {

    public TeleportSpell(String spellName, List<String> optionsToRemove) {
        super(new FullNameSearch(spellName), Collections.emptyList(), optionsToRemove);
    }

    @Override
    List<MenuAction> matchingActionTypes() {
        return List.of(
                MenuAction.CC_OP,
                MenuAction.CC_OP_LOW_PRIORITY
        );
    }
}
