package com.example.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentdatabase.model.Student;
import com.google.gson.Gson;

public class view_result extends AppCompatActivity {


    String Id = new String();
    String Sem = new String();

    TextView Name;
    TextView ID;

    final int Size = 9;

    TextView[] Sub;

    TextView[] Sub_credit;

    TextView[] Sub_grade;

    TextView Semester;
    TextView Total_Credit_sem ;
    TextView Earned_credit_sem;
    TextView SGPA;

    TextView Cumulative;
    TextView Total_Credit;
    TextView Earned_credit;
    TextView CGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);
        Sub = new TextView[Size];
        Sub_credit = new TextView[Size];
        Sub_grade = new TextView[Size];

        Intent intent = getIntent();
        Id = intent.getStringExtra(view_result_intermediate.Sender);
        Sem = intent.getStringExtra(Result_home.Sem_Sender);

        Name = findViewById(R.id.student_name);
        ID = findViewById(R.id.id_value);

        Sub[0] = findViewById(R.id.sub1);
        Sub[1] = findViewById(R.id.sub2);
        Sub[2] = findViewById(R.id.sub3);
        Sub[3] = findViewById(R.id.sub4);
        Sub[4] = findViewById(R.id.sub5);
        Sub[5] = findViewById(R.id.sub6);
        Sub[6] = findViewById(R.id.sub7);
        Sub[7] = findViewById(R.id.sub8);
        Sub[8] = findViewById(R.id.sub9);

        Sub_credit[0] = findViewById(R.id.credit1);
        Sub_credit[1] = findViewById(R.id.credit2);
        Sub_credit[2] = findViewById(R.id.credit3);
        Sub_credit[3] = findViewById(R.id.credit4);
        Sub_credit[4] = findViewById(R.id.credit5);
        Sub_credit[5] = findViewById(R.id.credit6);
        Sub_credit[6] = findViewById(R.id.credit7);
        Sub_credit[7] = findViewById(R.id.credit8);
        Sub_credit[8] = findViewById(R.id.credit9);

        Sub_grade[0] = findViewById(R.id.grade1);
        Sub_grade[1] = findViewById(R.id.grade2);
        Sub_grade[2] = findViewById(R.id.grade3);
        Sub_grade[3] = findViewById(R.id.grade4);
        Sub_grade[4] = findViewById(R.id.grade5);
        Sub_grade[5] = findViewById(R.id.grade6);
        Sub_grade[6] = findViewById(R.id.grade7);
        Sub_grade[7] = findViewById(R.id.grade8);
        Sub_grade[8] = findViewById(R.id.grade9);

        Semester = findViewById(R.id.sem_value);
        Total_Credit_sem = findViewById(R.id.sem_credit_value);
        Earned_credit_sem = findViewById(R.id.sem_earned_value);
        SGPA = findViewById(R.id.sgpa_value);

        Cumulative = findViewById(R.id.cumalative_value);
        Total_Credit = findViewById(R.id.total_credit_value);
        Earned_credit = findViewById(R.id.earned_credit_value);
        CGPA = findViewById(R.id.Cgpa_value);


//        TODO 2 Create Object and API call
        /**
         * Change this String IPAddress to your local WiFi Adapter's IPv4 Address.
         * Run 'ipconfig' at cmd to find it.
         */
        String IPAddress = "192.168.28.37";
        String URL = "http://"+ IPAddress + ":8080/api/student";
        Gson gson = new Gson();
        String req="/" + "20CS075";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        @SuppressLint("SetTextI18n") JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL + req,
                null,
                response -> {
                    Log.e("GET Response ",response.toString());
                    String res = response.toString();
                    System.out.println(res);
                    Student found = gson.fromJson(res, Student.class);

                    System.out.println("FOUND IN RESPONSE:\n\n\n " + found);

                    Result_Output(found);

                },
                error -> {
                    Log.e("GET Error Response ",error.toString());
                    String res = error.toString();
                    System.out.println(res);
                }
        );
        requestQueue.add(objectRequest);
//     TODO 2.
    }

    @SuppressLint("SetTextI18n")
    public void Result_Output(Student found){
        StudentDetails Profile_obj = new StudentDetails();
        Profile_obj.setId(found.getStudentId());
        Profile_obj.setName(found.getStudentName());

        Name.setText(Profile_obj.getName());
        ID.setText(Id);

        if(Sem.equals("1")){
            final int size = found.getS1().getSubjects().size();

            for(int i = 0; i < size ; i++) {
                Sub[i].setText(found.getS1().getSubjects().get(i).getSubCode());
                Sub_credit[i].setText(Double.toString(found.getS1().getSubjects().get(i).getCredit()));
                Sub_grade[i].setText(found.getS1().getSubjects().get(i).getGrade());
            }

            double SGPA_Numerator = 0;
            double Credit_sum = 0;


            for (int i = 0; i < size; i++) {
                SGPA_Numerator += found.getS1().getSubjects().get(i).getCredit() *
                        found.getS1().getSubjects().get(i).getGradePoint();

                Credit_sum += found.getS1().getSubjects().get(i).getCredit();
            }
            double Sgpa = SGPA_Numerator / Credit_sum;


            SGPA.setText(Double.toString(Math.round(Sgpa)));
            Total_Credit_sem.setText(Double.toString(Credit_sum));
            CGPA.setText(Double.toString(Math.round(Sgpa)));
            Total_Credit.setText(Double.toString(Credit_sum));

        }
        else{

            final int size = found.getS2().getSubjects().size();

            for(int i = 0; i < size ; i++) {
                Sub[i].setText(found.getS2().getSubjects().get(i).getSubCode());
                Sub_credit[i].setText(Double.toString(found.getS2().getSubjects().get(i).getCredit()));
                Sub_grade[i].setText(found.getS2().getSubjects().get(i).getGrade());
            }

            double SGPA_Numerator = 0;
            double CGPA_Numerator = 0;
            double Credit_sum = 0;

            for (int i = 0; i < size; i++) {
                SGPA_Numerator += found.getS2().getSubjects().get(i).getCredit() *
                        found.getS2().getSubjects().get(i).getGradePoint();

                Credit_sum += found.getS2().getSubjects().get(i).getCredit();
            }
            double Sgpa = SGPA_Numerator / Credit_sum;

            SGPA.setText(Double.toString(Sgpa));

            for (int i = 0; i < size; i++) {
                CGPA_Numerator += found.getS1().getSubjects().get(i).getCredit() *
                        found.getS1().getSubjects().get(i).getGradePoint();

                CGPA_Numerator += found.getS2().getSubjects().get(i).getCredit() *
                        found.getS2().getSubjects().get(i).getGradePoint();

                Credit_sum += found.getS1().getSubjects().get(i).getCredit();

                Credit_sum += found.getS2().getSubjects().get(i).getCredit();
            }
            double Cgpa = CGPA_Numerator / Credit_sum;

            CGPA.setText(Double.toString(Cgpa));
        }
        Semester.setText(Sem);
    }
}