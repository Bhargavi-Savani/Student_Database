package com.example.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Syllabus extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String[] items={"First","Second","Third","Fourth"};

    TextView Link;
    TextView textView2;

    private Spinner DropDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        Link = findViewById(R.id.textView53);
        textView2 = findViewById(R.id.textView52);

        Link.setClickable(true);

        DropDown = findViewById(R.id.dropdown_menu);

        DropDown.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DropDown.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() ==  R.id.dropdown_menu){
            String Semester = adapterView.getItemAtPosition(i).toString();

            textView2.setText("Link for " + Semester + " Syllabus is");

            if(Semester.equals("First")){
                Link.setText(getResources().getString(R.string.FirstYear));
                Link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://drive.google.com/file/d/19OuYACxsXcR_nXs2kx0tgzpOVDUKyNG4/view?usp=drivesdk");
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
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