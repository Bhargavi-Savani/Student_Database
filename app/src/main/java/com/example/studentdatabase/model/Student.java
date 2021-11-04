package com.example.studentdatabase.model;

import java.util.ArrayList;

public class Student implements Cloneable {
    String id;
    String studentId;
    String studentName;
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
        ArrayList<Subject> sub1 = new ArrayList<>();
        ArrayList<Subject> sub2 = new ArrayList<>();

        sub1.add(new Subject("CE143","Computer Concepts & Programming (TH)"));
        sub1.add(new Subject("CE143(P)","Computer Concepts & Programming (PR)"));
        sub1.add(new Subject("EE145","Basics of Electronics & Electrical Engineering (TH)"));
        sub1.add(new Subject("EE145(P)","Basics of Electronics & Electrical Engineering (PR)"));
        sub1.add(new Subject("HS101.02A","Communicative English (PR)"));
        sub1.add(new Subject("IT144","ICT Workshop (PR)"));
        sub1.add(new Subject("MA143","Engineering Mathematics-I (TH)"));
        sub1.add(new Subject("PY142","Engineering Physics - I (PR)"));

        sub2.add(new Subject("CE144","Object Oriented Programming with C++ (TH)"));
        sub2.add(new Subject("CE144(P)","Object Oriented Programming with C++ (PR)"));
        sub2.add(new Subject("CL144.02A","Environmental Sciences (PR)"));
        sub2.add(new Subject("HS202","Liberal Arts (PR)"));
        sub2.add(new Subject("MA144","Engineering Mathematics-II (TH)"));
        sub2.add(new Subject("ME145","Elements of Engineering (TH)"));
        sub2.add(new Subject("ME145(P)","Elements of Engineering (PR)"));
        sub2.add(new Subject("PY143","Engineering Physics - II"));

        s1 = new Semester(1, sub1);
        s2 = new Semester(2, sub2);
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
