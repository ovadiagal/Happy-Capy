package com.example.happycapy;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.happycapy.Logic.SpriteLogic;

public class GameScreen extends AppCompatActivity {

    // GameScreen is very much a boilerplate class
    // That simply serves to run GamePanel properly.
    // Does not do much other than act as a bridge between
    // GamePanel and the other classes.
    private static SpriteLogic spriteLogic = SpritePickScreen.getSpriteLogic();
    private static Context gameContext;
    private static GamePanel gamePanel;

    public static SpriteLogic getSpriteLogic() {
        return spriteLogic;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameContext = this;
        gamePanel = new GamePanel(this);


        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.soundtrack);
        mediaPlayer.start();

        gamePanel.setFocusable(true);
        gamePanel.setFocusableInTouchMode(true);
        gamePanel.requestFocus();
        gamePanel.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    System.out.println("KEYBAORD PRESS");
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_RIGHT:
                            gamePanel.right();
                            break;
                        case KeyEvent.KEYCODE_DPAD_LEFT:
                            gamePanel.left();
                            break;
                        case KeyEvent.KEYCODE_DPAD_UP:
                            gamePanel.up();
                            break;
                        case KeyEvent.KEYCODE_DPAD_DOWN:
                            gamePanel.down();
                            break;
                        default:
                            break;
                    }
                }
                return true;
            }
        });
        setContentView(gamePanel);
        Window window = this.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static Context getGameContext() {
        return gameContext;
    }

    public static String getPlayerName() {
        String playerText = spriteLogic.getName();
        return playerText;
    }


}
