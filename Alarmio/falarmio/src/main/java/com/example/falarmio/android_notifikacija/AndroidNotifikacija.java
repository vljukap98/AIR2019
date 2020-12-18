package com.example.falarmio.android_notifikacija;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.falarmio.R;

public class AndroidNotifikacija extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_notifikacija);
        textView = findViewById(R.id.poruka1);
        Bundle bundle = getIntent().getExtras();
        textView.setText(bundle.getString("poruka"));
    }
}