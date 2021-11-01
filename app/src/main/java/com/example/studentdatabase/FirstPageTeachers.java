package com.example.studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FirstPageTeachers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page_teachers);
    }

    // TRANSFERS TO ADD STUDENT INTERMEDIATE PAGE
    public void add_detail(View view){
        Intent intent = new Intent(this,add_student_intermediate.class);
        startActivity(intent);
    }

    // TRANSFERS TO DELETE STUDENT PAGE
    public void delete_detail(View view){
        Intent intent = new Intent(this,delete_student.class);
        startActivity(intent);
    }

    // TRANSFER TO MODIFY STUDENT INTERMEDIATE PAGE
    public void modify_detail(View view){
        Intent intent = new Intent(this,modify_student_intermediate.class);
        startActivity(intent);
    }

    // TRANSFERS TO VIEW RESULT INTERMEDIATE PAGE
    public void view_result(View view){
        Intent intent = new Intent(this,view_result_intermediate.class);
        startActivity(intent);
    }

}