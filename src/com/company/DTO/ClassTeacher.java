package com.company.DTO;

public class ClassTeacher {
    private int ClassRoomId;
    private int TeacherId;

    public ClassTeacher(int classRoomId, int teacherId) {
        ClassRoomId = classRoomId;
        TeacherId = teacherId;
    }

    public int getClassRoomId() {
        return ClassRoomId;
    }

    public void setClassRoomId(int classRoomId) {
        ClassRoomId = classRoomId;
    }

    public int getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(int teacherId) {
        TeacherId = teacherId;
    }
}
