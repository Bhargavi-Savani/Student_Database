package com.example.studentdatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

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

                LoginDetails Credential = null;
                try {
                    Credential = Encryption(Id,Psw);
                } catch (IOException | GeneralSecurityException e) {
                    e.printStackTrace();
                }


                try {
                    LoginCredentials(Credential);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    protected static Credential authorize() throws IOException, GeneralSecurityException{

        InputStream input = MainActivity.class.getResourceAsStream("/credentials.json");
        if (input == null) throw new FileNotFoundException("Resource not found: " + "/credentials.json");

        GoogleClientSecrets ClientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new InputStreamReader(input)
        );

        List<String> Scopes= Collections.singletonList(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),JacksonFactory.getDefaultInstance(),ClientSecrets,Scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new File("tokens")))
                .setAccessType("offline")
                .build();

        return new AuthorizationCodeInstalledApp(
                flow,new LocalServerReceiver()).authorize("user");
    }

    public static Sheets getSheetsServices() throws IOException, GeneralSecurityException{
        Credential credential= authorize();

        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                credential).build();
    }

    private void LoginCredentials(LoginDetails Credentials) throws IOException {
        LoginDetails loginInput=new LoginDetails();

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
            String[] tokens = new String[2];
            if (line != null) {
                tokens = line.split(",");
            }
            loginInput.setId(String.valueOf(tokens[0]));
            loginInput.setPassword(String.valueOf(tokens[1]));



            if(Credentials.getId().equals(loginInput.getId()) && Credentials.getPassword().equals(loginInput.getPassword()))
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

    private LoginDetails Encryption(String Id, String Password) throws IOException, GeneralSecurityException {

        int Key=0;
        Sheets sheetservices = getSheetsServices();
        ValueRange Response = sheetservices.spreadsheets().values()
                .get("1kxejlHtGZJsnB2WCbNah-HeNY1u3Ln18ImdyE0_uQ-4","B2").execute();

        List<List<Object>> values = Response.getValues();

        for(List row: values) {
            Key= Integer.parseInt((String) row.get(0));
        }

        LoginDetails EncryptedDetails = new LoginDetails();
        char[] id=new char[Id.length()];
        char[] psw=new char[Password.length()];
        for(int i=0;i<Id.length();i++)
            id[i]=(char)((int)Id.charAt(i)+Key);

        for(int i=0;i<Password.length();i++)
            psw[i] = (char)((int)Password.charAt(i)+Key);

        EncryptedDetails.setId(String.valueOf(id));
        EncryptedDetails.setPassword(String.valueOf(psw));
        return EncryptedDetails;
    }
}

