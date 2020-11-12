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
        if(dao.daniTableSize() == 0) {
            Dani danPon = new Dani();
            danPon.setNaziv("Ponedjeljak");
            danPon.setDanId((int) dao.insertDani(danPon)[0]);

            Dani danUto = new Dani();
            danUto.setNaziv("Utorak");
            danUto.setDanId((int) dao.insertDani(danUto)[0]);

            Dani danSri = new Dani();
            danSri.setNaziv("Srijeda");
            danSri.setDanId((int) dao.insertDani(danSri)[0]);

            Dani danCet = new Dani();
            danCet.setNaziv("Četvrtak");
            danCet.setDanId((int) dao.insertDani(danCet)[0]);

            Dani danPet = new Dani();
            danPet.setNaziv("Petak");
            danPet.setDanId((int) dao.insertDani(danPet)[0]);

            Dani danSub = new Dani();
            danSub.setNaziv("Subota");
            danSub.setDanId((int) dao.insertDani(danSub)[0]);

            Dani danNed = new Dani();
            danNed.setNaziv("Nedjelja");
            danNed.setDanId((int) dao.insertDani(danNed)[0]);
        }
    }


    @Override
    protected void onResume(){
        super.onResume();
        postaviDogadaj();

    }

    public void postaviDogadaj(){
        List<Alarm> alarmList = myDatabase.getDAO().loadAllAlarmi();
        List<Dani> daniList = myDatabase.getDAO().loadAllDani();
        List<PonavljaSeDanom> ponavljaSeDanomList = myDatabase.getDAO().loadAllPonavljaSeDanom();
        pocetniDogadaj = new PocetniDogadaj(getApplicationContext(),alarmList, daniList, ponavljaSeDanomList);
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