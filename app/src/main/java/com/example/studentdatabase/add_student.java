package com.example.studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentdatabase.model.Semester;
import com.example.studentdatabase.model.Student;
import com.example.studentdatabase.model.Subject;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class add_student extends AppCompatActivity {

    // Variable for max size of subject

    final int Size = 8;
    String ID;
    String Semester;
    String StudentName;
    Student student;
    TextView[] SubjectCode;
    EditText[] SubjectCredit;
    EditText[] SubjectGrade;


    //Change IPAddress in GlobalClasss if not same
    String URL = "http://"+ GlobalClasss.IPAddress1 + ":8080/api/student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // Creating array of view variables
        SubjectCode = new TextView[Size];
        SubjectCredit = new EditText[Size];
        SubjectGrade = new EditText[Size];

        // collecting course view id
        SubjectCode[0] = findViewById(R.id.AD_SUB1);
        SubjectCode[1] = findViewById(R.id.AD_SUB2);
        SubjectCode[2] = findViewById(R.id.AD_SUB3);
        SubjectCode[3] = findViewById(R.id.AD_SUB4);
        SubjectCode[4] = findViewById(R.id.AD_SUB5);
        SubjectCode[5] = findViewById(R.id.AD_SUB6);
        SubjectCode[6] = findViewById(R.id.AD_SUB7);
        SubjectCode[7] = findViewById(R.id.AD_SUB8);

        // collecting Credit view id
        SubjectCredit[0] = findViewById(R.id.AD_CREDIT1);
        SubjectCredit[1] = findViewById(R.id.AD_CREDIT2);
        SubjectCredit[2] = findViewById(R.id.AD_CREDIT3);
        SubjectCredit[3] = findViewById(R.id.AD_CREDIT4);
        SubjectCredit[4] = findViewById(R.id.AD_CREDIT5);
        SubjectCredit[5] = findViewById(R.id.AD_CREDIT6);
        SubjectCredit[6] = findViewById(R.id.AD_CREDIT7);
        SubjectCredit[7] = findViewById(R.id.AD_CREDIT8);

        // collecting Grade view id
        SubjectGrade[0] = findViewById(R.id.AD_GRADE1);
        SubjectGrade[1] = findViewById(R.id.AD_GRADE2);
        SubjectGrade[2] = findViewById(R.id.AD_GRADE3);
        SubjectGrade[3] = findViewById(R.id.AD_GRADE4);
        SubjectGrade[4] = findViewById(R.id.AD_GRADE5);
        SubjectGrade[5] = findViewById(R.id.AD_GRADE6);
        SubjectGrade[6] = findViewById(R.id.AD_GRADE7);
        SubjectGrade[7] = findViewById(R.id.AD_GRADE8);

        Intent intent = getIntent();
        StudentName = intent.getStringExtra(add_student_intermediate.AD_NAME_SENDER);
        ID = intent.getStringExtra(add_student_intermediate.AD_ID_SENDER);
        Semester = intent.getStringExtra(add_student_intermediate.AD_SEM_SENDER);


        student = new Student();
        student.setStudentId(ID);
        student.setStudentName(StudentName);

        System.out.println(student);

        if(Semester.equals("1")){
            for (int i = 0; i < Size; i++) {
                SubjectCode[i].setText(student.getS1().getSubjects().get(i).getSubCode().toUpperCase());
            }
        }
        else {
            for (int i = 0; i < Size; i++) {
                SubjectCode[i].setText(student.getS2().getSubjects().get(i).getSubCode().toUpperCase());
            }
        }
    }

    public void Get_Input(View view){

        if(Semester.equals("1")) {
            Semester semester = new Semester();
            ArrayList<Subject> AL = student.getS1().getSubjects();
            Subject[] subject = new Subject[AL.size()];
            subject = AL.toArray(subject);

            for (int i = 0; i < Size; i++) {
                subject[i].setCredit(Double.parseDouble(SubjectCredit[i].getText().toString()));
                subject[i].setGrade(SubjectGrade[i].getText().toString().toUpperCase());
                subject[i].setGradePoint(Integer.parseInt(GradePoints.valueOf(SubjectGrade[i].getText().toString().toUpperCase()).toString()));
            }

            ArrayList<Subject> var = new ArrayList<>(Arrays.asList(subject));
            semester.setSubjects(var);

            semester.setSem(Integer.parseInt(Semester));
            student.setS1(semester);
            callPostWith(student);
        }
        else {
            Semester semester = new Semester();
            ArrayList<Subject> AL = student.getS2().getSubjects();
            Subject[] subject = new Subject[AL.size()];
            subject = AL.toArray(subject);

            for (int i = 0; i < Size; i++) {
                subject[i].setCredit(Double.parseDouble(SubjectCredit[i].getText().toString()));
                subject[i].setGrade(SubjectGrade[i].getText().toString().toUpperCase());
                subject[i].setGradePoint(Integer.parseInt(GradePoints.valueOf(SubjectGrade[i].getText().toString().toUpperCase()).toString()));
            }

            ArrayList<Subject> var = new ArrayList<>(Arrays.asList(subject));
            semester.setSubjects(var);

            Gson gson = new Gson();
            String req="/" + ID;
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    URL + req,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("GET Response ",response.toString());
                            String res = response.toString();
                            Student found = gson.fromJson(res, Student.class);
                            semester.setSem(Integer.parseInt(Semester));
                            found.setS2(semester);
                            callPutWith(found);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("GET Error Response ",error.toString());
                        }
                    }
            );
            requestQueue.add(jsonObjectRequest);
        }

        Intent intent = new Intent(this,FirstPageTeachers.class);
        TextView Prompt = findViewById(R.id.confirmation_message);
        Prompt.setText("Student Data added successfully");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }

    void callPostWith(Student postStudent){
        Gson gson = new Gson();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(gson.toJson(postStudent));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String req="/";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Build the request
        final JsonObjectRequest jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.POST,
                URL + req,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(@NotNull JSONObject response) {
                        // Api call succeeded
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Api call failed
                        error.printStackTrace();
                        try {
                            @NotNull int statusCode = error.networkResponse.statusCode;
                            // Show the error to the user
                            Snackbar.make(findViewById(android.R.id.content).getRootView(),
                                    "Error Code: " + statusCode + ": NO STUDENT FOUND",
                                    Snackbar.LENGTH_SHORT).show();
                        }
                        catch(NullPointerException e){
                            e.printStackTrace();
                            Snackbar.make(findViewById(android.R.id.content).getRootView(),
                                    ID + " has been Added to DATABASE.",
                                    Snackbar.LENGTH_SHORT).show();
                        }
                    }
        }) {
            @NonNull
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // Build the headers
                final Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        // Make the request
        requestQueue.add(jsonObjectRequest);
    }
    void callPutWith(Student putStudent){
        Gson gson = new Gson();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(gson.toJson(putStudent));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String req="/";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Build the request
        final JsonObjectRequest jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.PUT,
                URL + req,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(@NotNull JSONObject response) {
                        // Api call succeeded
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Api call failed
                        error.printStackTrace();
                        try {
                            @NotNull int statusCode = error.networkResponse.statusCode;
                            // Show the error to the user
                            Snackbar.make(findViewById(android.R.id.content).getRootView(),
                                    "Error Code: " + statusCode + ": NO STUDENT FOUND",
                                    Snackbar.LENGTH_SHORT).show();
                        }
                        catch(NullPointerException e){
                            e.printStackTrace();
                            Snackbar.make(findViewById(android.R.id.content).getRootView(),
                                    ID + " has been Added to DATABASE.",
                                    Snackbar.LENGTH_SHORT).show();
                        }
                    }
        }) {
            @NonNull
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // Build the headers
                final Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        // Make the request
        requestQueue.add(jsonObjectRequest);
    }

    enum GradePoints {
        FF(0),DD(4),CD(5),CC(6),BC(7),BB(8),AB(9),AA(10);
        int num = 0;
        GradePoints(int x){
            this.num = x;
        }

        @Override
        public String toString(){
            return Integer.toString(num);
        }

    }
}
