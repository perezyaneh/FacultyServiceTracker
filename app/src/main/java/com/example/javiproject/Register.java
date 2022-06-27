package com.example.javiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    //initiate variable
    EditText et_firstName, et_lastName, et_tupId, et_password;
    TextView tv_login;
    Button btn_register;

    //Firebase
    FirebaseDatabase database;
    DatabaseReference myRef;

    //UserModel
    UserModel userModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //get id's
        et_firstName = findViewById(R.id.et_firstName);
        et_lastName = findViewById(R.id.et_lastName);
        et_tupId = findViewById(R.id.et_tupId);
        et_password = findViewById(R.id.et_password);
        btn_register = findViewById(R.id.btn_register);
        tv_login = findViewById(R.id.tv_login);

        //set on click for register
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get input text
                String firstName = et_firstName.getText().toString();
                String lastName = et_lastName.getText().toString();
                String tupId = et_tupId.getText().toString();
                String password = et_password.getText().toString();
                String requestCount = "0";
                String trackerPoints = "0";

                //connect to database
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("Users");

                //pass all data in model class
                userModel = new UserModel(firstName, lastName, tupId, password, requestCount,trackerPoints);

                //transfer to database
                myRef.child(tupId).setValue(userModel);
                Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_LONG).show();

                //go to main page
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}