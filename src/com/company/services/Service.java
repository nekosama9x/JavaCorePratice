package com.company.services;

import com.company.dto.ClassRoom;
import com.company.dto.Person;
import com.company.dto.Student;

import java.util.HashMap;
import java.util.List;

public interface Service {
    public List<? extends Person> getListPerson(String path, boolean isStudent);

    public HashMap<Integer, String> getClassRoom(String path);

    public void doCleaning(int classID, List<Student> list);

    public void checkException();

    public void cleaning(int classID, List<Student> list);
    public void insertClassRoom(String path);

}
