package com.example.happycapy.Entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.happycapy.GameScreen;
import com.example.happycapy.R;

public enum Capybara {
    BROWNCAPY(R.drawable.sprite_sheet_1),
    BlLUECAPY(R.drawable.sprite_sheet_2),
    PINKCAPY(R.drawable.sprite_sheet_3),
    GREENCAPY(R.drawable.sprite_sheet_4);

    private Bitmap spriteSheet;
    private BitmapFactory.Options options = new BitmapFactory.Options();
    private Bitmap[][] sprites = new Bitmap[1][3];

    Capybara(int resID) {
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(GameScreen.getGameContext().getResources(),
                resID, options);
        for (int j = 0; j < sprites.length; j++) {
            for (int i = 0; i < sprites[j].length; i++) {
                sprites[j][i] = getScaledBitmap(Bitmap.createBitmap(spriteSheet,
                        20 * i, 20 * j, 20, 20));
                //change these depending on the size of bitmap/sprites
                // also scales the bitmap
            }
        }
    }

    private Bitmap getScaledBitmap(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(bitmap,
                bitmap.getWidth() * 6, bitmap.getHeight() * 6, false);
    }

    public Bitmap getSprite(int yPos, int xPos) {
        return sprites[yPos][xPos];
    }

    public Bitmap getSpriteSheet() {
        return spriteSheet;
    }


}
