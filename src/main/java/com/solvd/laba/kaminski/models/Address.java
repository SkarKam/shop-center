package com.solvd.laba.kaminski.models;

import com.solvd.laba.kaminski.exception.BlankValueException;

import java.io.Serializable;
import java.util.Objects;

public class Address implements IAdrress, Serializable {
    private String street;
    private String city;
    private String state;

    public Address(String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if(!street.isBlank()) {
            this.street = street;
        } else{
            throw new BlankValueException("Street cannot be blank.");
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if(!city.isBlank()) {
            this.city = city;
        } else {
            throw new BlankValueException("City cannot be blank.");
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if(!state.isBlank()) {
            this.state = state;
        } else {
            throw new BlankValueException("State cannot be blank.");
        }
    }

    @Override
    public String toString() {
        return "\nAddress{" +
                "street='" + street + '\'' +
                "city='" + city + '\'' +
                "state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(state, address.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, state);
    }
}
