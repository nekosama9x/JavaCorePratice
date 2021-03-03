package com.company.services;


import com.company.dto.Person;
import com.company.dto.Student;
import com.company.dto.Teacher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ServicesImp implements Service {
    private AtomicInteger class1 = new AtomicInteger(0);

    @Override
    public List<? extends Person> getListPerson(String path, boolean isStudent) {
        // using 1 method with 2 different Object
        // 2 object is belong to Object Person
        // Purpose is to re-used method by extract data from file with different object
        List<? extends Person> listPerson;
        List<Student> listStudent = new ArrayList<>();
        List<Teacher> listTeacher = new ArrayList<>();


        List<String> inputList;
        try {
            Path paths = Paths.get(path);
            Stream<String> steam = Files.lines(paths);
            inputList = steam.flatMap(s -> Arrays.stream(s.split("\\n"))).collect(Collectors.toList());

            // take data from txt with Paths
            // Improve code for future changing

            if (isStudent) {

                List<Student> finalListStudent = listStudent;
                inputList.forEach(s -> {
                    List<String> Lines = Arrays.stream(s.split(" - ")).collect(Collectors.toList());
                    int classId = Integer.parseInt(Lines.get(0));
                    String name = Lines.get(1);
                    int id = Integer.parseInt(Lines.get(2));
                    Student student = new Student(id, name, classId);
                    finalListStudent.add(student);
                });


            } else {
                listTeacher = new ArrayList<>();

                List<Teacher> finalListTeacher = listTeacher;
                inputList.forEach(s -> {
                    List<String> Lines = Arrays.stream(s.split(" - ")).collect(Collectors.toList());
                    HashSet<Integer> classIdWorking = new HashSet<>();
                    int classId = Integer.parseInt(Lines.get(0));
                    String name = Lines.get(1);
                    int id = Integer.parseInt(Lines.get(2));
                    if (finalListTeacher.isEmpty()) {
                        classIdWorking.add(classId);
                        Teacher teacher = new Teacher(id, name, classIdWorking);
                        finalListTeacher.add(teacher);
                    } else {
                        AtomicBoolean flag = new AtomicBoolean(false);
                        finalListTeacher.forEach(teacher -> {
                            if (teacher.getId() == id) {
                                teacher.getListClassWorking().add(classId);
                            } else {
                                flag.set(true);
                            }
                        });

                        if (flag.get()) {
                            classIdWorking.add(classId);
                            Teacher teacher = new Teacher(id, name, classIdWorking);
                            finalListTeacher.add(teacher);
                            flag.set(false);
                        }
                    }
                });
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        listPerson = (isStudent) ? listStudent : listTeacher;

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
            if (class1.get() == 10) {
                System.out.println("closed + " + classId);
                es.shutdown();
            }
        }
    }

    @Override
    public HashMap<Integer, String> getClassRoom(String path) {
        // IO Read
        HashMap<Integer, String> list = new HashMap<>();
        FileInputStream fis = null;
        InputStreamReader reader = null;
        BufferedReader br = null;
        try {
            File paths = new File(path);
            fis = new FileInputStream(paths);
            reader = new InputStreamReader(fis);
            br = new BufferedReader(reader);
            List<String> ListInput = new ArrayList<>();
            try {
                String text;
                while ((text = br.readLine()) != null) {
                    ListInput.add(text);
                    System.out.println("text:" + text);
                }
                ListInput.forEach(s -> {
                    List<String> listsplit = Arrays.asList(s.split(" - ").clone());
                    list.put(Integer.parseInt(listsplit.get(0)), listsplit.get(1));

                });

                // checked Exception
            } catch (IOException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("File not found");

        } finally {
            try {
                if (br != null) br.close();
                if (reader != null) reader.close();
                if (fis != null) fis.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

        return list;
    }

    @Override
    public void checkException() {
        String url = null;
        System.out.println(url.toString());
    }

    @Override
    public void insertClassRoom(String path) {
        // IO write
        HashMap<Integer, String> list = new HashMap<>();
        list.put(106, "C++");
        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        try {
            File paths = new File(path);
            fos = new FileOutputStream(paths, true);
            writer = new OutputStreamWriter(fos);
            bw = new BufferedWriter(writer);
            List<String> output = new ArrayList<>();
            BufferedWriter finalBw = bw;
            list.forEach((integer, s) ->
                    {
                        try {
                            finalBw.write("\n");
                            finalBw.write(integer.toString() + " - " + s.toString());
                            System.out.println(" Write to txt: " + integer + " - " + s);
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    }
            );


        } catch (FileNotFoundException exception) {
            System.out.println("Exception FIle not found");
            exception.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
                if (writer != null) writer.close();
                if (fos != null) fos.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}
