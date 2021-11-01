package com.example.studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Page3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
    }

    // Transfers to STUDENT PROFILE PAGE
    public void Student_profile(View view){
        Intent Student_intent = new Intent(this,StudentProfile.class);
        startActivity(Student_intent);
    }

    // Transfers to RESULT PAGE
    public void Result(View view){
        Intent Result_intent = new Intent(this,Result_home.class);
        startActivity(Result_intent);
    }

    // Transfers to SYLLABUS PAGE
    public void Syllabus(View view){
        Intent Syllabus_intent = new Intent(this,Syllabus.class);
        startActivity(Syllabus_intent);
    }
}