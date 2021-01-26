package com.example.falarmio.recycler_view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.falarmio.AlarmioLoadData;
import com.example.falarmio.R;
import com.example.database.DAO;
import com.example.database.MyDatabase;
import com.example.database.entities.Alarm;
import com.example.database.entities.Dani;
import com.example.database.entities.PonavljaSeDanom;
import com.example.falarmio.alarm_funkcije.AzurirajAlarm;
import com.example.falarmio.pokretanje_alarma.Alarmio;

import java.util.List;


public class PocetniDogadaj extends RecyclerView.Adapter<PocetniDogadaj.ViewHolder> {
    Context context;
    List<Alarm> alarmList;
    List<Dani> daniList;
    List<PonavljaSeDanom> ponavljaSeDanomList;
    private static DAO dao;

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
        for(String dan : ponavljanjeDani){
            ponoviDani += dan + "\n";
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
                alarmList.remove(alarmList.get(position));
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
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.dodajAlarm.setChecked(true);
        holder.dodajAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Alarm alarm = new Alarm();
                int id = alarmList.get(position).getAlarmId();
                dao = MyDatabase.getInstance(context).getDAO();
                for (Alarm a : alarmList) {
                    if (a.getAlarmId() == id){
                        alarm = a;
                    }
                }
                final Intent intent = new Intent(context, Alarmio.class);
                if (isChecked)
                {
                    PokreniZvoni(intent, alarm);
                }
                else
                {
                    ZaustaviZvoni(intent);
                }
            }

            private void PokreniZvoni(Intent intent, Alarm alarm) {
                String[] rastavVrijeme = alarm.getVrijeme().split(":");

                intent.putExtra("alarmOpis", alarm.getOpis());
                intent.putExtra("alarmDatum", alarm.getDatum());
                intent.putExtra("alarmSati", Integer.parseInt(rastavVrijeme[0]));
                intent.putExtra("alarmMinute", Integer.parseInt(rastavVrijeme[1]));
                intent.putExtra("alarmId", alarm.getAlarmId());

                context.startService(intent);

            }

            private void ZaustaviZvoni(Intent intent) {
                context.stopService(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView vrijemeIDatumText, opisText, dani;
        Button brisanjeAlarma, azuriranjeAlarma;
        Switch dodajAlarm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vrijemeIDatumText = itemView.findViewById(R.id.vrijeme_datum);
            opisText = itemView.findViewById(R.id.opis);
            brisanjeAlarma = itemView.findViewById(R.id.btnObrisi);
            azuriranjeAlarma = itemView.findViewById(R.id.btnIzmjeni);
            dodajAlarm = itemView.findViewById(R.id.stanje);
            dani = itemView.findViewById(R.id.dani);
        }
    }
}
