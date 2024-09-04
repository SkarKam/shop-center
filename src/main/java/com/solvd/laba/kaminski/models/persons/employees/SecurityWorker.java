package com.solvd.laba.kaminski.models.persons.employees;

import com.solvd.laba.kaminski.exception.BlankValueException;
import com.solvd.laba.kaminski.exception.NegativeValueException;
import com.solvd.laba.kaminski.exception.ValidationException;

import java.util.Objects;

//Add fields
public class SecurityWorker extends CenterEmployee {

    private int rate;
    private int hoursWorked;
    private static String securityCompany;

    static {
        securityCompany = "SecCom";
    }

    public SecurityWorker(String name, String surname) {
        super(name, surname);
        this.rate = 25;
        this.hoursWorked = 0;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        if(rate > this.rate) {
            this.rate = rate;
        } else {
            throw new ValidationException("Rate must be greater than or equal to " + this.rate);
        }
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        if(hoursWorked >= 0) {
            this.hoursWorked = hoursWorked;
        } else {
            throw new NegativeValueException("Hours worked cannot be negative");
        }
    }

    public static String getSecurityCompany() {
        return securityCompany;
    }

    public static void setSecurityCompany(String securityCompany) {
        if(!securityCompany.isBlank()) {
            SecurityWorker.securityCompany = securityCompany;
        } else {
            throw new BlankValueException("SecurityCompany cannot be blank");
        }
    }

    @Override
    public int calculateSalary() {
        return  rate * hoursWorked;
    }

    @Override
    public String toString() {
        return "SecurityWorker{" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                "rate=" + rate +
                ", hoursWorked=" + hoursWorked +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityWorker that = (SecurityWorker) o;
        return rate == that.rate && hoursWorked == that.hoursWorked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate, hoursWorked);
    }
}
