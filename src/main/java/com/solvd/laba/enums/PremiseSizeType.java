package com.solvd.laba.enums;

public enum PremiseSizeType {

    SMALL(1,15),
    MEDIUM(16,30)
            {
                @Override
                public int getExtraDiscount() {
                    return 500;
                }
            },
    LARGE(30,200)
            {
                @Override
                public int getExtraDiscount() {
                    return 1000;
                }
            };

    private final int minSize;
    private final int maxSize;

    PremiseSizeType(int minSize, int maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    public int getMinSize() {
        return minSize;
    }
    public int getMaxSize() {
        return maxSize;
    }
    public int getExtraDiscount(){
        return 100;
    }
}
