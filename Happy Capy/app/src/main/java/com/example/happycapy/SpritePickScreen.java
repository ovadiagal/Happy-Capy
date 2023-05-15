package com.example.happycapy;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.happycapy.Logic.SpriteLogic;

public class SpritePickScreen extends AppCompatActivity {

    // This is where the player selects their sprite.
    // While SpritePickScreen is the UI end of this,
    // SpriteLogic most all the logical components.
    // This screen's purpose is to ultimately build a valid
    // SpriteLogic class and pass it forwards.
    private static EditText playerName; // Player name entry
    private static TextView warning; // Text that shows warning if any field missing

    // Sprite logic class that manages sprite attributes.
    private static SpriteLogic spriteLogic = new SpriteLogic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sprite_pick_screen);
        playerName = (EditText) findViewById(R.id.playerName);
        Intent intent = new Intent(SpritePickScreen.this, BackgroundSoundService.class);
        startService(intent);
    }

    public static SpriteLogic getSpriteLogic() {
        return spriteLogic;
    }

    // Sets the String field in SpriteLogic
    public void setSprite(View v) {
        ImageButton ib = (ImageButton) v;

        ColorMatrix colorMatrix = new ColorMatrix();
        ColorMatrixColorFilter saturationFilter = new ColorMatrixColorFilter(colorMatrix);
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter noSaturationFilter = new ColorMatrixColorFilter(colorMatrix);


        if (ib.getId() == R.id.brown_capybara) {
            ((ImageButton) findViewById(R.id.brown_capybara)).setColorFilter(saturationFilter);
            ((ImageButton) findViewById(R.id.pink_capybara)).setColorFilter(noSaturationFilter);
            ((ImageButton) findViewById(R.id.green_capybara)).setColorFilter(noSaturationFilter);
            spriteLogic.setType("brown");
        } else if (ib.getId() == R.id.pink_capybara) {
            ((ImageButton) findViewById(R.id.brown_capybara)).setColorFilter(noSaturationFilter);
            ((ImageButton) findViewById(R.id.pink_capybara)).setColorFilter(saturationFilter);
            ((ImageButton) findViewById(R.id.green_capybara)).setColorFilter(noSaturationFilter);
            spriteLogic.setType("pink");
        } else if (ib.getId() == R.id.green_capybara) {
            ((ImageButton) findViewById(R.id.brown_capybara)).setColorFilter(noSaturationFilter);
            ((ImageButton) findViewById(R.id.pink_capybara)).setColorFilter(noSaturationFilter);
            ((ImageButton) findViewById(R.id.green_capybara)).setColorFilter(saturationFilter);
            spriteLogic.setType("green");
        }
    }

    public static void setLives(View v) {
        if (v.getId() == R.id.easyButton) {
            spriteLogic.setLives(5);
        } else if (v.getId() == R.id.mediumButton) {
            spriteLogic.setLives(3);
        } else if (v.getId() == R.id.hardButton) {
            spriteLogic.setLives(1);
        }
    }

    public void startGameRegular(View v) {
        String name = String.valueOf(playerName.getText());
        if (name != null && !name.isEmpty() && !name.isBlank()
                && spriteLogic.getStringType() != null && spriteLogic.getLives() != 0) {
            spriteLogic.setName(name);
            startActivity(new Intent(SpritePickScreen.this, GameScreen.class));
        } else {
            warning = findViewById(R.id.warning);
            warning.setTextColor(Color.parseColor("#EE5D4B"));
        }
    }

    public void startGameEndless(View v) {
        String name = String.valueOf(playerName.getText());
        spriteLogic.setEndless(true);
        if (name != null && !name.isEmpty() && !name.isBlank()
                && spriteLogic.getStringType() != null && spriteLogic.getLives() != 0) {
            spriteLogic.setName(name);
            startActivity(new Intent(SpritePickScreen.this, GameScreen.class));
        } else {
            warning = findViewById(R.id.warning);
            warning.setTextColor(Color.parseColor("#EE5D4B"));
        }
    }
}