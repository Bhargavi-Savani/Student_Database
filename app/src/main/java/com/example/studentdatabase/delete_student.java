package com.example.studentdatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class delete_student extends AppCompatActivity {

    EditText d_id;
    TextView display_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);
    }

    public void DELETE_STUDENT(View view){
        // Getting id of view
        d_id = findViewById(R.id.DS_S_ID);
        display_view = findViewById(R.id.DISPLAY_VIEW);

        String ID = d_id.getText().toString();

        // TODO

        // ADD CODE B/W THIS TWO COMMENTS

        display_view.setText("Student Profile deleted");
    }

}