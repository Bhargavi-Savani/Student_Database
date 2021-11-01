package com.example.studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Result_home extends AppCompatActivity {

    public static final String Sem_Sender = "com.example.studentdatabase.sem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_home);
    }

    // FUNCTION OF SEMESTER 1 BUTTON
    public void sem1_result(View view){
        String sem = "1";
        Intent sem1_intent = new Intent(this,Result.class);
        sem1_intent.putExtra(Sem_Sender,sem);
        startActivity(sem1_intent);
    }
    // FUNCTION OF SEMESTER 1 BUTTON
    public void sem2_result(View view){
        String sem = "2";
        Intent sem1_intent = new Intent(this,Result.class);
        sem1_intent.putExtra(Sem_Sender,sem);
        startActivity(sem1_intent);
    }
}