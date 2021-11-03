package com.example.studentdatabase;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentdatabase.model.Student;
import com.google.gson.Gson;

import org.json.JSONObject;

public class StudentProfile extends AppCompatActivity {


    TextView Student_Id;
    TextView Name;
    TextView Batch;
    TextView Semester;
    TextView Programme;
    TextView College;

    /*static void setCurrent(String studentId, String studentName, Semester s1, Semester s2){
        current = new Student();
        current.setStudentId(studentId);
        current.setStudentName(studentName);
        ArrayList<Subject> sub1 = new ArrayList<>();
        sub1.addAll(s1.getSubjects());
        current.setS1(new Semester(s1.getSem(),sub1));
        ArrayList<Subject> sub2 = new ArrayList<>();
        sub2.addAll(s2.getSubjects());
        current.setS2(new Semester(s2.getSem(), sub2));
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        // FETCHING ALL THE ID OF ALL VIEWS
        Student_Id = findViewById(R.id.Profile_id);
        Name = findViewById(R.id.Profile_full_name);
        Batch = findViewById(R.id.Profile_batch);
        Semester = findViewById(R.id.Profile_Sem);
        Programme = findViewById(R.id.Profile_programme);
        College = findViewById(R.id.Profile_college);

//    TODO 1. Api call and convert it into object
        /**
         * Change this String IPAddress to your local WiFi Adapter's IPv4 Address.
         * Run 'ipconfig' at cmd to find it.
         */
        String IPAddress1 = "192.168.1.193";
        String URL = "http://"+ IPAddress1 + ":8080/api/student";
        Gson gson = new Gson();
        String req="/" + GlobalClasss.Student_Id;
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
                        System.out.println(res);
                        Student found = gson.fromJson(res, Student.class);
                        System.out.println("FOUND IN RESPONSE:\n\n\n " + found);

                        Screen_output(found);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("GET Error Response ",error.toString());
                        String res = error.toString();
                        System.out.println(res);
                    }
                }
        );
//        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    // DISPLAYS ALL THE VALUE FETCHED
    public void Screen_output(Student found){

        StudentDetails Profile_obj = new StudentDetails();      // VARIABLE TO DISPLAY ALL THE VALUES

        Profile_obj.setId(found.getStudentId());
        Profile_obj.setName(found.getStudentName());

        // SETTING ALL THE VALUE ON DISPLAY
        Student_Id.setText(Profile_obj.getId());
        Name.setText(Profile_obj.getName());
        Batch.setText(Profile_obj.getBatch());
        Semester.setText(Profile_obj.getSemester());
        Programme.setText(Profile_obj.getProgramme());
        College.setText(Profile_obj.getCollege());

    }

}