package com.example.laakepaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.laakepaivakirja.databinding.ActivityAlarm3Binding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
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

        Intent intent = getIntent();

        binding = ActivityAlarm3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createNotificationChannel();

        binding.ChooseAika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                naytaAjanValitsin();

            }

            private void naytaAjanValitsin() {

                valitsin = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_24H)
                        .setHour(20)
                        .setMinute(0)
                        .setTitleText("Valitse kellonaika")
                        .build();

                valitsin.show(getSupportFragmentManager(), "Halytys");
                valitsin.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        binding.ValitseAika.setText(valitsin.getHour() + " : " + valitsin.getMinute() + " ");
                    }

                });
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, valitsin.getHour());
                calendar.set(Calendar.MINUTE, valitsin.getMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
            }

        });

        binding.SetHalytys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                asetaHalytys();
            }
        });

    }


    private void asetaHalytys() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Hälytys asetettu!", Toast.LENGTH_SHORT).show();


        binding.poistaHalytys.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick (View view) {

        poistaHaly();
        }
    });
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