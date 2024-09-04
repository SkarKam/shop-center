package com.solvd.laba.kaminski.models.persons.clients;

import com.solvd.laba.kaminski.collections.LinkedList;
import com.solvd.laba.kaminski.models.persons.Person;
import com.solvd.laba.kaminski.models.premises.Shop;

import java.util.Objects;

/**
 * Shop owner - client of the shopCenter.
 */
public final class ShopOwner extends Person {

    private int rating;
    private LinkedList<Shop> shops;


    public ShopOwner(String name, String surname, int rating) {
        super(name, surname);
        this.rating = rating;
    }

    public LinkedList<Shop> getShops() {
        return shops;
    }

    public void setShops(LinkedList<Shop> shops) {
        this.shops = shops;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ShopOwner{" +
                "rating=" + rating +
                ", shops=" + shops +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ShopOwner shopOwner = (ShopOwner) o;
        return rating == shopOwner.rating && Objects.equals(shops, shopOwner.shops);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rating, shops);
    }
}
