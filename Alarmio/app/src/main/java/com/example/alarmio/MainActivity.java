package com.example.alarmio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.falarmio.MainActivityFramework;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.alarmio.R.layout.activity_main);
        Context context = getApplicationContext();

        Intent intent = new Intent(context, MainActivityFramework.class);
        startActivity(intent);
        }
}