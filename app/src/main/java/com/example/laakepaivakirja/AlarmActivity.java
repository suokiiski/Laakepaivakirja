package com.example.laakepaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.laakepaivakirja.databinding.ActivityAlarm3Binding;
import com.google.android.material.timepicker.MaterialTimePicker;
import java.util.Calendar;
import android.widget.TimePicker.OnTimeChangedListener;

/**
 * Sisältää hälytykseen liittyvät metodit ja kuvaa hälytyksen toimintaa
 * @author Atte Kilpeläinen
 */
public class AlarmActivity extends AppCompatActivity {

    private ActivityAlarm3Binding binding;
    private MaterialTimePicker valitsin;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private int hourCh;
    private int minCh;
    private String time;
    public static final String EXTRA_MESSAGE = "time";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm3);
        // Yhdistää xml tiedosto View-objekteihin
        binding = ActivityAlarm3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //luo ilmoituskanavan
        createNotificationChannel();


        //Lisää napeille toimintaa ja sidotaan järjestelmä hälytyspyyntöön
        binding.SetHalytys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                naytaAjanValitsin();
            }
        });

        //Lisää napeille toimintaa ja sidotaan järjestelmä hälytyspyyntöön
        binding.poistaHalytys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                poistaHaly();
            }
        });
    }


    // Metodi asettaa hälytyksen aikaan, jonka se saa parametrina
    private void asetaHalytys(int hour, int min) {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        if(calendar.before(calendar)){
            calendar.add(Calendar.DATE, 1);
        }

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Hälytys asetettu!", Toast.LENGTH_SHORT).show();

        Intent paluu = new Intent(this, AlarmActivity.class);
        startActivity(paluu);

    }

    // Metodi laittaa ajanvalitsin (timepicker) esille, ja asettaa hälytyksen valittuun aikaan AsetaHalytys() metodilla
    private void naytaAjanValitsin(){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(AlarmActivity.this, new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                Log.d("AIKA", hour+":"+min);
                asetaHalytys(hour, min);

            }
        },hour,minute,true);
        timePickerDialog.show();

    }



    // Poistaa hälytys, kun "poista hälytys" nappi on painettu, jos se ei ole null
    private void poistaHaly() {

        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        if(alarmManager == null){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Hälytys poistettu!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Siirtyy toiseen aktiviteettiin, kun "takaisin" nappi on painettu
     * @param v UI komponentti (button)
     */
    public void goBack (View v) {
        Intent intent = new Intent(this, LisaaActivity.class);
        intent.putExtra(EXTRA_MESSAGE, time);
        startActivity(intent);
    }


    // Metodi luo ilmoituskanavan, joka tekee pop-up ilmoituksen
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "HalytysReminderChannel";
            String description = "Lääkepäiväkirjan Alarm Channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("Halytys", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}