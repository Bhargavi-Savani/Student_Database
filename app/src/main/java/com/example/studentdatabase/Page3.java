package com.example.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Page3 extends AppCompatActivity {

    public static final String Student_ID = "com.example.studentdatabase.Page3";
    String Id = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        Intent intent = getIntent();
        Id = intent.getStringExtra(MainActivity.MSG);
    }



    public void Student_profile(View view){
        Intent Student_intent = new Intent(this,StudentProfile.class);
        Student_intent.putExtra(Student_ID,Id);
        startActivity(Student_intent);
    }

    public void Result(View view){
        Intent Result_intent = new Intent(this,Result_home.class);
        Result_intent.putExtra(Student_ID,Id);
        startActivity(Result_intent);
    }

    public void Syllabus(View view){
        Intent Syllabus_intent = new Intent(this,Syllabus.class);
        Syllabus_intent.putExtra(Student_ID,Id);
        startActivity(Syllabus_intent);
    }
}