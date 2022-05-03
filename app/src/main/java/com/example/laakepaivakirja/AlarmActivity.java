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
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.laakepaivakirja.databinding.ActivityAlarm3Binding;
import com.google.android.material.timepicker.MaterialTimePicker;
import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private ActivityAlarm3Binding binding;
    private MaterialTimePicker valitsin;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm3);
        binding = ActivityAlarm3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createNotificationChannel();


        binding.SetHalytys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                naytaAjanValitsin();
            }
        });

        binding.poistaHalytys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                poistaHaly();
            }
        });
    }


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


    }

    private void naytaAjanValitsin(){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(AlarmActivity.this, new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                //Tee tässä jotain sille ajalle
                Log.d("AIKA", hour+":"+min); //Tämä printtaa ajan nyt lokiin
                asetaHalytys(hour, min);
            }
        },hour,minute,true);
        timePickerDialog.show();
    }


    private void poistaHaly() {

        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        if(alarmManager == null){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Hälytys poistettu!", Toast.LENGTH_SHORT).show();
    }


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