package com.example.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "aktivnosti")
public class AndroidAktivnost {
    @PrimaryKey(autoGenerate = true)
    int aktivnostId;

    String naslov;

    @ForeignKey(entity = Alarm.class, parentColumns = "alarmId", childColumns = "alarmId")
    @ColumnInfo(index = true)
    int alarmId;

    public int getAktivnostId() {
        return aktivnostId;
    }

    public void setAktivnostId(int aktivnostId) {
        this.aktivnostId = aktivnostId;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }
}
