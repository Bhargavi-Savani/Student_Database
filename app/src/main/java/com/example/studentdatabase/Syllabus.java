package com.example.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class Syllabus extends AppCompatActivity {

    String[] items={"First","Second","Third","Fourth"};
    AutoCompleteTextView autoCompleteText1;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        autoCompleteText1 = findViewById(R.id.autoCompleteText1);

        adapterItems = new ArrayAdapter<String>(this,R.layout.dropdown_item,items);
        autoCompleteText1.setAdapter(adapterItems);

        autoCompleteText1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();

            }
        });
    }
}