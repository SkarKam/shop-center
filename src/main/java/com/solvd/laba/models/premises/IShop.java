package com.solvd.laba.models.premises;

import java.time.LocalDate;

public interface IShop {
    public String getShopName();
    public void setShopName(String shopName);
    public float getShopRating();
    public void setShopRating(int shopRating);
    public LocalDate getPaymentDate();
    public void setPaymentDate(LocalDate shopDate);
}
