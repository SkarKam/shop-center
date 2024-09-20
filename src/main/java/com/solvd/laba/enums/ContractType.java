package com.solvd.laba.enums;

public enum ContractType {

    FULLTIME(168),
    PARTTIME(84),
    QUARTERTIME(42);

    private final int hours;

    ContractType(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }
}
