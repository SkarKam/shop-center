package com.solvd.laba.models.persons.employees;

import com.solvd.laba.enums.ContractType;
import com.solvd.laba.exception.ValidationException;
import com.solvd.laba.interfaces.lambdas.IStringRefactor;

import java.util.Objects;

public class Janitor extends CenterEmployee {

    private int salary;
    private int bonus;


    public Janitor(String name, String surname, IStringRefactor<String> iStringRefactor) {
        super(name, surname, iStringRefactor);
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if(salary >= this.salary) {
            this.salary = salary;
        } else {
            throw new ValidationException("Salary should be larger than actually salary");
        }
    }


    @Override
    public int calculateSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Janitor{" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                "salary=" + salary +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Janitor janitor = (Janitor) o;
        return salary == janitor.salary;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(salary);
    }
}
