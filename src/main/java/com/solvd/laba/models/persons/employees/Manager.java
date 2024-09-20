package com.solvd.laba.models.persons.employees;

import com.solvd.laba.enums.Rating;
import com.solvd.laba.exception.NegativeValueException;
import com.solvd.laba.exception.ValidationException;

import java.io.File;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Manager extends CenterEmployee {

    private static int bonus = 1000;

    private int salary;
    private List<Integer> ratings = new ArrayList<>();;
    private boolean isObligationFulfilled;

    public Manager(String name, String surname) {
        super(name, surname);
        this.isObligationFulfilled = true;
        ratings.add(75);
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if(salary >= 0) {
            this.salary = salary;
        } else {
            throw new NegativeValueException("Salary cannot be negative");
        }
    }

    public static int getBonus() {
        return bonus;
    }

    public static void setBonus(int bonus) {
        if(bonus >= 500 && bonus < 2000) {
            Manager.bonus = bonus;
        } else {
            throw new ValidationException("Bonus must be between 500 and 2000");
        }
    }

    public boolean isObligationFulfilled() {
        return isObligationFulfilled;
    }

    public void setObligationFulfilled() {
        Predicate<Integer> isEnough = avgRating -> avgRating >= Rating.GOOD.getMinRate();
        isObligationFulfilled = isEnough.test(getAverageRating());
    }

    public List<Integer> getRating() {
        return ratings;
    }

    public void setRating(List<Integer> ratings) {
        this.ratings = ratings;
    }

    public void addRating(int rating) {
        if(rating>=0 && rating<=100) {
            this.ratings.add(rating);
        } else {
            throw new ValidationException("Rating must be between 0 and 100");
        }
    }

    public int getAverageRating() {
        int result = 0;
        int counter = 0;
        for(Integer rating : ratings) {
            result += rating;
            counter++;
        }
        return result / counter;
    }
    @Override
    public int calculateSalary() {

        if(isObligationFulfilled) {
            return salary + bonus;
        } else {
            return salary;
        }
    }

    @Override
    public String toString() {
        return "Manager{" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                "salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return salary == manager.salary && isObligationFulfilled == manager.isObligationFulfilled;
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary, isObligationFulfilled);
    }
}
