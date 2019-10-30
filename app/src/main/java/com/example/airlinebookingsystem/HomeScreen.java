package com.example.airlinebookingsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {

    private Button register;
    private Button showStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        register = (Button) findViewById(R.id.registerBtn);
        showStatus = (Button) findViewById(R.id.showRegStatus);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, personal_info.class));
            }
        });

        showStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomeScreen.this, "Update Coming Soon!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeScreen.this, search.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu,menu);


        return true;
    }
}
