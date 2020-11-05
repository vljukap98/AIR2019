package com.example.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dani")
public class Dani {
    @PrimaryKey(autoGenerate = true)
    int danId;
    String naziv;

    public int getDanId() {
        return danId;
    }

    public void setDanId(int danId) {
        this.danId = danId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
