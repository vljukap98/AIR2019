package com.example.alarmio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.entities.Alarm;

import java.util.List;

public class PocetniDogadaj extends RecyclerView.Adapter<PocetniDogadaj.ViewHolder> {
    Context context;
    List<Alarm> alarmList;

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
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView eventText, vrijemeIDatumText;
        private LinearLayout toplayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vrijemeIDatumText = (TextView) itemView.findViewById(R.id.vrijeme_datum);
            toplayout = (LinearLayout) itemView.findViewById(R.id.toplayout);
        }
    }
}
