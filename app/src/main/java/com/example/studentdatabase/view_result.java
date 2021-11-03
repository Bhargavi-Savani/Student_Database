package com.example.studentdatabase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

    final int Size = 8;

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

        // CREATING ARRAY OF TEXTVIEW TO FETCH ID
        Sub = new TextView[Size];
        Sub_credit = new TextView[Size];
        Sub_grade = new TextView[Size];

        // FETCHING VALUE FROM INTERMEDIATE PAGE
        Intent intent = getIntent();
        Id = intent.getStringExtra(view_result_intermediate.Sender);
        Sem = intent.getStringExtra(view_result_intermediate.INPUT_SEMESTER);

        // FETCHING ID OFF ALL THE VIEWS
        Name = findViewById(R.id.t_student_name);
        ID = findViewById(R.id.t_id_value);

        Sub[0] = findViewById(R.id.t_sub1);
        Sub[1] = findViewById(R.id.t_sub2);
        Sub[2] = findViewById(R.id.t_sub3);
        Sub[3] = findViewById(R.id.t_sub4);
        Sub[4] = findViewById(R.id.t_sub5);
        Sub[5] = findViewById(R.id.t_sub6);
        Sub[6] = findViewById(R.id.t_sub7);
        Sub[7] = findViewById(R.id.t_sub8);

        Sub_credit[0] = findViewById(R.id.t_credit1);
        Sub_credit[1] = findViewById(R.id.t_credit2);
        Sub_credit[2] = findViewById(R.id.t_credit3);
        Sub_credit[3] = findViewById(R.id.t_credit4);
        Sub_credit[4] = findViewById(R.id.t_credit5);
        Sub_credit[5] = findViewById(R.id.t_credit6);
        Sub_credit[6] = findViewById(R.id.t_credit7);
        Sub_credit[7] = findViewById(R.id.t_credit8);


        Sub_grade[0] = findViewById(R.id.t_grade1);
        Sub_grade[1] = findViewById(R.id.t_grade2);
        Sub_grade[2] = findViewById(R.id.t_grade3);
        Sub_grade[3] = findViewById(R.id.t_grade4);
        Sub_grade[4] = findViewById(R.id.t_grade5);
        Sub_grade[5] = findViewById(R.id.t_grade6);
        Sub_grade[6] = findViewById(R.id.t_grade7);
        Sub_grade[7] = findViewById(R.id.t_grade8);


        Semester = findViewById(R.id.t_sem_value);
        Total_Credit_sem = findViewById(R.id.t_sem_credit_value);
        Earned_credit_sem = findViewById(R.id.t_sem_earned_value);
        SGPA = findViewById(R.id.t_sgpa_value);

        Cumulative = findViewById(R.id.t_cumalative_value);
        Total_Credit = findViewById(R.id.t_total_credit_value);
        Earned_credit = findViewById(R.id.t_earned_credit_value);
        CGPA = findViewById(R.id.t_Cgpa_value);


