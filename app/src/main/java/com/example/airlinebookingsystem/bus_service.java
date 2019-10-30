package com.example.airlinebookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class bus_service extends AppCompatActivity {

    private Switch bus;
    private LinearLayout busLayout;
    private Button next;

    /*private EditText pickup_dest;
    private EditText dropoff_dest;
    //private Button bus_next;*/

    //private Button nextPage;
    private DatabaseReference mdatabase;
    private String reg_id;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_service);

        reg_id = getIntent().getStringExtra("register");
        mdatabase = FirebaseDatabase.getInstance().getReference().child("reg").child(reg_id);

        bus = (Switch) findViewById(R.id.switch1);
        //busLayout = (LinearLayout) findViewById(R.id.bus_det_lay);
        next = (Button) findViewById(R.id.nextBtn2);

        //pickup_dest = (EditText) findViewById(R.id.pickup_ed);
       // dropoff_dest = (EditText) findViewById(R.id.dropoff_ed);
        //bus_next = (Button) findViewById(R.id.nextBtn);
        //nextPage = (Button) findViewById(R.id.nextBtn2);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for not avaiaing bus service
                add_bus_response("Not Availed for Bus Services");
            }
        });

        bus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            add_bus_response("Availed for Bus Services..");
                        }
                    });
                }
                else{
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                add_bus_response("Not Availed for Bus Services");
                        }
                    });
                }
            }
        });
    }
    public void add_bus_response(String msg){

        Map<String, Object> newMap = new HashMap<>();
        newMap.put("services",msg);

        //reg_id = getIntent().getStringExtra("register");
        mdatabase.updateChildren(newMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(bus_service.this,TokenPage.class);
                    intent.putExtra("register", reg_id);
                    startActivity(intent);
                    //Toast.makeText(bus_service.this, "Response Added!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
