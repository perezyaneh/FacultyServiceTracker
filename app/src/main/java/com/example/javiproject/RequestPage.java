package com.example.javiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestPage extends AppCompatActivity {

    //initiate variable
    EditText et_tupId, et_eventName, et_date;
    Button btn_submit;
    TextView tv_goBack;

    FirebaseDatabase database;
    DatabaseReference myRef, myRef1, myRef2;
    RequestModel requestModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_page);

        //get id's

        et_eventName = findViewById(R.id.et_eventName);
        et_date = findViewById(R.id.et_date);
        btn_submit = findViewById(R.id.btn_submit);
        tv_goBack = findViewById(R.id.tv_goBack);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initiate variables
                Intent intent = getIntent();
                String tupId = intent.getStringExtra("tupId");

                String eventName = et_eventName.getText().toString();
                String date = et_date.getText().toString();
                String status = "Pending";

                //database path
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("Request");

                //update count of request & tracker points
                myRef2 = database.getReference("Users");
                myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String requestCountDb = snapshot.child(tupId).child("requestCount").getValue(String.class);
                        String trackerPointsDb = snapshot.child(tupId).child("trackerPoints").getValue(String.class);

                        int req = Integer.parseInt(requestCountDb);
                        int tracker = Integer.parseInt(trackerPointsDb);
                        req++;
                        tracker++;

                        String newReq = String.valueOf(req);
                        String newTracker = String.valueOf(tracker);

                        snapshot.getRef().child(tupId).child("requestCount").setValue(newReq);
                        snapshot.getRef().child(tupId).child("trackerPoints").setValue(newTracker);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                //get all data from input text
                requestModel = new RequestModel(tupId, eventName, date, status);
                myRef.child(tupId).child(eventName).setValue(requestModel);
                Toast.makeText(RequestPage.this, "Request Submitted", Toast.LENGTH_LONG).show();
            }
        });

        tv_goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update request count & tracker points count if request has been submitted
                myRef1 = database.getReference("Users");
                myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Intent intent;
                        intent = getIntent();
                        String tupId = intent.getStringExtra("tupId");

                        String tupIdDb = snapshot.child(tupId).child("tupId").getValue(String.class);
                        String firstNameDb = snapshot.child(tupId).child("firstName").getValue(String.class);
                        String requestCountDb = snapshot.child(tupId).child("requestCount").getValue(String.class);
                        String trackerPointsDb = snapshot.child(tupId).child("trackerPoints").getValue(String.class);

                        intent = new Intent(RequestPage.this, LoginPage.class);
                        intent.putExtra("tupId", tupIdDb);
                        intent.putExtra("firstName", firstNameDb);
                        intent.putExtra("requestCount", requestCountDb);
                        intent.putExtra("trackerPoints", trackerPointsDb);

                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}