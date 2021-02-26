package com.company;

import com.company.DTO.Student;
import com.company.DTO.Teacher;
import com.company.Service.Services;


import java.util.List;


public class Main {
    private static List<Student> listStudent = null;
    private static List<Teacher> listTeacher = null;

    public static void main(String[] args) throws InterruptedException {
        // write your code here

        Services sv = new Services();
        String pathStudent = "ListStudent.txt";
        String pathTeacher = "ListTeacher.txt";

        listStudent = (List<Student>) sv.getListPerson(pathStudent, true);
        listTeacher = (List<Teacher>) sv.getListPerson(pathTeacher, false);
        // tập luyện Generic, Collections, IO bằng cách lấy dữ liệu từ file txt và chia cắt ra các List trong các
        // trường hợp của Generic và Collection
        System.out.println("List Student:");
        listStudent.forEach(System.out::println);
        System.out.println("List Teachers:");
        listTeacher.forEach(System.out::println);
        String url = "CleaningFile.txt";
        sv.doCleaning(101, listStudent);
        //    sv.doCleaning(102, listStudent);
    }
}
