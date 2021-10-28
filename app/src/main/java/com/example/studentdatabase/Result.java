package com.example.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    String Id = new String();
    String Sem = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        Id = intent.getStringExtra(MainActivity.MSG);
        Sem = intent.getStringExtra(Result_home.Sem_Sender);

        TextView Name = findViewById(R.id.textView48);
        TextView ID = findViewById(R.id.textView50);

        TextView Sub1 = findViewById(R.id.textView4);
        TextView Sub2 = findViewById(R.id.textView7);
        TextView Sub3 = findViewById(R.id.textView10);
        TextView Sub4 = findViewById(R.id.textView13);
        TextView Sub5 = findViewById(R.id.textView16);
        TextView Sub6 = findViewById(R.id.textView19);
        TextView Sub7 = findViewById(R.id.textView22);
        TextView Sub8 = findViewById(R.id.textView25);
        TextView Sub9 = findViewById(R.id.textView28);

        TextView Sub1_credit = findViewById(R.id.textView5);
        TextView Sub2_credit = findViewById(R.id.textView8);
        TextView Sub3_credit = findViewById(R.id.textView11);
        TextView Sub4_credit = findViewById(R.id.textView14);
        TextView Sub5_credit = findViewById(R.id.textView17);
        TextView Sub6_credit = findViewById(R.id.textView20);
        TextView Sub7_credit = findViewById(R.id.textView23);
        TextView Sub8_credit = findViewById(R.id.textView26);
        TextView Sub9_credit = findViewById(R.id.textView29);

        TextView Sub1_grade = findViewById(R.id.textView6);
        TextView Sub2_grade = findViewById(R.id.textView9);
        TextView Sub3_grade = findViewById(R.id.textView12);
        TextView Sub4_grade = findViewById(R.id.textView15);
        TextView Sub5_grade = findViewById(R.id.textView18);
        TextView Sub6_grade = findViewById(R.id.textView21);
        TextView Sub7_grade = findViewById(R.id.textView24);
        TextView Sub8_grade = findViewById(R.id.textView27);
        TextView Sub9_grade = findViewById(R.id.textView30);

        TextView Semester = findViewById(R.id.textView35);
        TextView Total_Credit_sem = findViewById(R.id.textView36);
        TextView Earned_credit_sem = findViewById(R.id.textView37);
        TextView SGPA = findViewById(R.id.textView38);

        TextView Cumulative = findViewById(R.id.textView43);
        TextView Total_Credit = findViewById(R.id.textView44);
        TextView Earned_credit = findViewById(R.id.textView45);
        TextView CGPA = findViewById(R.id.textView46);


//        TODO Create Object and API call
    }


}