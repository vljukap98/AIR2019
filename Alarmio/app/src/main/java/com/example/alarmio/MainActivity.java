package com.example.alarmio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.falarmio.AlarmioLoadData;
import com.example.falarmio.alarm_funkcije.KreirajAlarm;
import com.example.falarmio.list.AlarmList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button kreirajAlarm, otvori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kreirajAlarm = findViewById(R.id.btnKreirajAlarm);
        kreirajAlarm.setOnClickListener(this);
        otvori = findViewById(R.id.otvoriListu);
        otvori.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if(view == kreirajAlarm){
                Intent intent = new Intent(getApplicationContext(), KreirajAlarm.class);
                startActivity(intent);
            } else if(view == otvori) {
                Intent intent = new Intent(getApplicationContext(), AlarmList.class);
                startActivity(intent);
            }
        }
}