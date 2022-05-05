package com.example.laakepaivakirja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

/**
 * Aktiviteetti joka tulee näkyviin kun listview:n elementtiin on klikattu. Sisältää tiedot lääkkeestä
 * @author Vera Finogenova
 */
public class MedicineActivity extends AppCompatActivity {
    TextView tvName, tvInstruction, tvHour, tvMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(MainActivity.EXTRA_MESSAGE, 0);

        tvName = findViewById(R.id.medName);
        tvInstruction = findViewById(R.id.medInstruction);
        tvHour = findViewById(R.id.medHours);
        tvMin = findViewById(R.id.medMins);

        tvName.setText(MedicineSingleton.getInstance().getMedicine(i).getName());
        tvInstruction.setText(MedicineSingleton.getInstance().getMedicine(i).getInstruction());
        tvHour.setText((MedicineSingleton.getInstance().getMedicine(i).getTime()));
    }

    /**
     * Metodi, joka poistaa lääke listalta ja SharedPreferensseista, kun "poista" nappi on painettu
     * @param v UI elementti (nappi)
     */
    public void deleteItem (View v) {
        Medicine m = new Medicine(tvName.getText().toString(), tvInstruction.getText().toString(),
                tvHour.getText().toString());
        if (MedicineSingleton.getInstance().getMedicines().contains(m)) {
            MedicineSingleton.getInstance().getMedicines().remove(m);
            SharedPreferences sharePref = getSharedPreferences("medPref", Activity.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharePref.edit();
            prefEditor.putString("MedsKey", MedicineSingleton.getInstance().getMedicinesJson());
            prefEditor.commit();
            Toast.makeText(this, "Poistettu!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    /**
     * Metodi, joka avaa MainActivity, kun "takaisin" nappi on painettu
     * @param v UI elementti (nappi)
     */
    public void goBack (View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}