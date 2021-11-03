package com.example.studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class modify_student_intermediate extends AppCompatActivity {

    static final String msd_id = "com.example.studentdatabase.msd.id";      // key to send id
    static final String msd_sem = "com.example.studentdatabase.msd.sem";        // key to send semester

    TextView msd_student_id;
    TextView msd_semester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_student_intermediate);
    }

    public void msg_send_data(View view){

        // FINDING ID OF BOTH INPUT VIEWS
        msd_student_id = findViewById(R.id.MSD_ID_VALUE);
        msd_semester = findViewById(R.id.MSD_SEM_VALUE);

        // CREATING AN INTENT TO CHANGE ACTIVITY AND TO SEND THE SEMESTER AND ID SELECTED BY TEACHER
        Intent intent = new Intent(this,modify_student.class);

        // SENDING DATA AND STARTING NEW ACTIVITY
        intent.putExtra(msd_id,msd_student_id.getText().toString());
        intent.putExtra(msd_sem,msd_semester.getText().toString());
        startActivity(intent);
    }
}