package com.recall.teleport.search;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class NameSearch {

    String searchName;

    public NameSearch(String searchName) {
        this.searchName = searchName;
    }

    public abstract boolean matches(String itemName);
}
