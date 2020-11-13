package com.example.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;



import java.sql.Time;
import java.util.Date;
import java.time.LocalTime;

@Entity(tableName = "alarmi")
public class Alarm {
    @PrimaryKey(autoGenerate = true)
    int alarmId;

    String naziv;
    String opis;
    String datum;
    String vrijeme;

    @ForeignKey(entity = TipNotifikacije.class, parentColumns = "tipNotifikacijeId", childColumns = "tipNotifikacijeId")
    @ColumnInfo(index = true)
    int tipNotifikacijeId;

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(String vrijeme) {
        this.vrijeme = vrijeme;
    }

    public int getTipNotifikacijeId() {
        return tipNotifikacijeId;
    }

    public void setTipNotifikacijeId(int tipNotifikacijeId) {
        this.tipNotifikacijeId = tipNotifikacijeId;
    }
}
