package com.example.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.time.LocalTime;

@Entity(tableName = "ponavljajuciAlarmi")
public class PonavljajuciAlarm {
    @PrimaryKey(autoGenerate = true)
    int ponavljajuciAlarmId;

    @ForeignKey(entity = Alarm.class, parentColumns = "alarmId", childColumns = "alarmId")
    @ColumnInfo(index = true)
    int alarmId;

    public int getPonavljajuciAlarmId() {
        return ponavljajuciAlarmId;
    }

    public void setPonavljajuciAlarmId(int ponavljajuciAlarmId) {
        this.ponavljajuciAlarmId = ponavljajuciAlarmId;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }
}
