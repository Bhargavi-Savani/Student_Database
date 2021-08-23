package com.example.studentdatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {


    String Id,Psw;          //Variable to store data.

    EditText UserId;            //Variable to store id`s of view.
    EditText Password;          //Variable to store id`s of view.

    Button SubmitButton;        //Variable to store id`s of view.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserId = findViewById(R.id.UserId);             //saving id of USerID text box.
        Password = findViewById(R.id.Password);         //saving id of password text box..

        SubmitButton = findViewById(R.id.SubButton);    //saving id of submit button.

        // specifies what will happen when submit button is clicked.
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Id = UserId.getText().toString();
                Psw = Password.getText().toString();

                CredentialCheck(Id,Psw);
            }
        });

    }
    private void CredentialCheck(String s1,String s2) {
        LoginDetails logindetails=new LoginDetails();

        InputStream row = getResources().openRawResource(R.raw.trial);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(row, StandardCharsets.UTF_8));
        int i=0;
        String line= null;
        String Str1="";
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            android.util.Log.d("My Activity", "Line " + line);
            String[] tokens = line.split(",");
            logindetails.setId(tokens[0]);
            logindetails.setPassword(tokens[1]);
            if(s1.equals(logindetails.getId()) && s2.equals(logindetails.getPassword()))
            {
                Str1="Login Success full";
                i=1;
                break;
            }
        }
        if(i==0)
            Str1="Login Failed";

        TextView Prompt= findViewById(R.id.textView4);
        Prompt.setText(Str1);

    }
}