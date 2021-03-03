package com.company.dto;

import java.util.Objects;

public class Person {
    private int Id;
    private String PersonName;

    public Person(int id, String personName) {
        Id = id;
        PersonName = personName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Id == person.Id && Objects.equals(PersonName, person.PersonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, PersonName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "Id=" + Id +
                ", PersonName='" + PersonName + '\'' +
                '}';
    }
}
