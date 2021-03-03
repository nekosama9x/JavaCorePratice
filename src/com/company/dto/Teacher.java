package com.company.dto;

import java.util.HashSet;
import java.util.Objects;

public class Teacher extends Person{
    public Teacher(int id, String personName) {
        super(id, personName);
    }
    private HashSet<Integer> listClassWorking;

    public Teacher(int id, String personName, HashSet<Integer> listClassWorking) {
        super(id, personName);
        this.listClassWorking = listClassWorking;
    }

    public HashSet<Integer> getListClassWorking() {
        return listClassWorking;
    }

    public void setListClassWorking(HashSet<Integer> listClassWorking) {
        this.listClassWorking = listClassWorking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(listClassWorking, teacher.listClassWorking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), listClassWorking);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                " Teacher Id: " + this.getId() +
                " Teacher Name: " + this.getPersonName() +
                " listClassWorking=" + listClassWorking +
                '}';
    }
}
