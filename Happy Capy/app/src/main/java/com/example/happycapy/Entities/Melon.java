package com.example.happycapy.Entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.happycapy.GameScreen;
import com.example.happycapy.R;

public enum Melon {

    MELON(R.drawable.watermelon);
    private Bitmap spriteSheet;
    private BitmapFactory.Options options = new BitmapFactory.Options();

    Melon(int resID) {
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(GameScreen.getGameContext().getResources(),
                resID, options);
    }

    private Bitmap getScaledBitmap(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 3),
                (int) (bitmap.getHeight() * 3), false);
    }

    public Bitmap getSprite() {
        return getScaledBitmap(spriteSheet);
    }

}
