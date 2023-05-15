package com.example.happycapy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.happycapy.Logic.PointsLogic;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_screen);

        // This sets the text to display the score the player got.
        TextView score = (TextView) findViewById(R.id.textView6);
        score.setText(String.valueOf(PointsLogic.getPoints()));
        PointsLogic.setPoints(0);



        // Exit Button Handlers
        Button exitBtn = (Button) findViewById(R.id.exitButton);
        exitBtn.setOnClickListener(v -> {
            Intent intent = new Intent(GameOver.this, StartingScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        });

        // Replay Button Handler
        Button restartBtn = (Button) findViewById(R.id.restartButton);
        restartBtn.setOnClickListener(v -> {
            Intent intent = new Intent(GameOver.this, StartingScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("CONFIGURE", true);
            startActivity(intent);
        });
    }
}