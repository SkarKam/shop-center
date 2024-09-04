package com.solvd.laba.models.persons.employees;

import com.solvd.laba.models.persons.Person;

import java.time.LocalDate;
import java.util.Objects;

public abstract class CenterEmployee extends Person implements ICenterEmployee{
    private static final String centerName = "SunnyCenter";
    protected LocalDate dateOfEmployment;

    public CenterEmployee(String name, String surname) {
        super(name,surname);
        dateOfEmployment = LocalDate.now();

    }


    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }


    public abstract int calculateSalary();

    public final int getYearsWorked(){
        return LocalDate.now().getYear() - dateOfEmployment.getYear();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CenterEmployee that = (CenterEmployee) o;
        return Objects.equals(dateOfEmployment, that.dateOfEmployment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateOfEmployment);
    }

    @Override
    public String toString() {
        return "CenterEmployee{" +
                "dateOfEmployment=" + dateOfEmployment +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
