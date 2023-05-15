package com.example.happycapy.Entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.happycapy.GameScreen;
import com.example.happycapy.R;

public enum Enemy {

    BADGER(R.drawable.badger),
    SNAKE(R.drawable.snake),
    TIGER(R.drawable.tiger);

    private Bitmap spriteSheet;
    private BitmapFactory.Options options = new BitmapFactory.Options();

    private Bitmap[] sprites = new Bitmap[2];

    Enemy(int resID) {
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(GameScreen.getGameContext().getResources(),
                resID, options);

        int width = 0;
        if (resID == R.drawable.tiger) {
            width = 25;
        } else if (resID == R.drawable.snake) {
            width = 38;
        } else if (resID == R.drawable.badger) {
            width = 12;
        }
        for (int i = 0; i < 2; i++) {
            sprites[i] = getScaledBitmap(Bitmap.createBitmap(spriteSheet,
                    width * i, 0, width, 20));
            //change these depending on the size of bitmap/sprites
            // also scales the bitmap
        }
    }

    private Bitmap getScaledBitmap(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(bitmap,
                bitmap.getWidth() * 6, bitmap.getHeight() * 6, false);
    }

    public Bitmap getSprite(int direction) {
        return sprites[(direction == 1) ? 1 : 0];
    }




}
