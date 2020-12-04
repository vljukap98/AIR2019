package com.example.alarmio.alarm_funkcije;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alarmio.MainActivity;
import com.example.alarmio.pokretanje_alarma.Alarmio;
import com.example.alarmio.R;
import com.example.database.DAO;
import com.example.database.MyDatabase;
import com.example.database.entities.Alarm;
import com.example.database.entities.PonavljaSeDanom;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AzurirajAlarm extends AppCompatActivity implements View.OnClickListener {

    Alarm alarm;
    Button btnVrijeme, btnDatum, btnIzmjeni, btnPonavljanje;
    CheckBox chkPon, chkUto, chkSri, chkCet, chkPet, chkSub, chkNed;
    String vrijemeObavijest;
    MyDatabase myDatabase;
    EditText opisTekst;
    List<String> ponavljajuciDani = new ArrayList<String>();
    Boolean ponavljanje;
    private static DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azuriraj_alarm);
        btnVrijeme = findViewById(R.id.btnVrijeme);
        btnDatum = findViewById(R.id.btnDatum);
        btnIzmjeni = findViewById(R.id.btnIzmjeni);
        btnPonavljanje = findViewById(R.id.btnSvakiDan);
        chkPon = findViewById(R.id.chkPon);
        chkUto = findViewById(R.id.chkUto);
        chkSri = findViewById(R.id.chkSri);
        chkCet = findViewById(R.id.chkCet);
        chkPet = findViewById(R.id.chkPet);
        chkSub = findViewById(R.id.chkSub);
        chkNed = findViewById(R.id.chkNed);
        btnVrijeme.setOnClickListener(this);
        btnDatum.setOnClickListener(this);
        btnIzmjeni.setOnClickListener(this);
        btnPonavljanje.setOnClickListener(this);
        myDatabase = MyDatabase.getInstance(getApplicationContext());
        opisTekst = findViewById(R.id.opisTekst);
        dao = MyDatabase.getInstance(getApplicationContext()).getDAO();
        Popunjavanje();
    }

    private void Popunjavanje(){
        alarm = (Alarm) getIntent().getSerializableExtra("Alarm");
        ponavljajuciDani = dao.loadAllPonavljanjeByAlarm(alarm.getAlarmId());

        for(String dan : ponavljajuciDani)
        {
            if(dan.equals("Ponedjeljak"))
                chkPon.setChecked(true);
            else if(dan.equals("Utorak"))
                chkUto.setChecked(true);
            else if (dan.equals("Srijeda"))
                chkSri.setChecked(true);
            else if (dan.equals("Četvrtak"))
                chkCet.setChecked(true);
            else if (dan.equals("Petak"))
                chkPet.setChecked(true);
            else if (dan.equals("Subota"))
                chkSub.setChecked(true);
            else if (dan.equals("Nedjelja"))
                chkNed.setChecked(true);
        }
        ponavljajuciDani.clear();
        btnVrijeme.setText(alarm.getVrijeme());
        btnDatum.setText(alarm.getDatum());
        opisTekst.setText(alarm.getOpis());
    }

    @Override
    public void onClick(View view) {
        if (view == btnVrijeme){
            odaberiVrijeme();
        }else if (view == btnDatum){
            odaberiDatum();
        }else if (view == btnPonavljanje){
            postaviSve();
        }
        else{
            Azuriraj(this);
        }
    }

    private void postaviSve() {
        chkPon.setChecked(true);
        chkUto.setChecked(true);
        chkSri.setChecked(true);
        chkCet.setChecked(true);
        chkPet.setChecked(true);
        chkSub.setChecked(true);
        chkNed.setChecked(true);
    }

    private void Azuriraj(Context context) {
        if (btnVrijeme.getText().toString().equals("Odaberi vrijeme") || btnDatum.getText().toString().equals("Odaberi datum")){
            Toast.makeText(this, "Molimo odaberite vrijeme ili datum", Toast.LENGTH_SHORT).show();
        }else{
            if(chkPon.isChecked() || chkUto.isChecked() || chkSri.isChecked() || chkCet.isChecked() || chkPet.isChecked() || chkSub.isChecked() || chkNed.isChecked())
                ponavljanje = true;

            if(chkPon.isChecked())
                ponavljajuciDani.add("Ponedjeljak");

            if (chkUto.isChecked())
                ponavljajuciDani.add("Utorak");

            if (chkSri.isChecked())
                ponavljajuciDani.add("Srijeda");

            if (chkCet.isChecked())
                ponavljajuciDani.add("Četvrtak");

            if (chkPet.isChecked())
                ponavljajuciDani.add("Petak");

            if (chkSub.isChecked())
                ponavljajuciDani.add("Subota");

            if (chkNed.isChecked())
                ponavljajuciDani.add("Nedjelja");

            String datum = btnDatum.getText().toString().trim();
            String vrijeme = btnVrijeme.getText().toString().trim();
            String opis = opisTekst.getText().toString();
            alarm.setDatum(datum);
            alarm.setVrijeme(vrijeme);
            alarm.setOpis(opis);
            dao.deleteAllPonavljanjeByAlarm(alarm.getAlarmId());

            if(ponavljanje=true)
            {
                for (String dan:ponavljajuciDani) {
                    if(dan=="Ponedjeljak")
                    {
                        PonavljaSeDanom ponavljaSeDanom = new PonavljaSeDanom();
                        ponavljaSeDanom.setDanId(dao.loadAllDanByNaziv(dan).get(0).getDanId());
                        ponavljaSeDanom.setAlarmId(alarm.getAlarmId());
                        dao.insertPonavljaSeDanom(ponavljaSeDanom);
                    }else if(dan=="Utorak")
                    {
                        PonavljaSeDanom ponavljaSeDanom = new PonavljaSeDanom();
                        ponavljaSeDanom.setDanId(dao.loadAllDanByNaziv(dan).get(0).getDanId());
                        ponavljaSeDanom.setAlarmId(alarm.getAlarmId());
                        dao.insertPonavljaSeDanom(ponavljaSeDanom);
                    }else if(dan=="Srijeda"){
                        PonavljaSeDanom ponavljaSeDanom = new PonavljaSeDanom();
                        ponavljaSeDanom.setDanId(dao.loadAllDanByNaziv(dan).get(0).getDanId());
                        ponavljaSeDanom.setAlarmId(alarm.getAlarmId());
                        dao.insertPonavljaSeDanom(ponavljaSeDanom);
                    }else if(dan=="Četvrtak"){
                        PonavljaSeDanom ponavljaSeDanom = new PonavljaSeDanom();
                        ponavljaSeDanom.setDanId(dao.loadAllDanByNaziv(dan).get(0).getDanId());
                        ponavljaSeDanom.setAlarmId(alarm.getAlarmId());
                        dao.insertPonavljaSeDanom(ponavljaSeDanom);
                    }else if(dan=="Petak"){
                        PonavljaSeDanom ponavljaSeDanom = new PonavljaSeDanom();
                        ponavljaSeDanom.setDanId(dao.loadAllDanByNaziv(dan).get(0).getDanId());
                        ponavljaSeDanom.setAlarmId(alarm.getAlarmId());
                        dao.insertPonavljaSeDanom(ponavljaSeDanom);
                    }else if(dan=="Subota"){
                        PonavljaSeDanom ponavljaSeDanom = new PonavljaSeDanom();
                        ponavljaSeDanom.setDanId(dao.loadAllDanByNaziv(dan).get(0).getDanId());
                        ponavljaSeDanom.setAlarmId(alarm.getAlarmId());
                        dao.insertPonavljaSeDanom(ponavljaSeDanom);
                    }else if(dan=="Nedjelja"){
                        PonavljaSeDanom ponavljaSeDanom = new PonavljaSeDanom();
                        ponavljaSeDanom.setDanId(dao.loadAllDanByNaziv(dan).get(0).getDanId());
                        ponavljaSeDanom.setAlarmId(alarm.getAlarmId());
                        dao.insertPonavljaSeDanom(ponavljaSeDanom);
                    }
                }
            }

            ponavljajuciDani.clear();
            dao.updateAlarmi(alarm);

            postaviAlarm(datum, vrijeme, opis);
        }
    }

    private void odaberiDatum() {
        Calendar calendar = Calendar.getInstance();
        int godina = calendar.get(Calendar.YEAR);
        int mjesec = calendar.get(Calendar.MONTH);
        int dan = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int g, int m, int d) {
                btnDatum.setText(d + "/" + (m+1) +"/"+g);
            }
        },godina,mjesec,dan);
        datePickerDialog.show();
    }

    private void odaberiVrijeme() {
        Calendar calendar = Calendar.getInstance();
        int sati = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int s, int m) {
                vrijemeObavijest= s + ":" + m;
                btnVrijeme.setText(vrijemeObavijest);
            }
        },sati,minute, false);
        timePickerDialog.show();
    }

    private void postaviAlarm(String datum, String vrijeme, String opis){
        final Intent intent = new Intent(this, Alarmio.class);
        ServiceCaller(intent, datum, vrijeme, opis);
    }

    private void ServiceCaller(Intent intent, String datum, String vrijeme, String opis) {
        stopService(intent);

        String[] rastavVrijeme = vrijeme.split(":");

        intent.putExtra("alarmOpis", opis);
        intent.putExtra("alarmDatum", datum);
        intent.putExtra("alarmSati", Integer.parseInt(rastavVrijeme[0]));
        intent.putExtra("alarmMinute", Integer.parseInt(rastavVrijeme[1]));

        startService(intent);
        Intent popis = new Intent(this, MainActivity.class);
        popis.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(popis);
    }
}