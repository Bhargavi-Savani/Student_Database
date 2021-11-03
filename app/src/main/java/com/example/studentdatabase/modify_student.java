package com.example.studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

public class modify_student extends AppCompatActivity {

    final int Size = 3;
    String ID;
    String Sem;
    Student student;
    EditText[] CourseCodeValue;
    EditText[] CourseGradeValue;
    String[] courseCodes;
    String[] courseGrades;
    int[] courseGradePoints;

    String URL = "http://"+ GlobalClasss.IPAddress1 + ":8080/api/student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_student);

        Intent intent = getIntent();
        Sem = intent.getStringExtra(modify_student_intermediate.msd_sem);
        ID = intent.getStringExtra(modify_student_intermediate.msd_id);

        student = new Student();
        student.setStudentId(ID);

        CourseCodeValue = new EditText[Size];
        CourseGradeValue = new EditText[Size];

        courseCodes = new String[Size];
        courseGrades = new String[Size];
        courseGradePoints = new int[Size];

        CourseCodeValue[0] = findViewById(R.id.MD_COURSE_VALUE1);
        CourseCodeValue[1] = findViewById(R.id.MD_COURSE_VALUE2);
        CourseCodeValue[2] = findViewById(R.id.MD_COURSE_VALUE3);

        CourseGradeValue[0] = findViewById(R.id.MD_GRADE_VALUE1);
        CourseGradeValue[1] = findViewById(R.id.MD_GRADE_VALUE2);
        CourseGradeValue[2] = findViewById(R.id.MD_GRADE_VALUE3);

    }

    public void ModifyButton(View view){

        /*Subject[] subject = new Subject[3];
        Semester semester = new Semester();
        Student student = new Student();

for(int i =0; i < Size; i++)
{
     subject[i] = new Subject();
}

        for (int i = 0; i < Size; i++) {
            subject[i].setSubCode(CourseCodeValue[i].getText().toString());
            subject[i].setGrade(CourseGradeValue[i].getText().toString());
            subject[i].setGradePoint(Integer.parseInt(add_student.GradePoints.valueOf(CourseGradeValue[i].getText().toString()).toString()));
        }
        ArrayList<Subject> var = new ArrayList<Subject>(Arrays.asList(subject));
        semester.setSubjects(var);*/

        for(int i = 0; i<Size; i++){
            courseCodes[i] = CourseCodeValue[i].getText().toString();
            courseGrades[i] = CourseGradeValue[i].getText().toString();
            courseGradePoints[i] = Integer.parseInt(add_student.GradePoints.valueOf(CourseGradeValue[i].getText().toString().toUpperCase()).toString());
        }


        // TODO api call
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
                        modifyObject(found);
//                        semester.setSem(Integer.parseInt(Sem));
//                        found.setS2(semester);
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
    void modifyObject(Student found){

        if(Sem.equals("1")) {
            Semester semester = found.getS1();
            ArrayList<Subject> AL = semester.getSubjects();
            Subject[] subject = new Subject[AL.size()];
            subject = AL.toArray(subject);

            /*for (int i = 0; i < Size; i++) {
                subject[i].setSubCode(CourseCodeValue[i].getText().toString());
                subject[i].setGrade(CourseGradeValue[i].getText().toString());
                subject[i].setGradePoint(Integer.parseInt(add_student.GradePoints.valueOf(CourseGradeValue[i].getText().toString()).toString()));
            }*/
            for (int i = 0; i < Size; i++) {
                for(int j = 0; j < subject.length; j++ ){
                    if(subject[j].getSubCode().equalsIgnoreCase(courseCodes[i])){
                        subject[j].setGrade(courseGrades[i].toUpperCase());
                        subject[j].setGradePoint(courseGradePoints[i]);
                    }
                }
            }

            ArrayList<Subject> var = new ArrayList<Subject>(Arrays.asList(subject));
            semester.setSubjects(var);
            semester.setSem(Integer.parseInt(Sem));

            student.setS1(semester);
        }
        else {
            Semester semester = found.getS2();
            ArrayList<Subject> AL = semester.getSubjects();
            Subject[] subject = new Subject[AL.size()];
            subject = AL.toArray(subject);


            /*for (int i = 0; i < Size; i++) {
                subject[i].setSubCode(CourseCodeValue[i].getText().toString());
                subject[i].setGrade(CourseGradeValue[i].getText().toString());
                subject[i].setGradePoint(Integer.parseInt(add_student.GradePoints.valueOf(CourseGradeValue[i].getText().toString()).toString()));
            }*/
            for (int i = 0; i < Size; i++) {
                for(int j = 0; j < subject.length; j++ ){
                    if(subject[j].getSubCode().equalsIgnoreCase(courseCodes[i])){
                        subject[j].setGrade(courseGrades[i].toUpperCase());
                        subject[j].setGradePoint(courseGradePoints[i]);
                    }
                }
            }

            ArrayList<Subject> var = new ArrayList<Subject>(Arrays.asList(subject));
            semester.setSubjects(var);
            semester.setSem(Integer.parseInt(Sem));

            student.setS2(semester);
        }
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
                                    ID + " has been MODIFIED.",
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
}
