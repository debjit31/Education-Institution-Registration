package com.example.airlinebookingsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class academic_details extends AppCompatActivity {


    private EditText class10;
    private EditText class12;
    private EditText dept;
    private EditText jee;
    private Button next;

    private String reg_id;

    private DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_details);

        class10 = (EditText) findViewById(R.id.class10_ed);
        class12 = (EditText) findViewById(R.id.class12_ed);
        dept = (EditText) findViewById(R.id.dept_ed);
        jee = (EditText) findViewById(R.id.jee_ed);

        next = (Button) findViewById(R.id.nextBtn);

        reg_id = getIntent().getStringExtra("register");
        mdatabase = FirebaseDatabase.getInstance().getReference("reg").child(reg_id);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_academic_data();
            }
        });

    }

    public void add_academic_data(){

        String mclass10 = class10.getText().toString().trim();
        String mclass12 = class12.getText().toString().trim();
        String mjee = jee.getText().toString().trim();
        String mdept = dept.getText().toString().trim();

        if(TextUtils.isEmpty(mclass10) || TextUtils.isEmpty(mclass12) || TextUtils.isEmpty(mjee) || TextUtils.isEmpty(mdept)){

            Toast.makeText(this, "Fields Cannot be Empty", Toast.LENGTH_SHORT).show();
        }
        else{
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("percentage_10th", mclass10);
            objectMap.put("percentage_12th", mclass12);
            objectMap.put("jeeRank", mjee);
            objectMap.put("dept", mdept);


            mdatabase.updateChildren(objectMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(academic_details.this, bus_service.class);
                        intent.putExtra("register", reg_id);
                        startActivity(intent);
                        //Toast.makeText(academic_details.this, "Success!!", Toast.LENGTH_SHORT).show();
                        class10.setText("");
                        class12.setText("");
                        jee.setText("");
                        dept.setText("");
                    }
                }
            });
        }

    }
}
