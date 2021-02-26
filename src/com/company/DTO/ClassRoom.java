package com.company.DTO;

import java.util.Objects;

public class ClassRoom {
    private int classId;
    private String ClassName;

    public ClassRoom(int classId, String className) {
        this.classId = classId;
        ClassName = className;
    }

    public ClassRoom(int classId) {
        this.classId = classId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "classId=" + classId +
                ", ClassName='" + ClassName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassRoom classRoom = (ClassRoom) o;
        return classId == classRoom.classId && Objects.equals(ClassName, classRoom.ClassName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId);
    }
}
