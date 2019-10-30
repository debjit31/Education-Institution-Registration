package com.example.airlinebookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class search extends AppCompatActivity {

    private EditText searchId;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchId = (EditText) findViewById(R.id.search_id);
        searchBtn = (Button) findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regid = searchId.getText().toString();
                if(TextUtils.isEmpty(regid)){
                    Toast.makeText(search.this, "Field Cannot be Empty", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(search.this, results.class);
                    intent.putExtra("regid", regid);
                    startActivity(intent);
                }
            }
        });
    }
}
