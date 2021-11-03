package com.example.studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class add_student_intermediate extends AppCompatActivity {

    // KEYS TO SEND DATA
    static final String AD_ID_SENDER = "com.example.studentdatabase.AD.ID";
    static final String AD_SEM_SENDER = "com.example.studentdatabase.AD.SEM";
    static final String AD_NAME_SENDER = "com.example.studentdatabase.AD.NAME";

    EditText ad_id;
    EditText ad_name;
    EditText ad_sem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_intermediate);
    }

    public void AD_BUTTON(View view){
        // GETTING ID`S OF VIEWS
        ad_id = findViewById(R.id.AD_ID);
        ad_name = findViewById(R.id.AD_S_NAME);
        ad_sem = findViewById(R.id.AD_S_SEM);

        // CREATING AN INTENT TO SEND DATA AND START NEW ACTIVITY
        Intent intent = new Intent(this,add_student.class);

        // SENDING DATA AND STARTING NEW ACTIVITY
        intent.putExtra(AD_ID_SENDER,ad_id.getText().toString());
        intent.putExtra(AD_NAME_SENDER,ad_name.getText().toString());
        intent.putExtra(AD_SEM_SENDER,ad_sem.getText().toString());
        startActivity(intent);
    }
}