package com.example.studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class view_result_intermediate extends AppCompatActivity {

    static String Sender = "com.example.studentdatabase.ID";
    static String INPUT_SEMESTER = "com.example.studentdatabase.Semester";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result_intermediate);
    }

    public void page_transfer(View view){
        Intent intent = new Intent(this,view_result.class);
        EditText Student_Id = findViewById(R.id.VR_Id_plaintext);
        EditText Semester = findViewById(R.id.VR_SemValue);
        String Id = Student_Id.getText().toString();
        String Input_sem = Semester.getText().toString();
        intent.putExtra(Sender,Id);
        intent.putExtra(INPUT_SEMESTER,Input_sem);
        startActivity(intent);
    }
}