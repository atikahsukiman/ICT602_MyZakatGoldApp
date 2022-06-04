package com.myzakatgoldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class AboutApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutapp);

        TextView text = findViewById(R.id.Link);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }

}