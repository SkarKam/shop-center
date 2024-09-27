package com.solvd.laba.models.persons;

import com.solvd.laba.interfaces.lambdas.IStringRefactor;

import java.util.Objects;
import java.util.function.Supplier;

public abstract class Person {

    protected String name;
    protected String surname;

    public Person(String name, String surname, IStringRefactor<String> iStringRefactor) {
        this.name = iStringRefactor.refactor(name);
        this.surname = iStringRefactor.refactor(surname);
    }


    public void refactorName(IStringRefactor<String> refactor){
           setName(refactor.refactor(getName()));
           setSurname(refactor.refactor(getSurname()));

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.isBlank()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(!surname.isBlank()) {
            this.surname = surname;
        } else {
            throw new IllegalArgumentException("Surname cannot be blank.");
        }
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
