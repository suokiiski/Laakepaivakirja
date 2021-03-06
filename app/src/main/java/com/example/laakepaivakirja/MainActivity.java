package com.example.laakepaivakirja;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CalendarView;
import java.util.ArrayList;
import java.util.List;
import com.example.laakepaivakirja.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Ohjelman päänäkymä
 * @author Kirsi Tolonen
 */
public class MainActivity extends AppCompatActivity {
    CalendarView calendar;
    ListView lv;
    List <Medicine> meds;
    public static final String EXTRA_MESSAGE = "Lääkemuistutuksen sivu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Seuraava koodi rakentaa valikkopalkin
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

        /*Seuraava koodi saa listan Gson Stringinä SharedPreferenseiltä, sitten muunnaa se Medicine-tyyppiseksi listaksi
        ja lisää sen alkioita listview-näkymälle.
         */

        lv = findViewById(R.id.medList);
        TypeToken<List<Medicine>> token = new TypeToken<List<Medicine>>() {};
        Gson gson = new Gson();
        SharedPreferences sharePref = getSharedPreferences("medPref", Activity.MODE_PRIVATE);
        String medsJson = sharePref.getString("MedsKey", "");
        Log.d("Tag", "Json on " + medsJson);
        if (!medsJson.equals("")) {
            meds = gson.fromJson(medsJson, token.getType());
            for (Medicine m : meds) {
                MedicineSingleton.getInstance().addMedicine(m);
            }
        }
        lv.setAdapter(new ArrayAdapter<Medicine>(this, android.R.layout.simple_list_item_1, MedicineSingleton.getInstance().getMedicines()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Metodi avaa uuden näkymän, kun listan elementti on painettu
             * @param adapterView adapteri
             * @param view UI elementti (listview)
             * @param i elementin indeksi
             * @param l
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MedicineActivity.class);
                intent.putExtra(EXTRA_MESSAGE, i);
                startActivity(intent);
            }
        });
    }
}