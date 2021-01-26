package com.example.falarmio.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.falarmio.AlarmioLoadData;
import com.example.falarmio.R;

public class AlarmList extends AppCompatActivity {
    AlarmioLoadData alarmioLoadData;
    RecyclerView rv;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);
        alarmioLoadData = new AlarmioLoadData();
        rv = findViewById(R.id.alarmListRecV);
        context = getApplicationContext();

        alarmioLoadData.DatabaseInitialize(context);
        alarmioLoadData.postaviDogadaj(rv, context);
    }
}