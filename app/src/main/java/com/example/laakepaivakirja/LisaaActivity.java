package com.example.laakepaivakirja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Aktivity, jonka käytetään lääkkeen tietojen tallentamiseen
 * @author Vera Finogenova
 */
public class LisaaActivity extends AppCompatActivity {
    private EditText name, instructions;
    private TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisaa);
        name = (EditText) findViewById(R.id.nameInput);
        instructions = (EditText) findViewById(R.id.instructionInput);
        tvTime = (TextView) findViewById(R.id.timeInput);
        tvTime.setText(getTime());

        Intent intent = getIntent();

        //Seuraava koodi rakentaa valikkopalkin
        //Tekijä: Kirsi Tolonen
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setSelectedItemId(R.id.lisaa);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.lisaa:
                        return true;
                    case R.id.koti:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.more:
                        startActivity(new Intent(getApplicationContext()
                                , MuuActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        })
    ;}

    /**
     * Kun nappi on painettu, metodi tallentaa syötetyt tiedot: tekee niistä olion, jonka lisätään listaan, jonka tallennetaan SharedPreferensseihin
     * Jos joku kenttä on tyhjä, se ilmoittaa tästä
     * @param v UI elementti (button)
     */
    public void sendData (View v) {
        String strName = name.getText().toString();
        String strInstructions = instructions.getText().toString();
        String strTime = tvTime.getText().toString();
        Intent intent = new Intent(LisaaActivity.this, MainActivity.class);

        if (strName.isEmpty()) {
            Toast.makeText(this, "Lisää lääkkeen nimi!", Toast.LENGTH_SHORT).show();
            return;
        } else if (strInstructions.isEmpty()) {
            Toast.makeText(this, "Lisää käyttöohjeet!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            MedicineSingleton.getInstance().addMedicine(strName, strInstructions, strTime);
            SharedPreferences sharePref = getSharedPreferences("medPref", Activity.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharePref.edit();
            prefEditor.putString("MedsKey", MedicineSingleton.getInstance().getMedicinesJson());
            prefEditor.commit();
            Toast.makeText(this, "Lisätty!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            return;
        }

    }

    /**
     * Metodi avaa AlarmActivity-aktiviteetti kun "aseta hälytys" -nappi on painettu
     * @param v UI elementti (button)
     */
    public void goToAlarm (View v) {
        Intent toAlarm = new Intent (this, AlarmActivity.class);
        startActivity(toAlarm);
    }

    /**
     * Metodi, joka saa ajan timepickeriltä ja palauttaa se String-muodossa
     * @return hälytyksen aika String muodossa
     */
    public String getTime() {
        Intent intent = new Intent();
        String time = intent.getStringExtra(AlarmActivity.EXTRA_MESSAGE);
        return time;
    }

}
