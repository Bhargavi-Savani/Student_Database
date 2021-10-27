package com.example.studentdatabase.model;

public class Subject {
    String subCode;
    String subName;
    double credit;
    String grade;
    int gradePoint;

    public Subject() {
    }

    public Subject(String subCode, String subName, double credit, String grade, int gradePoint) {
        this.subCode = subCode;
        this.subName = subName;
        this.credit = credit;
        this.grade = grade;
        this.gradePoint = gradePoint;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(int gradePoint) {
        this.gradePoint = gradePoint;
    }
}
