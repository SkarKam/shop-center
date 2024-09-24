package com.solvd.laba.models;

import com.solvd.laba.exception.BlankValueException;

import com.solvd.laba.interfaces.IShopCenter;
import com.solvd.laba.models.parkings.Parking;
import com.solvd.laba.models.parkings.ParkingSpace;
import com.solvd.laba.models.premises.Premise;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

public class ShopCenter implements IShopCenter, Serializable {

    private static final Logger logger = LogManager.getLogger(ShopCenter.class);

    private String shopCenterName;
    private List<Premise> premises;
    private Parking parking;
    private Address address;
    private Set<MallRegion> mallRegions;

    public ShopCenter(String shopCenterName, Parking parking, Address address) {
        this.shopCenterName = shopCenterName;
        this.parking = parking;
        this.address = address;
    }


    public String getShopCenterName() {
        return shopCenterName;
    }

    public void setShopCenterName(String shopCenterName) {
        if(!shopCenterName.isBlank()) {
            this.shopCenterName = shopCenterName;
        } else {
            throw new BlankValueException("Shop center name cannot be blank.");
        }
    }

    public List<Premise> getPremises() {
        return premises;
    }

    public void setPremises(List<Premise> premises) {
        this.premises = premises;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if(address != null) {
            this.address = address;
        } else {
            throw new NullPointerException("Address cannot be blank.");
        }
    }

    public Set<MallRegion> getMallRegion() {
        return mallRegions;
    }

    public void setCenterWorkersSections(Set<MallRegion> mallRegions) {
        this.mallRegions = mallRegions;
    }

    public void addCenterWorkersSection(MallRegion mallRegion) {
        if(mallRegions != null) {
            this.mallRegions.add(mallRegion);
        } else {
            throw new NullPointerException("CenterWorkersSection cannot be blank.");
        }
    }

    @Override
    public String toString() {
        return "\nShopCenter{" +
                "shopCenterName='" + shopCenterName + '\'' +
                "premises=" + premises +
                "parking=" + parking +
                "address=" + address +
                "centerWorkersSections=" + mallRegions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopCenter that = (ShopCenter) o;
        return Objects.equals(shopCenterName, that.shopCenterName) && Objects.equals(premises, that.premises) && Objects.equals(parking, that.parking) && Objects.equals(address, that.address) && Objects.equals(mallRegions, that.mallRegions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopCenterName, premises, parking, address, mallRegions);
    }


    @Override
    public int calculateRevenue() {
            int revenue = premises
                    .stream()
                    .mapToInt(Premise::getRevenue)
                    .sum();
            revenue += parking.getParkingSpaces()
                    .values()
                    .stream()
                    .mapToInt(ParkingSpace::getRevenue)
                    .sum();

            revenue -= mallRegions
                    .stream()
                    .mapToInt(MallRegion::calculateCost)
                    .sum();
            return revenue;
    }

    public void generateRevenueInfo(){
        logger.info("Creating Shop Center Revenue");
        File file = new File("target/revenue.txt");
        try {
            FileUtils.writeStringToFile(file,String.valueOf(calculateRevenue()),"UTF-8");
            logger.info("Shop Center Revenue Info created");

        } catch (IOException e) {
            logger.error("Input File Error");
        }
    }

    public int calculateFreeSpaces() {
        if(premises.isEmpty()){
            return 0;
        }
        return (int) getPremises()
                .stream()
                .filter(premise -> premise.getShop() == null)
                .count();
    }


    public int calculateOccupiedSpaces() {
        if(premises.isEmpty()){
            return 0;
        }
        return (int) getPremises()
                .stream()
                .filter(premise -> premise.getShop() != null)
                .count();
    }
}
