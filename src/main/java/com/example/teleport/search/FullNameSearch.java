package com.example.teleport.search;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class FullNameSearch extends NameSearch {

    public FullNameSearch(String searchName) {
        super(searchName);
    }

    @Override
    public boolean matches(String itemName) {
        // objects sometimes look like "<col=ffff>Portal"
        log.debug("name before stripping = " + itemName);
//        List<String> stringsToRemove = List.of("<col=ffff>", "</col>");
//        for (String stringToRemove : stringsToRemove) {
//            itemName = itemName.replaceAll(stringToRemove, "");
//        }

        if (itemName.contains(">")) {
            int startOfNameIndex = itemName.indexOf(">") + 1;
            itemName = itemName.substring(startOfNameIndex);
        }

        // objects sometimes look like "<col=ffff>Camelot Teleport</col>"
        if (itemName.contains("<")) {
            int endOfNameIndex = itemName.lastIndexOf("<");
            itemName = itemName.substring(0, endOfNameIndex);
        }
        log.debug("name after stripping = " + itemName);
        return this.searchName.toLowerCase().equals(itemName.toLowerCase());
    }
}
