package com.example.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class view_result_intermediate extends AppCompatActivity {

    static String Sender = "com.example.studentdatabase.ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result_intermediate);
    }

    public void page_transfer(View view){
        Intent intent = new Intent(this,view_result.class);
        EditText Student_Id = findViewById(R.id.Id_plaintext);
        String Id = Student_Id.getText().toString();
        intent.putExtra(Sender,Id);
        startActivity(intent);
    }
}