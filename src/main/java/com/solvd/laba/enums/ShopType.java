package com.solvd.laba.enums;

public enum  ShopType {

    ServiceStore (60000),
    ClothingStore(80000),
    Grocery(1200000),
    Mixed(6000);

    private final int estimitedIncome;

    ShopType(int estimitedIncome) {
        this.estimitedIncome = estimitedIncome;
    }

    public int getEstimitedIncome() {
        return estimitedIncome;
    }
}
