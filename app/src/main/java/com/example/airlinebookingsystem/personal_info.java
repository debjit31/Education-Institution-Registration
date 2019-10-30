package com.example.airlinebookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class personal_info extends AppCompatActivity {

    private DatabaseReference mdatabase;
    private EditText mName;
    private EditText mAddress;
    private EditText mPhone;
    private Spinner mGender;
    private EditText mEmail;
    private EditText mDob;
    private Button submit;

    private String reg_id;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("reg");

        mName = (EditText) findViewById(R.id.nameEd);
        mAddress = (EditText) findViewById(R.id.addressEd);
        mPhone = (EditText) findViewById(R.id.phoneEd);
        mGender = (Spinner) findViewById(R.id.genderEd);
        mEmail = (EditText) findViewById(R.id.emailEd);
        mDob = (EditText) findViewById(R.id.dobEd);
        submit = (Button) findViewById(R.id.submitBtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGender.setAdapter(adapter);

        mGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_pers_data();
            }
        });

    }

    public void add_pers_data() {


        try{
            String name = mName.getText().toString().trim();
            String email = mEmail.getText().toString().trim();
            String phone = mPhone.getText().toString().trim();
            String address = mAddress.getText().toString().trim();
            String dob = mDob.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address) || TextUtils.isEmpty(dob)) {
                Toast.makeText(this, "Fields cannot be empty!!!", Toast.LENGTH_SHORT).show();
            } else {
                HashMap<String, String> newMap = new HashMap<String, String>();
                newMap.put("name", name);
                newMap.put("email", email);
                newMap.put("phone", phone);
                newMap.put("gender", gender);
                newMap.put("address", address);
                newMap.put("dob", dob);

                reg_id = String.valueOf(System.currentTimeMillis());

                mdatabase.child(reg_id).setValue(newMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(personal_info.this, academic_details.class);
                            intent.putExtra("register", reg_id);
                            startActivity(intent);


                        }
                    }
                });
            }
        }catch (Exception e){
            Toast.makeText(this, "Exception Occured" + e, Toast.LENGTH_SHORT).show();
        }
        finally {
            mName.setText("");
            mEmail.setText("");
            mPhone.setText("");
            mAddress.setText("");
            mDob.setText("");
        }






    }
}
