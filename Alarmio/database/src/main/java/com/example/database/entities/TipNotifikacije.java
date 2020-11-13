package com.example.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tipNotifikacija")
public class TipNotifikacije {
    @PrimaryKey(autoGenerate = true)
    int tipNotifikacijeId;

    String naziv;

    public int getTipNotifikacijeId() {
        return tipNotifikacijeId;
    }

    public void setTipNotifikacijeId(int tipNotifikacijeId) {
        this.tipNotifikacijeId = tipNotifikacijeId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
