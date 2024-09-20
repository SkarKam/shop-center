package com.solvd.laba.models.persons;

import com.solvd.laba.interfaces.IStringRefactor;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public abstract class Person {

    protected String name;
    protected String surname;

    public Person(String name, String surname) {
        IStringRefactor<String, String> iStringRefactor = StringUtils::capitalize;

        this.name = iStringRefactor.refactor(name);
        this.surname = iStringRefactor.refactor(surname);
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
