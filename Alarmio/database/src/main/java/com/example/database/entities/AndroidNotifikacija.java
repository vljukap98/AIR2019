package com.example.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "notifikacije")
public class AndroidNotifikacija {
    @PrimaryKey(autoGenerate = true)
    int notifikacijaId;

    String naslov;

    @ForeignKey(entity = Alarm.class, parentColumns = "alarmId", childColumns = "alarmId")
    @ColumnInfo(index = true)
    int alarmId;

    public int getNotifikacijaId() {
        return notifikacijaId;
    }

    public void setNotifikacijaId(int notifikacijaId) {
        this.notifikacijaId = notifikacijaId;
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
