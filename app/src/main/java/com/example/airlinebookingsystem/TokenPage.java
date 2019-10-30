package com.example.airlinebookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TokenPage extends AppCompatActivity {

    private Button home;
    private TextView tokenDisplay;
    private String reg_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_page);

        reg_id = getIntent().getStringExtra("register");

        tokenDisplay = (TextView) findViewById(R.id.token);
        home = (Button) findViewById(R.id.gotoHome);

        tokenDisplay.setText(reg_id);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TokenPage.this, HomeScreen.class));
            }
        });


    }
}
