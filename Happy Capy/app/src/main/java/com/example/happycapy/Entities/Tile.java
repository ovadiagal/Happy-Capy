package com.example.happycapy.Entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.happycapy.GameScreen;
import com.example.happycapy.R;

public enum Tile {
    WATER(R.drawable.water_tile2),
    ROAD(R.drawable.road_tile),
    SAFE(R.drawable.grass_tile2),
    GOAL(R.drawable.goal_tile);


    private Bitmap tileSheet;
    private BitmapFactory.Options options = new BitmapFactory.Options();

    Tile(int resID) {
        options.inScaled = false;
        tileSheet = BitmapFactory.decodeResource(GameScreen.getGameContext().getResources(),
                resID, options);

    }

    private Bitmap getScaledBitmap(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 3),
                (int) (bitmap.getHeight() * 3), false);
    }

    public Bitmap getTile() {
        return getScaledBitmap(tileSheet);
    }
}
