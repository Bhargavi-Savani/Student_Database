package com.example.studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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
        String type="Student";      // Defines the role of user
        intent.putExtra(Role,type);     // sending the type of role to next page
        startActivity(intent);
    }

    // function to declare type of login as Faculty
    public void FacultyLogin(View view){
        Intent intent = new Intent(this,MainActivity.class);
        String type="Faculty";      // Defines the role of user
        intent.putExtra(Role,type);     // sending the type of role to next page
        startActivity(intent);
    }
}