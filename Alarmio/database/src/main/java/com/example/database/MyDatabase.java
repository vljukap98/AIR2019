package com.example.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.database.entities.AndroidAktivnost;
import com.example.database.entities.AndroidNotifikacija;
import com.example.database.entities.Alarm;
import com.example.database.entities.Dani;
import com.example.database.entities.Mail;
import com.example.database.entities.PonavljaSeDanom;
import com.example.database.entities.PonavljajuciAlarm;

@Database(version = 1,
        entities = {Alarm.class, PonavljajuciAlarm.class, Mail.class, AndroidAktivnost.class, AndroidNotifikacija.class, Dani.class, PonavljaSeDanom.class},
        exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public static final String NAME = "main";
    public static final int VERSION = 1;

    private static com.example.database.MyDatabase INSTANCE = null;

    public synchronized static com.example.database.MyDatabase getInstance(final Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    com.example.database.MyDatabase.class,
                    com.example.database.MyDatabase.NAME
            ).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public abstract DAO getDAO();
}