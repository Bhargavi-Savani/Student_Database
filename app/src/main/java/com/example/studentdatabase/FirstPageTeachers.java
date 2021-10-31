package com.example.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FirstPageTeachers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page_teachers);
    }

    public void add_detail(View view){
        Intent intent = new Intent(this,add_student_intermediate.class);
        startActivity(intent);
    }

    public void delete_detail(View view){
        Intent intent = new Intent(this,delete_student.class);
        startActivity(intent);
    }

    public void modify_detail(View view){
        Intent intent = new Intent(this,modify_student_intermediate.class);
        startActivity(intent);
    }

    public void view_result(View view){
        Intent intent = new Intent(this,view_result_intermediate.class);
        startActivity(intent);
    }

}