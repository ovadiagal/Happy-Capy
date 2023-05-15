package com.example.happycapy.Entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.happycapy.GameScreen;
import com.example.happycapy.R;

public enum Log {

    LOG(R.drawable.log_sprite),
    MAGIC_LOG(R.drawable.magic_log);

    private Bitmap spriteSheet;
    private BitmapFactory.Options options = new BitmapFactory.Options();

    Log(int resID) {
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(GameScreen.getGameContext().getResources(),
                resID, options);
    }

    private Bitmap getScaledBitmap(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 6),
                (int) (bitmap.getHeight() * 6), false);
    }

    public Bitmap getSprite() {
        return getScaledBitmap(spriteSheet);
    }

}
