package com.example.happycapy.Logic;

import java.util.ArrayList;

public class EnemyManager {

    private ArrayList<EnemyLaneLogic> enemyArray = new ArrayList<EnemyLaneLogic>();

    public EnemyManager(int[] tileList) {
        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i] == 3) {
                enemyArray.add(new EnemyLaneLogic(i));
            }
        }
    }


    public void update() {
        for (EnemyLaneLogic each : enemyArray) {
            each.update();
        }
    }

    public boolean collided(int playerX, int playerY) {
        for (EnemyLaneLogic each : enemyArray) {
            if (each.checkForCollision(playerX, playerY)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<EnemyLaneLogic> getEnemyArray() {
        return enemyArray;
    }

}
