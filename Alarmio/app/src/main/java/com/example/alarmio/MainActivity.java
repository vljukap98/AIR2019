package com.example.alarmio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.database.MyDatabase;
import com.example.database.entities.Alarm;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button kreirajAlarm;
    PocetniDogadaj pocetniDogadaj;
    RecyclerView recyclerView;
    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kreirajAlarm = findViewById(R.id.btnKreirajAlarm);
        recyclerView = findViewById(R.id.recyclerview);
        kreirajAlarm.setOnClickListener(this);
        myDatabase = MyDatabase.getInstance(getApplicationContext());
    }

    @Override
    protected void onResume(){
        super.onResume();
        postaviDogadaj();
    }

    private void postaviDogadaj(){
        List<Alarm> alarmList = myDatabase.getDAO().loadAllAlarmi();
        pocetniDogadaj = new PocetniDogadaj(getApplicationContext(),alarmList);
        recyclerView.setAdapter(pocetniDogadaj);
    }

    @Override
    public void onClick(View view) {
        if(view == kreirajAlarm){
            novaAktivnost();
        }
    }

    private void novaAktivnost(){
        Intent intent = new Intent(getApplicationContext(),KreirajAlarm.class);
        startActivity(intent);
    }
}