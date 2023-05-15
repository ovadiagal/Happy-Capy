package com.example.happycapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StartingScreen extends AppCompatActivity {
    @Override

    // StartingScreen class is the entry way to the application.
    // Upon start button being pressed, we transition to the sprite select screen.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_screen);


        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        if (getIntent().getBooleanExtra("CONFIGURE", false)) {
            transition(null);
        }
    }
    public void transition(View v) {
        startActivity(new Intent(this, SpritePickScreen.class));
    }
}