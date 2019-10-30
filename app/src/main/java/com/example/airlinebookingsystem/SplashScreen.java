package com.example.airlinebookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private static int  SPLASH_TIMEOUT = 3500;
    private ImageView splimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splimg = (ImageView) findViewById(R.id.splash_img);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this, HomeScreen.class));
                finish();
            }
        }, SPLASH_TIMEOUT);
        splimg.animate().alpha(1f).setDuration(3000);
    }
}
