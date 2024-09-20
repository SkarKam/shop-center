package com.solvd.laba.models.parkings;


import com.solvd.laba.exception.NegativeValueException;
import com.solvd.laba.exception.ValidationException;

import com.solvd.laba.exception.WrongTimeException;
import com.solvd.laba.interfaces.ISpaces;

import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;

public class Parking implements ISpaces {

    private int parkingLevels;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Map<Integer,ParkingSpace> parkingSpaces;

    public Parking(int parkingLevels) {
        this.parkingLevels = parkingLevels;
        openTime = LocalTime.of(6,30);
        closeTime = LocalTime.of(22,30);
    }

    public int getParkingLevels() {
        return parkingLevels;
    }

    public void setParkingLevels(int parkingLevels) {
        if(parkingLevels>=1) {
            this.parkingLevels = parkingLevels;
        } else throw new NegativeValueException("Parking Levels must be greater or equal 1");
    }

    public int getParkingSpacesPerLevels(){
        return parkingSpaces.size()/parkingLevels;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        if(closeTime == null){
            throw new NullPointerException("Close Time cannot be null");
        }
        if(closeTime.isAfter(openTime)) {
            this.closeTime = closeTime;
        } else throw new WrongTimeException("Close Time must be greater than open Time");
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        if(openTime == null){
            throw new NullPointerException("Open Time cannot be null");
        }
        if(openTime.isBefore(closeTime)) {
            this.openTime = openTime;
        } else throw new WrongTimeException("Close Time must be greater than open Time");
    }

    public Map<Integer,ParkingSpace> getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(Map<Integer,ParkingSpace> parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parking parking = (Parking) o;
        return parkingLevels == parking.parkingLevels && Objects.equals(openTime, parking.openTime) && Objects.equals(closeTime, parking.closeTime) && Objects.equals(parkingSpaces, parking.parkingSpaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingLevels, openTime, closeTime, parkingSpaces);
    }

    @Override
    public String toString() {
        return "Parking{" +
                "parkingLevels=" + parkingLevels +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", parkingSpaces=" + parkingSpaces +
                '}';
    }

    @Override
    public int calculateFreeSpaces() {
        if(parkingSpaces.isEmpty()){
            return 0;
        }
        int counter = 0;
        for(ParkingSpace parkingSpace : parkingSpaces.values()){
            if(!parkingSpace.isOccupied()){
                counter++;
            }
        }
        return counter;
    }

    @Override
    public int calculateOccupiedSpaces() {
        if(parkingSpaces.isEmpty()){
            return 0;
        }
        int counter = 0;
        for(ParkingSpace parkingSpace : parkingSpaces.values()){
            if(parkingSpace.isOccupied()){
                counter++;
            }
        }
        return counter;
    }
}
