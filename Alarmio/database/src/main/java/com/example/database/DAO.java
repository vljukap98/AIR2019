package com.example.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.database.entities.Alarm;
import com.example.database.entities.AndroidAktivnost;
import com.example.database.entities.AndroidNotifikacija;
import com.example.database.entities.Dani;
import com.example.database.entities.Mail;
import com.example.database.entities.PonavljaSeDanom;
import com.example.database.entities.PonavljajuciAlarm;

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
    public long[] insertPonavljajuciAlarmi(PonavljajuciAlarm... ponavljajuciAlarmi);
    @Update
    public void updatePonavljajuciAlarmi(PonavljajuciAlarm... ponavljajuciAlarmi);
    @Delete
    public void deletePonavljajuciAlarmi(PonavljajuciAlarm... ponavljajuciAlarmi);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertAktivnosti(AndroidAktivnost... aktivnosti);
    @Update
    public void updateAktivnosti(AndroidAktivnost... aktivnosti);
    @Delete
    public void deleteAktivnosti(AndroidAktivnost... aktivnosti);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertNotifikacije(AndroidNotifikacija... notifikacije);
    @Update
    public void updateNotifikacije(AndroidNotifikacija... notifikacije);
    @Delete
    public void deleteNotifikacije(AndroidNotifikacija... notifikacije);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertDani(Dani... dani);
    @Update
    public void updateDani(Dani... dani);
    @Delete
    public void deleteDani(Dani... dani);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertMail(Mail... mail);
    @Update
    public void updateMail(Mail... mail);
    @Delete
    public void deleteMail(Mail... mail);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertPonavljaSeDanom(PonavljaSeDanom... ponavljaSeDanom);
    @Update
    public void updatePonavljaSeDanom(PonavljaSeDanom... ponavljaSeDanom);
    @Delete
    public void deletePonavljaSeDanom(PonavljaSeDanom... ponavljaSeDanom);

<<<<<<< Updated upstream
    /* Sve funkcije za dohvaćanje podataka iz tablica*/
    @Query("SELECT * FROM ponavljajuciAlarmi")
    public List<PonavljajuciAlarm> loadAllPonavljajuciAlarmi();

    @Query("SELECT * FROM aktivnosti")
    public List<AndroidAktivnost> loadAllAktivnosti();

    @Query("SELECT * FROM notifikacije")
    public List<AndroidNotifikacija> loadAllNotifikacije();

    @Query("SELECT * FROM dani")
    public List<Dani> loadAllDani();

    @Query("SELECT * FROM mail")
    public List<Mail> loadAllMailovi();
=======
    @Query("SELECT * FROM alarmi")
    public List<Alarm> loadAllAlarmi();

>>>>>>> Stashed changes
}