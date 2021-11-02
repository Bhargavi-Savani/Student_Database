package com.example.studentdatabase.model;

import java.util.ArrayList;

public class Semester {
    int sem;
    ArrayList<Subject> subjects;
    public Semester() {
    }

    public Semester(int sem, ArrayList<Subject> subjects) {
        this.sem = sem;
        this.subjects = subjects;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

}
