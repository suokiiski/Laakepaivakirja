package com.example.laakepaivakirja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laakepaivakirja.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LisaaActivity extends AppCompatActivity {
    private EditText name, instructions, hour, min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisaa);
        name = (EditText) findViewById(R.id.nameInput);
        instructions = (EditText) findViewById(R.id.instructionInput);
        hour = (EditText) findViewById(R.id.hoursInput);
        min = (EditText) findViewById(R.id.minsInput);

        Intent intent = getIntent();

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

    public void sendData (View v) {
        String strName = name.getText().toString();
        String strInstructions = instructions.getText().toString();
        String strMin = min.getText().toString();
        String strHour = hour.getText().toString();

        if (strName.isEmpty()) {
            Toast.makeText(this, "Lisää lääkkeen nimi!", Toast.LENGTH_SHORT).show();
            return;
        } else if (strInstructions.isEmpty()) {
            Toast.makeText(this, "Lisää käyttöohjeet!", Toast.LENGTH_SHORT).show();
            return;
        } else if (strHour.isEmpty() || strMin.isEmpty()) {
            Toast.makeText(this, "Lisää aika!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            MedicineSingleton.getInstance().addMedicine(strName, strInstructions, Integer.parseInt(strHour), Integer.parseInt(strMin));
            Toast.makeText(this, "Lisätty!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void goToMain (View v) {
        Intent returnBack = new Intent (this, MainActivity.class);
        startActivity(returnBack);
    }
}
