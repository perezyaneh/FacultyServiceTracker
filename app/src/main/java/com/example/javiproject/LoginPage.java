package com.example.javiproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginPage extends AppCompatActivity {


    //initiate variable
    TextView tv_name, tv_requestCount, tv_trackerPoints;
    Button btn_submitRequest;

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<ItemModel> list;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //get id's
        tv_name = findViewById(R.id.tv_name);
        tv_requestCount = findViewById(R.id.tv_requestCount);
        tv_trackerPoints = findViewById(R.id.tv_trackerPoints);
        btn_submitRequest = findViewById(R.id.btn_submitRequest);



        //get string extra from previous activity
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String requestCount = intent.getStringExtra("requestCount");
        String trackerPoints = intent.getStringExtra("trackerPoints");
        String tupId = intent.getStringExtra("tupId");

        tv_name.setText(firstName);
        tv_requestCount.setText(requestCount);
        tv_trackerPoints.setText(trackerPoints);

        //display all request
        recyclerView = findViewById(R.id.recyclerView);
        myRef = FirebaseDatabase.getInstance().getReference("Request").child(tupId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren() ){
                    ItemModel itemModel = dataSnapshot.getValue(ItemModel.class);
                    list.add(itemModel);
                }
                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        //set on click to submit request
        btn_submitRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, RequestPage.class);
                intent.putExtra("tupId", tupId);
                startActivity(intent);
            }
        });
    }
}