package com.recall.teleport.filter;

import com.recall.teleport.search.FullNameSearch;
import net.runelite.api.MenuAction;

import java.util.Collections;
import java.util.List;

public class TeleportObject extends TeleportFilter {

    public TeleportObject(String objectName, List<String> optionsToRemove) {
        super(new FullNameSearch(objectName), Collections.emptyList(), optionsToRemove);
    }

    @Override
    List<MenuAction> matchingActionTypes() {
        return List.of(
                MenuAction.GAME_OBJECT_FIRST_OPTION,
                MenuAction.GAME_OBJECT_SECOND_OPTION,
                MenuAction.GAME_OBJECT_THIRD_OPTION,
                MenuAction.GAME_OBJECT_FOURTH_OPTION,
                MenuAction.GAME_OBJECT_FIFTH_OPTION
        );
    }
}
