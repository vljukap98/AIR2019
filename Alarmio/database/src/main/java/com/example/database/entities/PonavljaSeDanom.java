package com.example.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "ponavljaSeDanom", primaryKeys = {"ponavljajuciAlarmId","danId"})
public class PonavljaSeDanom {
    @ForeignKey(entity = PonavljajuciAlarm.class, parentColumns = "ponavljajuciAlarmId", childColumns = "ponavljajuciAlarmId")
    @ColumnInfo(index = true)
    int ponavljajuciAlarmId;

    @ForeignKey(entity = Dani.class, parentColumns = "danId", childColumns = "danId")
    @ColumnInfo(index = true)
    int danId;

    public int getPonavljajuciAlarmId() {
        return ponavljajuciAlarmId;
    }

    public void setPonavljajuciAlarmId(int ponavljajuciAlarmId) {
        this.ponavljajuciAlarmId = ponavljajuciAlarmId;
    }

    public int getDanId() {
        return danId;
    }

    public void setDanId(int danId) {
        this.danId = danId;
    }
}
