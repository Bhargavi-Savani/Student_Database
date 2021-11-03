package com.example.studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentdatabase.model.Semester;
import com.example.studentdatabase.model.Student;
import com.example.studentdatabase.model.Subject;

import java.util.ArrayList;
import java.util.Arrays;

public class modify_student extends AppCompatActivity {

    final int Size = 3;
    String ID;
    String Sem;
    EditText[] CourseCodeValue;
    EditText[] CourseGradeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_student);

        Intent intent = getIntent();
        Sem = intent.getStringExtra(modify_student_intermediate.msd_sem);
        ID = intent.getStringExtra(modify_student_intermediate.msd_id);

        CourseCodeValue = new EditText[Size];
        CourseGradeValue = new EditText[Size];


        CourseCodeValue[0] = findViewById(R.id.MD_COURSE_VALUE1);
        CourseCodeValue[1] = findViewById(R.id.MD_COURSE_VALUE2);
        CourseCodeValue[2] = findViewById(R.id.MD_COURSE_VALUE3);

        CourseGradeValue[0] = findViewById(R.id.MD_GRADE_VALUE1);
        CourseGradeValue[1] = findViewById(R.id.MD_GRADE_VALUE2);
        CourseGradeValue[2] = findViewById(R.id.MD_GRADE_VALUE3);
    }

    public void ModifyButton(View view){

        Subject[] subject = new Subject[3];
        Semester semester = new Semester();
        Student student = new Student();

        for (int i = 0; i < Size; i++) {
            subject[i].setSubCode(CourseCodeValue[i].getText().toString());
            subject[i].setGrade(CourseGradeValue[i].getText().toString());
            subject[i].setGradePoint(Integer.parseInt(add_student.GradePoints.valueOf(CourseGradeValue[i].getText().toString()).toString()));
        }
        ArrayList<Subject> var = new ArrayList<Subject>(Arrays.asList(subject));
        semester.setSubjects(var);

        semester.setSem(Integer.parseInt(Sem));
        if(Sem.equals("1")) {
            student.setS1(semester);
        }
        else {
            student.setS2(semester);
        }

        // TODO api call
    }
}