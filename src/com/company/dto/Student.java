package com.company.dto;

public class Student extends Person {

    public Student(int id, String personName) {
        super(id, personName);
    }

    private int belongToClass;

    public int getBelongToClass() {
        return belongToClass;
    }

    public void setBelongToClass(int belongToClass) {
        this.belongToClass = belongToClass;
    }

    public Student(int id, String personName, int belongToClass) {
        super(id, personName);
        this.belongToClass = belongToClass;
    }

    @Override
    public String toString() {
        return "Student{" +
                " Student ID:" + this.getId() +
                " Student Name:" + this.getPersonName() +
                " belongToClass=" + belongToClass +
                '}';
    }
}
