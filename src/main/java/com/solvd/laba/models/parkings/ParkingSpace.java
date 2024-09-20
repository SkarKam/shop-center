package com.solvd.laba.models.parkings;

import com.solvd.laba.enums.ParkingSpaceType;
import com.solvd.laba.exception.NegativeValueException;
import com.solvd.laba.interfaces.IRevenue;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

public class ParkingSpace implements IRevenue, Serializable {

    private static int cost;

    private final ParkingSpaceType type;

    private int id;
    private boolean isPaid;
    private boolean isOccupied;

    public ParkingSpace(int id, ParkingSpaceType type) {
        this.id = id;
        this.type = type;
        this.isPaid = false;
        this.isOccupied = false;
        cost = 10;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id>0) {
            this.id = id;
        } else {
            throw new NegativeValueException("ID must be greater than 0");
        }
    }

    public ParkingSpaceType getParkingSpaceType() {
        return type;
    }


    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public static int getCost() {
        return cost;
    }

    public static void setCost(int cost) {
        if(cost >= 0) {
            ParkingSpace.cost = cost;
        } else {
            throw new NegativeValueException("Cost cannot be negative");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSpace that = (ParkingSpace) o;
        return id == that.id && isPaid == that.isPaid && isOccupied == that.isOccupied && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id, isPaid, isOccupied);
    }

    @Override
    public String toString() {
        return "ParkingSpace{" +
                "type=" + type +
                ", id=" + id +
                ", isPaid=" + isPaid +
                ", isOccupied=" + isOccupied +
                '}';
    }

    @Override
    public int getRevenue() {
        if(isPaid()){
            if(isOccupied()) {
                return cost;
            }
        }
        return 0;
    }
}
