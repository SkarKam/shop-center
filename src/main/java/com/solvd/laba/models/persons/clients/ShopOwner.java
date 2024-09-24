package com.solvd.laba.models.persons.clients;

import com.solvd.laba.collections.CustomLinkedList;
import com.solvd.laba.interfaces.IShopOwner;
import com.solvd.laba.interfaces.lambdas.IMyPredict;
import com.solvd.laba.models.persons.Person;
import com.solvd.laba.models.premises.Premise;
import com.solvd.laba.models.premises.Shop;

import java.util.*;

/**
 * Shop owner - client of the shopCenter.
 */
public final class ShopOwner extends Person implements IShopOwner {

    private List<Integer> ratings = new ArrayList<>();
    private CustomLinkedList<Shop> shops;


    public ShopOwner(String name, String surname, int ratings) {
        super(name, surname);
        this.ratings.add(7);
    }

    public CustomLinkedList<Shop> getShops() {
        return shops;
    }

    public void setShops(CustomLinkedList<Shop> shops) {
        this.shops = shops;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = Collections.singletonList(ratings);
    }

    @Override
    public String toString() {
        return "ShopOwner{" +
                "rating=" + ratings +
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
        return ratings == shopOwner.ratings && Objects.equals(shops, shopOwner.shops);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ratings, shops);
    }


    @Override
    public void rentAPremise(Premise premise, Shop shop, IMyPredict<Shop,Integer> predict) {
        OptionalDouble avgRating = getRatings()
                .stream()
                .mapToDouble(Integer::doubleValue)
                .average();
        if(avgRating.isPresent()) {
            if (avgRating.getAsDouble() > 4.5) {
                premise.setShop(shop, predict);
            } else {
                System.out.println("You can't rent a premise. You don't have enough social credit");
            }
        }
        else {
            System.out.println("Your ratings data was wiped.");
        }
    }
}
