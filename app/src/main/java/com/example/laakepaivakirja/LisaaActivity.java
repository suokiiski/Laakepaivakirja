package com.example.laakepaivakirja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laakepaivakirja.databinding.ActivityMainBinding;

public class LisaaActivity extends AppCompatActivity {
    private EditText name, instructions, hour, min;
    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisaa);
        name = (EditText) findViewById(R.id.nameInput);
        instructions = (EditText) findViewById(R.id.instructionInput);
        hour = (EditText) findViewById(R.id.hoursInput);
        min = (EditText) findViewById(R.id.minsInput);
        msg = (TextView) findViewById(R.id.errorMsg);

        Intent intent = getIntent();
    }

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
}