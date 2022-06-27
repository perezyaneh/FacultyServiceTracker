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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    //initiate variable
    EditText et_tupId, et_password;
    TextView tv_signUp;
    Button btn_login;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get id's
        et_tupId = findViewById(R.id.et_tupId);
        et_password = findViewById(R.id.et_password);
        tv_signUp = findViewById(R.id.tv_signUp);
        btn_login = findViewById(R.id.btn_login);

        //on click listener for signup page
        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isUser();
            }
        });
    }

    private void isUser(){
        //set variables
        String tupId = et_tupId.getText().toString();
        String password = et_password.getText().toString();

        //check database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        //query to check data from database
        Query checkTupId = myRef.orderByChild("tupId").equalTo(tupId);
        checkTupId.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    //set error to false
                    et_tupId.setError(null);
                    et_password.setError(null);

                    //get password
                    String passwordDb = snapshot.child(tupId).child("password").getValue(String.class);

                    if(passwordDb.equals(password)){
                        //transfer data to another activity implement toast
                        String tupIdDb = snapshot.child(tupId).child("tupId").getValue(String.class);
                        String firstNameDb = snapshot.child(tupId).child("firstName").getValue(String.class);
                        String requestCountDb = snapshot.child(tupId).child("requestCount").getValue(String.class);
                        String trackerPointsDb = snapshot.child(tupId).child("trackerPoints").getValue(String.class);

                        Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(MainActivity.this, LoginPage.class);
                        intent.putExtra("tupId", tupIdDb);
                        intent.putExtra("firstName", firstNameDb);
                        intent.putExtra("requestCount", requestCountDb);
                        intent.putExtra("trackerPoints", trackerPointsDb);

                        startActivity(intent);
                    } else {
                        et_password.setError("Wrong password");
                        et_password.setFocusable(true);
                    }
                } else {
                    et_tupId.setError("Wrong TUP ID");
                    et_tupId.setFocusable(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}