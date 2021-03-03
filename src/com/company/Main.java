package com.company;

import com.company.dto.Student;
import com.company.dto.Teacher;
import com.company.services.ServicesImp;


import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static List<Student> listStudent = null;
    private static List<Teacher> listTeacher = null;
    private static HashMap<Integer, String> listClassRoom = null;

    public static void main(String[] args) throws InterruptedException {
        // write your code here
        // pratice Generic, Collection, IO, Exception and Thread
        ServicesImp sv = new ServicesImp();
        String pathStudent = "ListStudent.txt";
        String pathTeacher = "ListTeacher.txt";
        String pathClassRoom = "ListClassRoom.txt";

        //Collection + NIO + Generics
        listStudent = (List<Student>) sv.getListPerson(pathStudent, true);
        listTeacher = (List<Teacher>) sv.getListPerson(pathTeacher, false);




        // HashMap and IO Read pratice + CheckedException.
        listClassRoom = sv.getClassRoom(pathClassRoom);
        System.out.println("List Classroom");
        listClassRoom.forEach((integer, s) -> System.out.println("Class Room: " + integer + " - Name: " + s));


        System.out.println("List Student:");
        listStudent.forEach(System.out::println);
        System.out.println("List Teachers:");
        listTeacher.forEach(System.out::println);

        // IO Write pratice
        sv.insertClassRoom(pathClassRoom);

        // calling a null.
        //catch exception
        try {
            sv.checkException();
        } catch (NullPointerException ex) {
            System.out.println("Exception with null");
            ex.printStackTrace();
        }


             sv.doCleaning(101, listStudent);
        Thread.sleep(3000);

    }
}
