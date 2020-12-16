package com.example.falarmio.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.falarmio.MainActivityFramework;

public class PokretanjeRecivera extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, MainActivityFramework.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
