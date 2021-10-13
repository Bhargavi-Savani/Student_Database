package com.example.studentdatabase;

import android.content.Intent;
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

    public static final String MSG = "com.example.studentdatabase.Main";

    String Id,Psw;          //Variable to store data.

    EditText UserId;            //Variable to store id`s of view.
    EditText Password;          //Variable to store id`s of view.

    Button SubmitButton;        //Variable to store id`s of view.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserId = findViewById(R.id.UserId);             //saving id of UserID text box.
        Password = findViewById(R.id.Password);         //saving id of password text box..

        SubmitButton = findViewById(R.id.SubButton);    //saving id of submit button.

        // specifies what will happen when submit button is clicked.
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Id = UserId.getText().toString();
                Psw = Password.getText().toString();

                LoginDetails Credential = Encryption(Id,Psw);

                try {
                    CredentialCheck(Credential);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    // Function checks the credentials entered by the user
    private void CredentialCheck(LoginDetails Credentials) throws IOException {

        Intent intent = new Intent(this,Page3.class); // an intent variable to transfer to next page

        LoginDetails loginInput=new LoginDetails();

        InputStream row = getResources().openRawResource(R.raw.encryption); // getting the file from the system
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(row, StandardCharsets.UTF_8));

        int i=0;
        String line = new String();     // Variable to store the line extracted from the file
        String Str1="";     // Stores the message to be displayed

        // A loop to iterate through the file to match the credentials
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] tokens = line.split(",");      // Storing the value of each column in form of array
            loginInput.setId(tokens[0]);
            loginInput.setPassword(tokens[1]);


            if(Credentials.getId().equals(loginInput.getId()) && Credentials.getPassword().equals(loginInput.getPassword()))  // Validates the credentials
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
        intent.putExtra(MSG,Credentials.getId());       // sends extra data to next page
        startActivity(intent);     // starts page3 activity
    }

    private LoginDetails Encryption(String Id,String Password)
    {
        LoginDetails EncryptedDetails = new LoginDetails();
        char[] id=new char[Id.length()];
        char[] psw=new char[Password.length()];
        int Key=0;

        InputStream row = getResources().openRawResource(R.raw.key);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(row, StandardCharsets.UTF_8));

        String line = new String();

        // an iterative loop to fetch the key to the corresponding id
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            android.util.Log.d("My Activity", "Line " + line);
            String[] tokens = line.split(",");
            if(tokens[0].equals(Id)) {
                Key = Integer.parseInt(tokens[1]);
            }
        }

        // two loops to encrypt the id and password entered by the user
        for(int i=0;i<Id.length();i++)
            id[i]=(char)((int)Id.charAt(i)+Key);

        for(int i=0;i<Password.length();i++)
            psw[i] = (char)((int)Password.charAt(i)+Key);

        EncryptedDetails.setId(String.valueOf(id));
        EncryptedDetails.setPassword(String.valueOf(psw));
        return EncryptedDetails;
    }
}