package com.example.studentdatabase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Syllabus extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String[] items={"First","Second","Third","Fourth"};     // STRING ARRAY FOR SPINNER

    TextView Link;
    TextView textView2;

    private Spinner DropDown;       // DROPDOWN MENU OBJECT

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        // FETCHING THE ID OF VIEWS
        Link = findViewById(R.id.textView53);
        textView2 = findViewById(R.id.textView52);

        Link.setClickable(true);    // MAKING TEXT VIEW CLICKABLE

        DropDown = findViewById(R.id.dropdown_menu);

        DropDown.setOnItemSelectedListener(this);

        // CREATING THE DROPDOWN
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DropDown.setAdapter(adapter);
    }

    // OVERRIDING DROPDOWN METHOD
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() ==  R.id.dropdown_menu){
            String Semester = adapterView.getItemAtPosition(i).toString();  // FETCHING THE SELECTED SEMESTER VALUE

            textView2.setText("Link for " + Semester + " Syllabus is");

            // SETTING LINKS DEPENDING UPON THE SEMESTER CHOSEN
            if(Semester.equals("First")){
                Link.setText(getResources().getString(R.string.FirstYear));
                Link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://drive.google.com/file/d/19OuYACxsXcR_nXs2kx0tgzpOVDUKyNG4/view?usp=drivesdk"); // SETTING THE LINK VALUE
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);     // STARING THE ACTIVITY TO TRANSFER TO DRIVE
                        startActivity(intent);
                    }
                });


            }
            else if(Semester.equals("Second")){
                Link.setText((getResources().getString(R.string.SecondYear)));
                Link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://drive.google.com/file/d/19KBMdPFYNTeH0LQ7GWomr4rEFUTTGtn4/view?usp=drivesdk");
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                });
            }
            else if(Semester.equals("Third")){
                Link.setText(getResources().getString(R.string.ThirdYear));
                Link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://drive.google.com/file/d/19OqZ0LFUu0Jjg2uRsF9CAysaiElXh-uu/view?usp=drivesdk");
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                });
            }
            else if(Semester.equals("Fourth")){
                Link.setText(getResources().getString(R.string.FourthYear));
                Link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://drive.google.com/file/d/19KaY4lvYVgHSaX0PupQYuwnQbmBlcpUK/view?usp=drivesdk");
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                });

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}