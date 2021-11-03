package com.example.studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentdatabase.model.Semester;
import com.example.studentdatabase.model.Student;
import com.example.studentdatabase.model.Subject;

import java.util.ArrayList;
import java.util.Arrays;

public class add_student extends AppCompatActivity {

    // Variable for max size of subject

    final int Size = 8;
    String ID;
    String Semester;
    String StudentName;
    Student student;
    TextView[] SubjectCode;
    EditText[] SubjectCredit;
    EditText[] SubjectGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // Creating array of view variables
        SubjectCode = new TextView[Size];
        SubjectCredit = new EditText[Size];
        SubjectGrade = new EditText[Size];

        // collecting course view id
        SubjectCode[0] = findViewById(R.id.AD_SUB1);
        SubjectCode[1] = findViewById(R.id.AD_SUB2);
        SubjectCode[2] = findViewById(R.id.AD_SUB3);
        SubjectCode[3] = findViewById(R.id.AD_SUB4);
        SubjectCode[4] = findViewById(R.id.AD_SUB5);
        SubjectCode[5] = findViewById(R.id.AD_SUB6);
        SubjectCode[6] = findViewById(R.id.AD_SUB7);
        SubjectCode[7] = findViewById(R.id.AD_SUB8);

        // collecting Credit view id
        SubjectCredit[0] = findViewById(R.id.AD_CREDIT1);
        SubjectCredit[1] = findViewById(R.id.AD_CREDIT2);
        SubjectCredit[2] = findViewById(R.id.AD_CREDIT3);
        SubjectCredit[3] = findViewById(R.id.AD_CREDIT4);
        SubjectCredit[4] = findViewById(R.id.AD_CREDIT5);
        SubjectCredit[5] = findViewById(R.id.AD_CREDIT6);
        SubjectCredit[6] = findViewById(R.id.AD_CREDIT7);
        SubjectCredit[7] = findViewById(R.id.AD_CREDIT8);

        // collecting Grade view id
        SubjectGrade[0] = findViewById(R.id.AD_GRADE1);
        SubjectGrade[1] = findViewById(R.id.AD_GRADE2);
        SubjectGrade[2] = findViewById(R.id.AD_GRADE3);
        SubjectGrade[3] = findViewById(R.id.AD_GRADE4);
        SubjectGrade[4] = findViewById(R.id.AD_GRADE5);
        SubjectGrade[5] = findViewById(R.id.AD_GRADE6);
        SubjectGrade[6] = findViewById(R.id.AD_GRADE7);
        SubjectGrade[7] = findViewById(R.id.AD_GRADE8);

        Intent intent = getIntent();
        StudentName = intent.getStringExtra(add_student_intermediate.AD_NAME_SENDER);
        ID = intent.getStringExtra(add_student_intermediate.AD_ID_SENDER);
        Semester = intent.getStringExtra(add_student_intermediate.AD_SEM_SENDER);


        student = new Student();

        System.out.println(student);

        if(Semester.equals("1")){
            for (int i = 0; i < Size; i++) {
                SubjectCode[i].setText(student.getS1().getSubjects().get(i).getSubCode());
            }
        }
        else {
            for (int i = 0; i < Size; i++) {
                SubjectCode[i].setText(student.getS2().getSubjects().get(i).getSubCode());
            }
        }

    }

    public void Get_Input(View view){
        Semester semester = new Semester();
        Subject[] subject = new Subject[8];

for(int i =0; i < Size; i++)
{
     subject[i] = new Subject();
}

        for (int i = 0; i < Size; i++) {
            subject[i].setCredit(Double.parseDouble(SubjectCredit[i].getText().toString()));
            subject[i].setGrade(SubjectGrade[i].getText().toString());
            subject[i].setGradePoint(Integer.parseInt(GradePoints.valueOf(SubjectGrade[i].getText().toString()).toString()));
        }

        ArrayList<Subject> var = new ArrayList<Subject>(Arrays.asList(subject));
        semester.setSubjects(var);

        if(Semester.equals("1")) {
            semester.setSem(Integer.parseInt(Semester));
            student.setS1(semester);

            // TODO api call to send sem1 object
        }
        else {
            // TODO do api call for sem1 object and then add the sem2 data fetched to you called object and send it back to api
        }

    }

    enum GradePoints {
        FF(0),DD(4),CD(5),CC(6),BC(7),BB(8),AB(9),AA(10);
        int num = 0;
        GradePoints(int x){
            this.num = x;
        }

        @Override
        public String toString(){
            return Integer.toString(num);
        }

        public int getint(String str){
            num = GradePoints.valueOf(str).ordinal();
            return num;
        }
    }


}
