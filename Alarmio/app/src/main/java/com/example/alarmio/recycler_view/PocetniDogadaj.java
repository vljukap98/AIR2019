package com.example.alarmio.recycler_view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarmio.R;
import com.example.alarmio.alarm_funkcije.AzurirajAlarm;
import com.example.database.DAO;
import com.example.database.MyDatabase;
import com.example.database.entities.Alarm;
import com.example.database.entities.Dani;
import com.example.database.entities.PonavljaSeDanom;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class PocetniDogadaj extends RecyclerView.Adapter<PocetniDogadaj.ViewHolder> {
    Context context;
    List<Alarm> alarmList;
    List<Dani> daniList;
    List<PonavljaSeDanom> ponavljaSeDanomList;
    private static DAO dao;
    PocetniDogadaj pocetniDogadaj;

    public PocetniDogadaj(Context context, List<Alarm> alarmList, List<Dani> daniList, List<PonavljaSeDanom> ponavljaSeDanomList) {
        this.context = context;
        this.alarmList = alarmList;
        this.daniList = daniList;
        this.ponavljaSeDanomList = ponavljaSeDanomList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.redovi, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.vrijemeIDatumText.setText(alarmList.get(position).getDatum() + " " + alarmList.get(position).getVrijeme());
        holder.opisText.setText(alarmList.get(position).getOpis());
        dao = MyDatabase.getInstance(context).getDAO();
        String ponoviDani = "";
        List<String> ponavljanjeDani = dao.loadAllPonavljanjeByAlarm(alarmList.get(position).getAlarmId());
        Log.d("listaUpita", ponavljanjeDani.toString());
        for(String dan : ponavljanjeDani){
            ponoviDani += dan + "\n";
            Log.d("ponoviDani", ponoviDani);
        }
        holder.dani.setText(ponoviDani);
        holder.brisanjeAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alarm alarm = new Alarm();
                int id = alarmList.get(position).getAlarmId();
                dao = MyDatabase.getInstance(context).getDAO();
                for (Alarm a : alarmList) {
                    if (a.getAlarmId() == id){
                        alarm = a;
                    }
                }
                dao.deleteAlarmi(alarm);
                dao.deleteAllPonavljanjeByAlarm(alarm.getAlarmId());
                /*Treba doraditi*/
                alarmList.remove(alarmList.get(position));
                holder.toplayout.removeViewAt(position);

                /*pocetniDogadaj = new PocetniDogadaj(context,alarmList);
                pocetniDogadaj.notifyItemRemoved(position);
                pocetniDogadaj.notifyItemRangeChanged(position,alarmList.size());*/
            }
        });
        holder.azuriranjeAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alarm alarm = new Alarm();
                int id = alarmList.get(position).getAlarmId();
                dao = MyDatabase.getInstance(context).getDAO();
                for (Alarm a : alarmList) {
                    if (a.getAlarmId() == id){
                        alarm = a;
                    }
                }
                Intent intent = new Intent(context, AzurirajAlarm.class);
                intent.putExtra("Alarm", alarm);
                Log.d("Slanje objekta",alarm.toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView eventText, vrijemeIDatumText, opisText, dani;
        Button brisanjeAlarma, azuriranjeAlarma;
        Switch dodajAlarm;
        private LinearLayout toplayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventText = itemView.findViewById(R.id.dogadaj);
            vrijemeIDatumText = itemView.findViewById(R.id.vrijeme_datum);
            toplayout = itemView.findViewById(R.id.toplayout);
            opisText = itemView.findViewById(R.id.opis);
            brisanjeAlarma = itemView.findViewById(R.id.btnObrisi);
            azuriranjeAlarma = itemView.findViewById(R.id.btnIzmjeni);
            dodajAlarm = itemView.findViewById(R.id.stanje);
            dani = itemView.findViewById(R.id.dani);
        }
    }
}
