package com.example.alarmio.pokretanje_alarma;


import android.app.Service;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Alarmio extends Service { //Kada se zatvori aplikacija alarm ne zvoni
    private Integer alarmHour;
    private Integer alarmMinute;
    private Ringtone ringtone;
    private Timer t = new Timer();
    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        alarmHour = intent.getIntExtra("alarmSati", 0);
        alarmMinute = intent.getIntExtra("alarmMinute", 0);

        ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);

        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == alarmHour &&
                        Calendar.getInstance().get(Calendar.MINUTE) == alarmMinute &&
                        Calendar.getInstance().get(Calendar.SECOND) < 10){
                    ringtone.play();
                }
                else {
                    ringtone.stop();
                }
            }
        }, 0, 2000);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        ringtone.stop();
        t.cancel();
        super.onDestroy();
    }
}
