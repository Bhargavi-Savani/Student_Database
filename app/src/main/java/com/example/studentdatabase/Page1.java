package com.example.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Page1 extends AppCompatActivity {
    public static final String Role = "com.example.studentdatabase.Page1";     //Key for next page to get the type of login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
    }

    // function to declare type of login as student
    public void StudentLogin (View view){

        Intent intent = new Intent(this,MainActivity.class);
        String type="Student";
        intent.putExtra(Role,type);
        startActivity(intent);
    }

    // function to declare type of login as Faculty
    public void FacultyLogin(View view){
        Intent intent = new Intent(this,MainActivity.class);
        String type="Faculty";
        intent.putExtra(Role,type);
        startActivity(intent);
    }
}