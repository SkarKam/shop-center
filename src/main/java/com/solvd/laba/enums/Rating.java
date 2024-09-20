package com.solvd.laba.enums;

public enum Rating {

    VERYBAD(0,20), // x<20
    BAD(20,40), // 20<=x<40
    MEDIOCARE(40,60), // 40<=x<60
    GOOD(60,80), //60<=x<80
    VERYGOOD(80,100); // 80<=x<=100

    private final int minRate;
    private final int maxRate;

    Rating(int minRate, int maxRate) {
        this.minRate = minRate;
        this.maxRate = maxRate;
    }

    public int getMinRate() {
        return minRate;
    }
    public int getMaxRate() {
        return maxRate;
    }
}
