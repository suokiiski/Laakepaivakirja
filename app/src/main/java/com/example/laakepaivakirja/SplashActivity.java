package com.example.laakepaivakirja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.window.SplashScreen;

/**
 * SplashScreen-activity
 * @author Kirsi Tolonen
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            /**
             * Metodi, joka asettaa SplashScreen esille, kun sovellus käynnistyy
             */
            public void run(){
                startActivity(new Intent(SplashActivity.this,MainActivity.class ));
                finish();
            }
        }, 2000);

    }
}