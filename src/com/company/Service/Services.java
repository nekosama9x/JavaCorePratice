package com.company.Service;

import com.company.DTO.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface doServices {
    public List<? extends Person> getListPerson(String path, boolean isStudent);


    public void doCleaning(int classID, List<Student> list);

    public void cleaning(int classID, List<Student> list);

}

public class Services implements doServices {
    private AtomicInteger class1 = new AtomicInteger(0);
    private AtomicInteger class2 = new AtomicInteger(0);
    private AtomicInteger class3 = new AtomicInteger(0);
    private AtomicInteger class4 = new AtomicInteger(0);

    @Override
    public List<? extends Person> getListPerson(String path, boolean isStudent) {
        // su dung 1 method voi 2 kieu Object khac nhau
        // 2 Object deu` la con cua 1 Object Person.
        // mục đích là có thể tái sử dụng method để lấy dữ liệu từ file về với các Object khác nhau
        List<? extends Person> listPerson;
        List<Student> listStudent = null;
        List<Teacher> listTeacher = null;


        List<String> inputList;
        try {
            Path paths = Paths.get(path);
            Stream<String> steam = Files.lines(paths);
            inputList = steam.flatMap(s -> Arrays.stream(s.split(" - "))).collect(Collectors.toList());
            // lay du lieu txt theo Paths.
            if (isStudent) {
                listStudent = new ArrayList<>();

                int count = 0;
                while (count < inputList.size()) {

                    int classId = Integer.parseInt(inputList.get(count));
                    String name = inputList.get(++count);
                    int id = Integer.parseInt(inputList.get(++count));
                    Student student = new Student(id, name, classId);
                    listStudent.add(student);
                    count++;
                }
            } else {
                listTeacher = new ArrayList<>();
                int count = 0;

                while (count < inputList.size()) {
                    HashSet<Integer> classIdWorking = new HashSet<>();
                    // sử dụng Hashset (equal + HashCode) để bảo đảm không trùng lặp

                    int classId = Integer.parseInt(inputList.get(count));
                    String name = inputList.get(++count);
                    int id = Integer.parseInt(inputList.get(++count));
                    count++;

                    if (listTeacher.isEmpty()) {
                        classIdWorking.add(classId);
                        Teacher teacher = new Teacher(id, name, classIdWorking);
                        listTeacher.add(teacher);
                    } else {
                        final boolean[] insert = {false};
                        listTeacher.forEach(teacher -> {
                            if (teacher.getId() == id) {
                                teacher.getListClassWorking().add(classId);
                            } else {
                                insert[0] = true;
                            }
                        });

                        if (insert[0]) {
                            classIdWorking.add(classId);
                            Teacher teacher = new Teacher(id, name, classIdWorking);
                            listTeacher.add(teacher);
                            insert[0] = false;
                        }
                    }


                }

            }
        } catch (IOException ex) {
            ex.getMessage();
        }
        if (isStudent) {
            listPerson = listStudent;
        } else {
            listPerson = listTeacher;
        }

        return listPerson;
    }


    private final ScheduledExecutorService es = Executors.newScheduledThreadPool(4);

    @Override
    public void doCleaning(int classID, List<Student> list) {
        // chạy multiThread mà số lần cleaning không trùng lặp.
        // thread dừng lại khi đến yêu cầu.
        final List<Student> listStudentOfClass = list.stream().filter(student -> student.getBelongToClass() == classID).collect(Collectors.toList());


        for (int i = 0; i < 10; i++) {
            es.scheduleAtFixedRate(() -> {
                cleaning(classID, listStudentOfClass);
            }, 0, 1, TimeUnit.SECONDS);
        }
    }

    @Override
    public void cleaning(int classId, List<Student> list) {
        Random rand = new Random();

        String name = list.get(rand.nextInt(list.size())).getPersonName();
        if (classId == 101) {
            System.out.println("Numberof Time cleaning in class 1 :" + class1.incrementAndGet() + " By: " + name);
            if (class1.get() == 50) {
                System.out.println("closed + " + classId);
                es.shutdown();
            }
        } /*else if (classId == 102) {
            System.out.println("Numberof Time cleaning in class 2 :" + class2.incrementAndGet() + " By: " + name);
            if (class2.get() == 10) {
                System.out.println("closed +" + classId);
                es.shutdown();
            }
        }*/


    }

}
