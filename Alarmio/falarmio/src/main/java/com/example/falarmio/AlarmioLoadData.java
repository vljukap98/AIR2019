package com.example.falarmio;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.database.DAO;
import com.example.database.MyDatabase;
import com.example.database.entities.Alarm;
import com.example.database.entities.Dani;
import com.example.database.entities.PonavljaSeDanom;
import com.example.database.entities.TipNotifikacije;
import com.example.falarmio.recycler_view.PocetniDogadaj;

import java.util.List;

public class AlarmioLoadData {
    PocetniDogadaj pocetniDogadaj;
    MyDatabase myDatabase;

    public void DatabaseInitialize(Context context){
        myDatabase = MyDatabase.getInstance(context);

        DAO dao = myDatabase.getDAO();
        if(dao.daniTableSize() == 0) {
            Dani danPon = new Dani();
            danPon.setNaziv("Ponedjeljak");
            danPon.setDanId((int) dao.insertDani(danPon)[0]);

            Dani danUto = new Dani();
            danUto.setNaziv("Utorak");
            danUto.setDanId((int) dao.insertDani(danUto)[0]);

            Dani danSri = new Dani();
            danSri.setNaziv("Srijeda");
            danSri.setDanId((int) dao.insertDani(danSri)[0]);

            Dani danCet = new Dani();
            danCet.setNaziv("Četvrtak");
            danCet.setDanId((int) dao.insertDani(danCet)[0]);

            Dani danPet = new Dani();
            danPet.setNaziv("Petak");
            danPet.setDanId((int) dao.insertDani(danPet)[0]);

            Dani danSub = new Dani();
            danSub.setNaziv("Subota");
            danSub.setDanId((int) dao.insertDani(danSub)[0]);

            Dani danNed = new Dani();
            danNed.setNaziv("Nedjelja");
            danNed.setDanId((int) dao.insertDani(danNed)[0]);
        }

        if(dao.tipTableSize() == 0) {
            TipNotifikacije notifikacijaActivity = new TipNotifikacije();
            notifikacijaActivity.setNaziv("Android activity");
            notifikacijaActivity.setTipNotifikacijeId((int)dao.insertTipNotifikacija(notifikacijaActivity)[0]);

            TipNotifikacije notifikacijaAndroid = new TipNotifikacije();
            notifikacijaAndroid.setNaziv("Android notifikacija");
            notifikacijaAndroid.setTipNotifikacijeId((int)dao.insertTipNotifikacija(notifikacijaAndroid)[0]);

            TipNotifikacije notifikacijaMail = new TipNotifikacije();
            notifikacijaMail.setNaziv("E-mail notifikacija");
            notifikacijaMail.setTipNotifikacijeId((int)dao.insertTipNotifikacija(notifikacijaMail)[0]);
        }
    }
    public void postaviDogadaj(RecyclerView rv, Context context){
        List<Alarm> alarmList = myDatabase.getDAO().loadAllAlarmi();
        List<Dani> daniList = myDatabase.getDAO().loadAllDani();
        List<PonavljaSeDanom> ponavljaSeDanomList = myDatabase.getDAO().loadAllPonavljaSeDanom();
        pocetniDogadaj = new PocetniDogadaj(context,alarmList, daniList, ponavljaSeDanomList);
        rv.setAdapter(pocetniDogadaj);
    }
}
