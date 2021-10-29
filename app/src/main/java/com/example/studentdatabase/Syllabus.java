package com.example.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Syllabus extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String[] items={"Select","First","Second"};
    String[] SyllabusLink = new String[4];

    TextView textView1;
    TextView textView2;

    private Spinner DropDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        textView1 = findViewById(R.id.textView53);
        textView2 = findViewById(R.id.textView52);

        DropDown = findViewById(R.id.dropdown_menu);

        DropDown.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DropDown.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        SyllabusLink[0] = "First";
        SyllabusLink[1] = "Second";
        SyllabusLink[2] = "Third";
        SyllabusLink[3] = "Fourth";

        if(adapterView.getId() ==  R.id.dropdown_menu){
            String Semester = adapterView.getItemAtPosition(i).toString();

            textView2.setText("Link for " + Semester + "Syllabus is");

            if(Semester.equals("First")){
                textView1.setText(SyllabusLink[0]);
            }
            else if(Semester.equals("Second")){
                textView1.setText(SyllabusLink[0]);
            }
            if(Semester.equals("Third")){
                textView1.setText(SyllabusLink[0]);
            }
            if(Semester.equals("Fourth")){
                textView1.setText(SyllabusLink[0]);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}