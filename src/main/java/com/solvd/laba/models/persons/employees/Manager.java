package com.solvd.laba.models.persons.employees;

import com.solvd.laba.enums.Rating;
import com.solvd.laba.exception.NegativeValueException;
import com.solvd.laba.exception.ValidationException;
import com.solvd.laba.interfaces.IManger;
import com.solvd.laba.models.MallRegion;

import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Manager extends CenterEmployee implements IManger {

    private static int bonus = 1000;

    private int salary;
    private List<Integer> ratings = new ArrayList<>();
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

    public void setObligationFulfilled(Predicate<Rating> predicate) {
        isObligationFulfilled = predicate.test(Rating.GOOD);
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
        int result = getRating()
                .stream()
                .mapToInt(rating -> rating)
                .sum();

        return result / getRating().size();
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

    @Override
    public void firedJanitor(Janitor janitor, MallRegion mallRegion) {
        if(janitor==null){
            throw new ValidationException("Janitor cannot be null");
        }
        if(mallRegion==null){
            throw new ValidationException("MallRegion cannot be null");
        }
        if(!mallRegion.getManager().equals(this)){
            throw new ValidationException("Manager cannot remove janitor from not his region");
        }
        mallRegion.getJanitors().remove(janitor);
        System.out.println("Janitor get fired.");
    }

    @Override
    public void replaceJanitorRegion(Janitor janitor, MallRegion currentRegion, MallRegion newRegion) {
        if(janitor==null){
            throw new ValidationException("Janitor cannot be null");
        }
        if(currentRegion==null || newRegion==null){
            throw new ValidationException("MallRegion cannot be null");
        }
        if(!currentRegion.getManager().equals(this)){
            throw new ValidationException("Manager cannot remove janitor from not his region");
        }
        if(!currentRegion.getJanitors().contains(janitor)){
            throw new ValidationException("Janitor cannot remove janitor from not his region");
        }
        newRegion.getJanitors().add(janitor);
        currentRegion.getJanitors().remove(janitor);
        System.out.println("Successfully replaced janitor region");
    }

    @Override
    public void hiredNewJanitor(Janitor janitor, MallRegion mallRegion) {
        if(janitor==null){
            throw new ValidationException("Janitor cannot be null");
        }
        if(mallRegion==null){
            throw new ValidationException("MallRegion cannot be null");
        }
        if(!mallRegion.getManager().equals(this)){
            throw new ValidationException("Manager cannot remove janitor from not his region");
        }
        mallRegion.getJanitors().add(janitor);
        System.out.println("New employee"+janitor.getName() + " " + janitor.getSurname() +"was added.");
    }
}
