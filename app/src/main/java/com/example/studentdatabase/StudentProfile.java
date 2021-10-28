package com.example.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StudentProfile extends AppCompatActivity {

    String Id = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        Intent  intent = getIntent();
        Id = intent.getStringExtra(Page3.Student_ID);

        TextView Student_Id = findViewById(R.id.textView12);
        TextView Name = findViewById(R.id.textView13);
        TextView Batch = findViewById(R.id.textView14);
        TextView Semester = findViewById(R.id.textView15);
        TextView Programme = findViewById(R.id.textView16);
        TextView College = findViewById(R.id.textView17);

//    TODO Api call and convert it into object

        StudentDetails Profile_obj = new StudentDetails();

        Batch.setText(Profile_obj.getBatch());
        Semester.setText(Profile_obj.getSemester());
        Programme.setText(Profile_obj.getProgramme());
        College.setText(Profile_obj.getCollege());
    }


}