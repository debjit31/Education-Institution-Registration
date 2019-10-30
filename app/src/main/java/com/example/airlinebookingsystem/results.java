package com.example.airlinebookingsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class results extends AppCompatActivity {

    private String regid;

    private DatabaseReference mdatabase;
    private DatabaseReference mdb;

    private  TextView address;
    private TextView dept;
    private TextView dob;
    private TextView email;
    private TextView gender;
    private TextView rank;
    private  TextView name;
    private TextView perc10;
    private TextView per12;
    private TextView phone;
    private TextView bus;
    private TextView status;
    private Button backtoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        regid = getIntent().getStringExtra("regid");
        mdatabase = FirebaseDatabase.getInstance().getReference().child("reg").child(regid);
        mdb = FirebaseDatabase.getInstance().getReference().child("Appr");

        //Toast.makeText(this, regid, Toast.LENGTH_SHORT).show();

        address = (TextView) findViewById(R.id.AddressDesc);
        dept = (TextView) findViewById(R.id.deptDesc);
        dob = (TextView) findViewById(R.id.dobDesc);
        email = (TextView) findViewById(R.id.emailDesc);
        gender = (TextView) findViewById(R.id.genderDesc);
        rank = (TextView) findViewById(R.id.rankDesc);
        name = (TextView) findViewById(R.id.nameDesc);
        perc10 = (TextView) findViewById(R.id.tenDesc);
        per12 = (TextView) findViewById(R.id.twDesc);
        phone = (TextView) findViewById(R.id.phoneDesc);
        bus = (TextView) findViewById(R.id.busDesc);
        status = (TextView) findViewById(R.id.status);
        backtoHome = (Button) findViewById(R.id.backtohome);

        backtoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(results.this, HomeScreen.class));
            }
        });

        mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                address.setText(dataSnapshot.child("address").getValue().toString());
                dept.setText(dataSnapshot.child("dept").getValue().toString());
                dob.setText(dataSnapshot.child("dob").getValue().toString());
                email.setText(dataSnapshot.child("email").getValue().toString());
                gender.setText(dataSnapshot.child("gender").getValue().toString());
                rank.setText(dataSnapshot.child("jeeRank").getValue().toString());
                name.setText(dataSnapshot.child("name").getValue().toString());
                perc10.setText(dataSnapshot.child("percentage_10th").getValue().toString());
                per12.setText(dataSnapshot.child("percentage_12th").getValue().toString());
                phone.setText(dataSnapshot.child("phone").getValue().toString());
                bus.setText(dataSnapshot.child("services").getValue().toString());

                onStart();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                onStart();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                onStart();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onStart(){
        super.onStart();


        mdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(regid)){
                    status.setText("Status :- Approved");
                    status.setTextColor(Color.GREEN);
                }
                else{
                    status.setText("Status :- Pending");
                    status.setTextColor(Color.RED);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
