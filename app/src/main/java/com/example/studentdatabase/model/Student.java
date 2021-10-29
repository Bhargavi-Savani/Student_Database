package com.example.studentdatabase.model;

public class Student implements Cloneable {
    String id = "15";
    String studentId = "52";
    String studentName = "xyz";
    Semester s1;
    Semester s2;

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", s1=" + s1 +
                ", s2=" + s2 +
                '}';
    }

    public Student() {
    }

    public Student(String studentId, String studentName, Semester s1, Semester s2) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.s1 = s1;
        this.s2 = s2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Semester getS1() {
        return s1;
    }

    public void setS1(Semester s1) {
        this.s1 = s1;
    }

    public Semester getS2() {
        return s2;
    }

    public void setS2(Semester s2) {
        this.s2 = s2;
    }

}
