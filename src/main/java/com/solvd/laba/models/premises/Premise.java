package com.solvd.laba.models.premises;

import com.solvd.laba.exception.AlreadyTakenException;
import com.solvd.laba.exception.ValidationException;
import com.solvd.laba.exception.NullValueException;

import java.awt.*;
import java.time.LocalDate;
import java.util.Objects;

public class Premise {
    private static int monthlyCost = 5000;
    private Shop shop;
    private LocalDate rentalDate;
    private Dimension dimension;

    public Premise(Shop shop, Dimension dimension) {
        this.shop = shop;
        this.rentalDate = LocalDate.now();
        this.dimension = dimension;
    }
    //Premise can exist without shop
    public Premise(Dimension dimension) {
        this.dimension = dimension;
    }

    public static int getMonthlyCost() {
        return monthlyCost;
    }

    public static void setMonthlyCost(int monthlyCost) {
        if(monthlyCost > 1000) {
            Premise.monthlyCost = monthlyCost;
        } else {
            throw new ValidationException("costForM2 must be greater than 1000");
        }
    }

    public Shop getShop() {
        if(shop!=null) {
            return shop;
        } else {
            return null;
        }
    }

    public void setShop(Shop shop) {
            if(shop == null) {
                this.shop = shop;
                setRentalDate();
                return;
            }
            if(getShop()==null){
                this.shop = shop;
                setRentalDate();
            } else {
                throw new AlreadyTakenException("Other shop already use this premise");
            }
        }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        if(dimension != null) {
            this.dimension = dimension;
        } else {
            throw new NullValueException("Dismension cannot be null");
        }
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    private void setRentalDate() {
        if(getShop()==null) {
            this.rentalDate = null;
        }
        else{
            this.rentalDate = LocalDate.now();
        }
    }

   public int getPremiseCost(){
        return (int)dimension.getWidth()*(int)dimension.getHeight() * monthlyCost;
    }


    public static int getDimensionCost(int m2){
        return monthlyCost *m2;
    }
    public static int getDimensionCost(int width, int length){
        return monthlyCost *width*length;
    }

    @Override
    public String toString() {
        return "\nPremise{" +
                "shop=" + shop +
                "rentalDate=" + rentalDate +
                "dimension=" + dimension +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Premise premise = (Premise) o;
        return Objects.equals(shop, premise.shop) && Objects.equals(rentalDate, premise.rentalDate) && Objects.equals(dimension, premise.dimension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shop, rentalDate, dimension);
    }
}
