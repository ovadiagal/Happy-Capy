package com.example.happycapy.Logic;

import java.util.ArrayList;
import java.util.Random;

public class MelonManager {

    private int row;
    private int col;
    private ArrayList<Integer> possible = new ArrayList();
    private Random rand = new Random();
    private boolean onScreen = true;


    public MelonManager(int[] tileList) {
        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i] == 3) {
                possible.add(i);
            }
        }

        if (possible.size() != 0) {
            row = possible.get(rand.nextInt(possible.size()));
            // pick a random position for the watermelon.
            col = rand.nextInt(9);
        } else {
            row = -1;
            col = -1;
        }
    }


    public boolean checkForMelonCollision(int spriteX, int spriteY) {
        if ((((spriteX / 120) == col) && (spriteY / 120 == row)) && onScreen) {
            return true;
        }
        return false;
    }

    public void remove() {
        onScreen = false;
        row = -1;
        col = -1;
    }

    public boolean getOnScreen() {
        return onScreen;
    }

    public int getX() {
        return col * 120;
    }

    public int getY() {
        return row * 120;
    }
}
