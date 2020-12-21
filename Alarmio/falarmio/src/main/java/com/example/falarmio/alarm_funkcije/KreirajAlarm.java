package com.example.falarmio.alarm_funkcije;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.falarmio.MainActivityFramework;
import com.example.falarmio.R;
import com.example.falarmio.android_notifikacija.AndroidNotifikacijaBrodcastReceiver;
import com.example.falarmio.pokretanje_alarma.Alarmio;
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

public class KreirajAlarm extends AppCompatActivity implements View.OnClickListener {
    RadioGroup tipNotifikacije;
    RadioButton odabranTipNotifikacije;
    Button btnVrijeme, btnDatum, btnDodaj, btnPonavljanje;
    CheckBox chkPon, chkUto, chkSri, chkCet, chkPet, chkSub, chkNed;
    String vrijemeObavijest, nazivNotifikacije;
    MyDatabase myDatabase;
    EditText opisTekst;
    ArrayList<String> ponavljajuciDani = new ArrayList<>();
    Boolean ponavljanje;
    private static DAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kreiraj_alarm);
        btnVrijeme = findViewById(R.id.btnVrijeme);
        btnDatum = findViewById(R.id.btnDatum);
        btnDodaj = findViewById(R.id.btnDodaj);
        btnPonavljanje = findViewById(R.id.btnSvakiDan);
        tipNotifikacije = findViewById(R.id.tipNotifikacije);
        chkPon = findViewById(R.id.chkPon);
        chkUto = findViewById(R.id.chkUto);
        chkSri = findViewById(R.id.chkSri);
        chkCet = findViewById(R.id.chkCet);
        chkPet = findViewById(R.id.chkPet);
        chkSub = findViewById(R.id.chkSub);
        chkNed = findViewById(R.id.chkNed);
        btnVrijeme.setOnClickListener(this);
        btnDatum.setOnClickListener(this);
        btnDodaj.setOnClickListener(this);
        btnPonavljanje.setOnClickListener(this);
        tipNotifikacije.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                odabranTipNotifikacije = findViewById(checkedId);
                nazivNotifikacije = odabranTipNotifikacije.getText().toString();
            }
        });
        myDatabase = MyDatabase.getInstance(getApplicationContext());
        opisTekst = findViewById(R.id.opisTekst);
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
            dodaj(this);
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

    private void dodaj(Context context) {
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

            dao = MyDatabase.getInstance(context).getDAO();

            Alarm alarm = new Alarm();
            String datum = btnDatum.getText().toString().trim();
            String vrijeme = btnVrijeme.getText().toString().trim();
            String opis = opisTekst.getText().toString();
            Integer tip;

            if (nazivNotifikacije.equals("Alarm"))
                tip = 1;
            else if (nazivNotifikacije.equals("Notifikacija"))
                tip = 2;
            else
                tip = 3;

            alarm.setDatum(datum);
            alarm.setVrijeme(vrijeme);
            alarm.setOpis(opis);
            alarm.setTipNotifikacijeId(tip);
            alarm.setAlarmId((int)dao.insertAlarmi(alarm)[0]);

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

            if (tip == 1)
                postaviAlarm(datum, vrijeme, opis, alarm.getAlarmId());
            else if (tip == 2)
                NotificationCaller(opis,datum,vrijeme);
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

    private void postaviAlarm(String datum, String vrijeme, String opis, Integer alarmId){
        final Intent intent = new Intent(this, Alarmio.class);
        ServiceCaller(intent, datum, vrijeme, opis, alarmId);

    }

    private void NotificationCaller(String opis, String datum, String vrijeme) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent1 = new Intent(getApplicationContext(), AndroidNotifikacijaBrodcastReceiver.class);
        intent1.putExtra("alarmOpis", opis);
        intent1.putExtra("alarmDatum", datum);
        intent1.putExtra("alarmVrijeme", vrijeme);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent1, PendingIntent.FLAG_ONE_SHOT);
        String dateandtime = datum + " " + vrijemeObavijest;
        DateFormat formatter = new SimpleDateFormat("d/M/yyyy hh:mm");
        try {
            Date date1 = formatter.parse(dateandtime);
            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finish();
    }

    private void ServiceCaller(Intent intent, String datum, String vrijeme, String opis, Integer alarmId) {
        stopService(intent);

        String[] rastavVrijeme = vrijeme.split(":");
        intent.putExtra("alarmOpis", opis);
        intent.putExtra("alarmDatum", datum);
        intent.putExtra("alarmSati", Integer.parseInt(rastavVrijeme[0]));
        intent.putExtra("alarmMinute", Integer.parseInt(rastavVrijeme[1]));
        intent.putExtra("alarmId", alarmId);

        ponavljajuciDani.clear();

        startService(intent);
        Intent popis = new Intent(this, MainActivityFramework.class);
        popis.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(popis);
    }
}