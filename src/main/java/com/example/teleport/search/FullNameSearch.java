package com.example.teleport.search;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FullNameSearch extends NameSearch {

    public FullNameSearch(String searchName) {
        super(searchName);
    }

    @Override
    public boolean matches(String itemName) {
        // objects sometimes look like "<col=ffff>Portal"
        if (itemName.contains("<col")) {
            int startOfNameIndex = itemName.indexOf(">") + 1;
            itemName = itemName.substring(startOfNameIndex);
        }

        // objects sometimes look like "<col=ffff>Camelot Teleport</col>"
        if (itemName.contains("</col>")) {
            int endOfNameIndex = itemName.lastIndexOf("<");
            itemName = itemName.substring(0, endOfNameIndex);
        }
        return this.searchName.toLowerCase().equals(itemName.toLowerCase());
    }
}
