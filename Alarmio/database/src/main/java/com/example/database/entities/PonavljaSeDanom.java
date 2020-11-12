package com.example.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "ponavljaSeDanom", primaryKeys = {"alarmId","danId"})
public class PonavljaSeDanom {
    @ForeignKey(entity = Alarm.class, parentColumns = "alarmId", childColumns = "alarmId")
    @ColumnInfo(index = true)
    int alarmId;

    @ForeignKey(entity = Dani.class, parentColumns = "danId", childColumns = "danId")
    @ColumnInfo(index = true)
    int danId;

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public int getDanId() {
        return danId;
    }

    public void setDanId(int danId) {
        this.danId = danId;
    }
}
