package com.dermacon.securewebapp.data;

public enum ItemCategory {
    KITCHEN_SUPPLY(new String[] {"kuechenrolle", "küchenrolle"}),
    BATHROOM_SUPPLY(new String[] {"klopapier"}),
    COSTUM_SUPPLY(new String[]{});

    private final String[] itemNames;

    ItemCategory(String[] itemNames) {
        this.itemNames = itemNames;
    }

    public String[] getItemNames() {
        return itemNames;
    }
}
