package com.example.studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class delete_student extends AppCompatActivity {

    EditText d_id;
    TextView display_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);
    }

    public void DELETE_STUDENT(View view){
        // Getting id of view
        d_id = findViewById(R.id.DS_S_ID);
        display_view = findViewById(R.id.DISPLAY_VIEW);

        String ID = d_id.getText().toString();

        String URL = "http://"+ GlobalClasss.IPAddress1 + ":8080/api/student";
        String req="/" + ID;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.DELETE,
                URL + req,
                null,
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
                                    ID + " has been deleted from DATABASE.",
                                    Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);

        display_view.setText("Student Profile deleted");
        Intent intent = new Intent(this,FirstPageTeachers.class);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }

}