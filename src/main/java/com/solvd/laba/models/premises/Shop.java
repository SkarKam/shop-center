package com.solvd.laba.models.premises;

import com.solvd.laba.enums.ShopType;
import com.solvd.laba.exception.ValidationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Shop implements Serializable {

    private static final long serialVersionUID  = 42321L;

    private static final Logger logger = LogManager.getLogger(Shop.class);

    private static List<Shop> shops = new ArrayList<>();

    private ShopType shopType;
    private String shopName;
    private float shopRating;
    private LocalDate paymentDate;

    public Shop(String shopName, ShopType shopType,LocalDate paymentDate) {
        this.shopName = shopName;
        this.paymentDate = paymentDate;
        this.shopType = shopType;
        shops.add(this);
    }

    public float getShopRating() {
        return shopRating;
    }

    public void setShopRating(int shopRating) {
        if(shopRating >= 0 && shopRating <= 5) {
            this.shopRating = shopRating;
        } else {
            throw new ValidationException("Shop Rating must be between 0 and 5");
        }
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        if(shopName != null) {
            this.shopName = shopName;
        } else {
            throw  new NullPointerException("Shop Name cannot be null");
        }
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        if(paymentDate != null) {
            this.paymentDate = paymentDate;
        } else {
            throw  new NullPointerException("Payment Date cannot be null");
        }
    }

    public ShopType getShopType() {
        return shopType;
    }

    public void setShopType(ShopType shopType) {
        this.shopType = shopType;
    }

    public static void save(){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./target/shops.txt"))){
            objectOutputStream.writeObject(shops);
        }
        catch (IOException e)
        {
            logger.error("Error saving shop", e);
            e.printStackTrace();
        }
    }
    public static List<Shop> load(){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("./target/shops.txt"))) {
            return (List<Shop>) objectInputStream.readObject();
        }
        catch (IOException e)
        {
            logger.error("Error loading shop", e);
            Shop.save();
            throw new RuntimeException("There is no file in folder\n" + e);
        }
        catch (ClassNotFoundException e)
        {
            logger.error("Error loading shop - class exception", e);
            throw new RuntimeException("Class exception");
        }
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopType=" + shopType +
                ", shopName='" + shopName + '\'' +
                ", shopRating=" + shopRating +
                ", paymentDate=" + paymentDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Float.compare(shopRating, shop.shopRating) == 0 && shopType == shop.shopType && Objects.equals(shopName, shop.shopName) && Objects.equals(paymentDate, shop.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopType, shopName, shopRating, paymentDate);
    }
}
