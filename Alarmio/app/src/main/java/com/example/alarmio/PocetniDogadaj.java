package com.example.alarmio;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.DAO;
import com.example.database.MyDatabase;
import com.example.database.entities.Alarm;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class PocetniDogadaj extends RecyclerView.Adapter<PocetniDogadaj.ViewHolder> {
    Context context;
    List<Alarm> alarmList;
    private static DAO dao;
    PocetniDogadaj pocetniDogadaj;

    public PocetniDogadaj(Context context, List<Alarm> alarmList) {
        this.context = context;
        this.alarmList = alarmList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.redovi, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.vrijemeIDatumText.setText(alarmList.get(position).getDatum() + " " + alarmList.get(position).getVrijeme());
        holder.opisText.setText(alarmList.get(position).getOpis());
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
                /*Treba doraditi*/
                alarmList.remove(alarmList.get(position));
                holder.toplayout.removeViewAt(position);

                /*pocetniDogadaj = new PocetniDogadaj(context,alarmList);
                pocetniDogadaj.notifyItemRemoved(position);
                pocetniDogadaj.notifyItemRangeChanged(position,alarmList.size());*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView eventText, vrijemeIDatumText, opisText, proba;
        Button brisanjeAlarma;
        private LinearLayout toplayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventText = (TextView) itemView.findViewById(R.id.dogadaj);
            vrijemeIDatumText = (TextView) itemView.findViewById(R.id.vrijeme_datum);
            toplayout = (LinearLayout) itemView.findViewById(R.id.toplayout);
            opisText = (TextView) itemView.findViewById(R.id.opis);
            brisanjeAlarma = (Button) itemView.findViewById(R.id.btnObrisi);
            proba = (TextView) itemView.findViewById(R.id.proba);
        }
    }
}
