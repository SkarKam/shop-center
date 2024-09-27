package com.solvd.laba.models.premises;

import com.solvd.laba.enums.PremiseSizeType;
import com.solvd.laba.exception.AlreadyTakenException;
import com.solvd.laba.exception.ValidationException;

import com.solvd.laba.interfaces.IRevenue;
import com.solvd.laba.interfaces.lambdas.IMyPredict;

import java.awt.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;


public class Premise implements IRevenue {

    private static int monthlyCost; //per 1 m2

    private Shop shop;
    private LocalDate rentalDate;
    private Dimension dimension;
    private PremiseSizeType premiseSizeType;

    public Premise(Shop shop, Dimension dimension) {
        this.shop = shop;
        this.rentalDate = LocalDate.now();
        this.dimension = dimension;
        monthlyCost = 5000;
        setPremiseSize();
    }
    //Premise can exist without shop
    public Premise(Dimension dimension) {
        this.dimension = dimension;
        monthlyCost = 5000;
        setPremiseSize();
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

    public Optional<Shop> getShop() {
            return Optional.of(shop);
    }

    public void setShop(Shop shop, IMyPredict<Shop,Integer> predict) {
            if(shop == null) {
                this.shop = shop;
                setRentalDate();
                return;
            }
            if(getShop()==null){
                if(predict.predict(shop,getPremiseCost())) {
                    this.shop = shop;
                    setRentalDate();
                } else {
                    throw new ValidationException("Shop estimited income should be greater than premise cost");
                }
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
            throw new NullPointerException("Dismension cannot be null");
        }
    }

    public Optional<LocalDate> getRentalDate() {
        return Optional.of(rentalDate);
    }

    private void setRentalDate() {
        if(getShop().isEmpty()) {
            this.rentalDate = null;
        }
        else{
            this.rentalDate = LocalDate.now();
        }
    }

   public int getPremiseCost(){
        return (int)dimension.getWidth()*(int)dimension.getHeight() * (monthlyCost- premiseSizeType.getExtraDiscount());
    }


    public static int getDimensionCost(int m2){
        return monthlyCost *m2;
    }
    public static int getDimensionCost(int width, int length){
        return monthlyCost *width*length;
    }

    public PremiseSizeType getPremiseSizeType() {
        return premiseSizeType;
    }

    public void setPremiseSize() {
        int size = (int) dimension.getWidth() * (int) dimension.getHeight();
        if(PremiseSizeType.SMALL.getMaxSize()<=size) {
            premiseSizeType = PremiseSizeType.SMALL;
        } else if(PremiseSizeType.LARGE.getMinSize()>=size) {
            premiseSizeType = PremiseSizeType.LARGE;
        } else {
            premiseSizeType = PremiseSizeType.MEDIUM;
        }
    }

    public void printPremiseCost(Function<Integer,String> function){
        System.out.println(function.apply(getPremiseCost()));
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


    @Override
    public int getRevenue() {
        if(getShop().isEmpty()) {
            return 0;
        } else {
            return getPremiseCost();
        }
    }
}
