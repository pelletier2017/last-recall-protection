package com.recall.teleport.search;

public class SubstringNameSearch extends NameSearch {

    public SubstringNameSearch(String name) {
        super(name);
    }

    @Override
    public boolean matches(String itemName) {
        return itemName.toLowerCase().contains(this.searchName.toLowerCase());
    }
}
