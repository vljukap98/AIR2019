package com.example.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.database.entities.Alarm;
import com.example.database.entities.Dani;
import com.example.database.entities.PonavljaSeDanom;
import com.example.database.entities.TipNotifikacije;

import java.util.List;

@Dao
public interface DAO {
    /* Sve insert, update i delete funkcije za svaku tablicu */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertAlarmi(Alarm... alarmi);
    @Update
    public void updateAlarmi(Alarm... alarmi);
    @Delete
    public void deleteAlarmi(Alarm... alarmi);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertDani(Dani... dani);
    @Update
    public void updateDani(Dani... dani);
    @Delete
    public void deleteDani(Dani... dani);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertPonavljaSeDanom(PonavljaSeDanom... ponavljaSeDanom);
    @Update
    public void updatePonavljaSeDanom(PonavljaSeDanom... ponavljaSeDanom);
    @Delete
    public void deletePonavljaSeDanom(PonavljaSeDanom... ponavljaSeDanom);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertTipNotifikacija(TipNotifikacije... tipNotifikacija);
    @Update
    public void updateTipNotifikacija(TipNotifikacije... tipNotifikacija);
    @Delete
    public void deleteTipNotifikacija(TipNotifikacije... tipNotifikacija);


    /* Sve funkcije za dohvaćanje podataka iz tablica*/
    @Query("SELECT * FROM dani")
    List<Dani> loadAllDani();

    @Query("SELECT * FROM alarmi")
    List<Alarm> loadAllAlarmi();

    @Query("SELECT * FROM ponavljaSeDanom")
    List<PonavljaSeDanom> loadAllPonavljaSeDanom();

    @Query("SELECT * FROM tipNotifikacija")
    List<TipNotifikacije> loadAllTipNotifikacija();

    @Query("SELECT  DISTINCT d.naziv  FROM dani d, alarmi a, ponavljaSeDanom pd WHERE pd.alarmId = :alarmId AND d.danId = pd.danId")
    List<String> loadAllPonavljanjeByAlarm(int alarmId);

    @Query("SELECT danId, naziv  FROM dani WHERE naziv = :danNaziv")
    List<Dani> loadAllDanByNaziv(String danNaziv);

    @Query("SELECT count(*) FROM dani")
    Integer daniTableSize();

    @Query("DELETE FROM ponavljaSeDanom WHERE alarmId = :alarmId")
    void deleteAllPonavljanjeByAlarm(int alarmId);

    @Query("SELECT count(*) FROM tipNotifikacija")
    Integer tipTableSize();
}