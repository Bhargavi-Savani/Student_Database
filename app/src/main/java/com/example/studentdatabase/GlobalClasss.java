package com.example.studentdatabase;

import android.app.Application;

public class GlobalClasss extends Application {
    static String Student_Id;   // GLOBAL VARIABLE TO ACCESS STUDENT ID IN ALL ACTIVITY OF APPLICATION
    static String Role;     // GLOBAL VARIABLE TO ACCESS ROLE IN ALL ACTIVITY OF APPLICATION
    final static String URL = "https://crud-api-student.herokuapp.com/api/student";
}
