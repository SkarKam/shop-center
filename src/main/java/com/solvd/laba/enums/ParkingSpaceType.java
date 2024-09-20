package com.solvd.laba.enums;

import java.awt.*;

public enum ParkingSpaceType {

    ForInvalids(new Dimension(6, 4)),
    ForShops(new Dimension(8, 6)),
    ForOthersCars(new Dimension(4, 3)),
    ForOthersMotors(new Dimension(2, 1)),;

    private final Dimension dimension;

    ParkingSpaceType(Dimension dimension) {
        this.dimension = dimension;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
