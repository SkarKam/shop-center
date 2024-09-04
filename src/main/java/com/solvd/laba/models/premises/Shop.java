package com.solvd.laba.models.premises;

import com.solvd.laba.exception.ValidationException;
import com.solvd.laba.exception.NullValueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.time.LocalDate;
import java.util.Objects;

public class Shop implements IShop, Serializable {
    private static final Logger logger = LogManager.getLogger(Shop.class);
    private String shopName;
    private float shopRating;
    private LocalDate paymentDate;

    public Shop(String shopName, LocalDate paymentDate) {
        this.shopName = shopName;
        this.paymentDate = paymentDate;
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
            throw  new NullValueException("Shop Name cannot be null");
        }
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        if(paymentDate != null) {
            this.paymentDate = paymentDate;
        } else {
            throw  new NullValueException("Payment Date cannot be null");
        }
    }

    public static void save(Shop shop){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./SOLVD2/files/shop.txt"))){
            objectOutputStream.writeObject(shop);
        }catch (IOException e){
            logger.error("Error saving shop", e);
            e.printStackTrace();
        }
    }
    public static Shop load(){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("./SOLVD2/files/shop.txt"))) {
            return (Shop) objectInputStream.readObject();
        } catch (IOException e) {
            logger.error("Error loading shop", e);
            throw new RuntimeException("There is no file in folder\n" + e);
        } catch (ClassNotFoundException e) {
            logger.error("Error loading shop - class exception", e);
            throw new RuntimeException("Class exception");
        }
    }

    @Override
    public String toString() {
        return "\nShop{" +
                "shopName='" + shopName + '\'' +
                "shopRating=" + shopRating +
                "paymentDate=" + paymentDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Float.compare(shopRating, shop.shopRating) == 0 && Objects.equals(shopName, shop.shopName) && Objects.equals(paymentDate, shop.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopName, shopRating, paymentDate);
    }
}