//        TODO 2 Create Object and API call
        /**
         * Remove that 1 for your IPAddress
         * In case, it is changed for your lap, change it to
         */
        String URL = "http://"+ GlobalClasss.IPAddress1 + ":8080/api/student";
        Gson gson = new Gson();
        String req="/" + Id;
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

                    T_Result_Output(found);

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

    // DISPLAY FUNCTION
    @SuppressLint("SetTextI18n")
    public void T_Result_Output(Student found){

        int informer = 0;       // VALUE = 0 IF PASS AND VALUE = 1 IF FAIL

        StudentDetails Profile_obj = new StudentDetails();
        Profile_obj.setId(found.getStudentId());
        Profile_obj.setName(found.getStudentName());

        Name.setText(Profile_obj.getName());
        ID.setText(Profile_obj.getId());

        // DISPLAYING VALUES DEPENDING UPON THE SEMESTER SELECTED

        if(Sem.equals("1")){
            final int size = found.getS1().getSubjects().size();        // GIVES THE SIZE OF THE OBJECT FETCHED FROM API

            // LOOP TO DISPLAY ALL THE VALUES ON SCREEN
            for(int i = 0; i < size ; i++) {
                Sub[i].setText(found.getS1().getSubjects().get(i).getSubCode().toUpperCase());
                Sub_credit[i].setText(Double.toString(found.getS1().getSubjects().get(i).getCredit()));
                Sub_grade[i].setText(found.getS1().getSubjects().get(i).getGrade());
            }

            // CHECKS IF STUDENT IS FAILED IN ANY SUBJECT
            for (int i = 0; i < size; i++) {
                if(found.getS1().getSubjects().get(i).getGradePoint() < 4){
                    informer = 1;
                }
            }

            double SGPA_Numerator = 0;
            double Credit_sum = 0;


            // LOOP TO CALCULATE SGPA
            for (int i = 0; i < size; i++) {
                SGPA_Numerator += found.getS1().getSubjects().get(i).getCredit() *
                        found.getS1().getSubjects().get(i).getGradePoint();

                Credit_sum += found.getS1().getSubjects().get(i).getCredit();
            }
            double Sgpa = SGPA_Numerator / Credit_sum;

            // CHECKS IF THE STUDENT IS PASS OR FAILED AND DISPLAYS THE OUTPUT ACCORDINGLY
            if(informer == 0){
                SGPA.setText(String.format("%.2f",Sgpa));   // DISPLAYS OUTPUT UP TO TWO DECIMAL VALUE
                CGPA.setText(String.format("%.2f",Sgpa));
                Earned_credit_sem.setText(Double.toString(Credit_sum));
                Earned_credit.setText(Double.toString(Credit_sum));
            }
            else {
                SGPA.setText("Fail");
                CGPA.setText("Fail");
                Earned_credit_sem.setText("-");
                Earned_credit.setText("-");
            }
            Total_Credit_sem.setText(Double.toString(Credit_sum));
            Total_Credit.setText(Double.toString(Credit_sum));

        }

        else{

            final int size = found.getS2().getSubjects().size();
            double SGPA_Numerator = 0;
            double CGPA_Numerator = 0;
            double Sgpa_Credit_sum = 0;
            double Cgpa_Credit_sum = 0;

            // LOOP TO DISPLAY ALL THE VALUES ON SCREEN
            for(int i = 0; i < size ; i++) {
                Sub[i].setText(found.getS2().getSubjects().get(i).getSubCode().toUpperCase());
                Sub_credit[i].setText(Double.toString(found.getS2().getSubjects().get(i).getCredit()));
                Sub_grade[i].setText(found.getS2().getSubjects().get(i).getGrade());
            }

            // CHECKS IF STUDENT IS FAIL IN ANY SUBJECT IN BOTH SEMESTER
            for (int i = 0; i < size; i++) {
                if(found.getS1().getSubjects().get(i).getGradePoint() < 4){
                    informer = 1;
                    break;
                }

                if(found.getS2().getSubjects().get(i).getGradePoint() < 4){
                    informer = 1;
                    break;
                }
            }

            // CALCULATE CURRENT SEMESTER SGPA
            for (int i = 0; i < size; i++) {
                SGPA_Numerator += found.getS2().getSubjects().get(i).getCredit() *
                        found.getS2().getSubjects().get(i).getGradePoint();

                Sgpa_Credit_sum += found.getS2().getSubjects().get(i).getCredit();
            }

            double Sgpa = SGPA_Numerator / Sgpa_Credit_sum;

            Total_Credit_sem.setText(Double.toString(Sgpa_Credit_sum));

            CGPA_Numerator += SGPA_Numerator;
            Cgpa_Credit_sum += Sgpa_Credit_sum;

            // CALCULATES CGPA
            for (int i = 0; i < size; i++) {
                CGPA_Numerator += found.getS1().getSubjects().get(i).getCredit() *
                        found.getS1().getSubjects().get(i).getGradePoint();


                Cgpa_Credit_sum += found.getS1().getSubjects().get(i).getCredit();

            }

            double Cgpa = CGPA_Numerator / Cgpa_Credit_sum;

            // DISPLAYS GRADE, SGPA AND CGPA DEPENDING UPON INFORMER VALUE
            if(informer == 0){
                SGPA.setText(String.format("%.2f",Sgpa));
                CGPA.setText(String.format("%.2f",Cgpa));
                Earned_credit_sem.setText(Double.toString(Sgpa_Credit_sum));
                Earned_credit.setText(Double.toString(Cgpa_Credit_sum));
            }
            else {
                SGPA.setText("Fail");
                CGPA.setText("Fail");
                Earned_credit_sem.setText("-");
                Earned_credit.setText("-");
            }
            Total_Credit.setText(Double.toString(Cgpa_Credit_sum));
            Cumulative.setText("-");
        }
        Cumulative.setText("-");
        Semester.setText(Sem);
    }
}