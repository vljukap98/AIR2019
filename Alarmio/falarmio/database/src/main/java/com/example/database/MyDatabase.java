package com.example.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.database.entities.Alarm;
import com.example.database.entities.Dani;
import com.example.database.entities.PonavljaSeDanom;
import com.example.database.entities.TipNotifikacije;

import java.io.Serializable;

@Database(version = 1,
        entities = {Alarm.class, Dani.class, PonavljaSeDanom.class, TipNotifikacije.class},
        exportSchema = false)
public abstract class MyDatabase extends RoomDatabase implements Serializable {
    public static final String NAME = "main";
    public static final int VERSION = 1;

    private static MyDatabase INSTANCE = null;

    public synchronized static MyDatabase getInstance(final Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MyDatabase.class,
                    MyDatabase.NAME
            ).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public abstract DAO getDAO();
}