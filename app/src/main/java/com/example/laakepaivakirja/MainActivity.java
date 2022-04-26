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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.laakepaivakirja.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    CalendarView calendar;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setSelectedItemId(R.id.koti);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.koti:
                         return true;
                    case R.id.lisaa:
                        startActivity(new Intent(getApplicationContext()
                                , LisaaActivity.class));
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
        });

        lv = findViewById(R.id.medList);
        lv.setAdapter(new ArrayAdapter<Medicine>(this, android.R.layout.simple_list_item_1, MedicineSingleton.getInstance().getMedicines()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        calendar = findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String Date = dayOfMonth + "-" + (month + 1) + "-" + year;
                    }
        });
    }

    public void addIsPressed (View v) {
        Intent intent = new Intent(this, LisaaActivity.class);
        startActivity(intent);
    }
}