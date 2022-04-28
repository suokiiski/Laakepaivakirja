package com.example.laakepaivakirja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MedicineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(MainActivity.EXTRA_MESSAGE, 0);

        TextView tvName = findViewById(R.id.medName);
        TextView tvInstruction = findViewById(R.id.medInstruction);
        TextView tvHour = findViewById(R.id.medHours);
        TextView tvMin = findViewById(R.id.medMins);

        tvName.setText(MedicineSingleton.getInstance().getMedicine(i).getName());
        tvInstruction.setText(MedicineSingleton.getInstance().getMedicine(i).getInstruction());
        tvHour.setText((MedicineSingleton.getInstance().getMedicine(i).getHours()));
        tvMin.setText(MedicineSingleton.getInstance().getMedicine(i).getMins());
    }

    public void deleteItem (View v) {

    }
}