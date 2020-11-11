package com.example.alarmio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.database.DAO;
import com.example.database.MyDatabase;
import com.example.database.entities.Alarm;
import com.example.database.entities.Dani;
import com.example.database.entities.PonavljaSeDanom;
import com.example.database.entities.PonavljajuciAlarm;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button kreirajAlarm, btnObrisi;
    PocetniDogadaj pocetniDogadaj;
    RecyclerView recyclerView;
    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kreirajAlarm = findViewById(R.id.btnKreirajAlarm);
        btnObrisi = findViewById(R.id.btnObrisi);
        recyclerView = findViewById(R.id.recyclerview);
        kreirajAlarm.setOnClickListener(this);
        myDatabase = MyDatabase.getInstance(getApplicationContext());

        DAO dao = myDatabase.getDAO();
        Dani danPon = new Dani();
        danPon.setNaziv("Ponedjeljak");
        dao.insertDani(danPon);

        Dani danUto = new Dani();
        danPon.setNaziv("Utorak");
        dao.insertDani(danUto);

        Dani danSri = new Dani();
        danPon.setNaziv("Srijeda");
        dao.insertDani(danSri);

        Dani danCet = new Dani();
        danPon.setNaziv("Četvrtak");
        dao.insertDani(danCet);

        Dani danPet = new Dani();
        danPon.setNaziv("Petak");
        dao.insertDani(danPet);

        Dani danSub = new Dani();
        danPon.setNaziv("Subota");
        dao.insertDani(danSub);

        Dani danNed = new Dani();
        danPon.setNaziv("Nedjelja");
        dao.insertDani(danNed);
    }


    @Override
    protected void onResume(){
        super.onResume();
        postaviDogadaj();

    }

    public void postaviDogadaj(){
        List<Alarm> alarmList = myDatabase.getDAO().loadAllAlarmi();
        List<Dani> daniList = myDatabase.getDAO().loadAllDani();
        List<PonavljajuciAlarm> ponavljajuciAlarmList = myDatabase.getDAO().loadAllPonavljajuciAlarmi();
        List<PonavljaSeDanom> ponavljaSeDanomList = myDatabase.getDAO().loadAllPonavljaSeDanom();
        Log.d("ProvjeraPonavlja", ponavljajuciAlarmList.toString());
        pocetniDogadaj = new PocetniDogadaj(getApplicationContext(),alarmList, daniList, ponavljajuciAlarmList, ponavljaSeDanomList);
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