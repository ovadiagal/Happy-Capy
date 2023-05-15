package com.example.happycapy.Logic;

import java.util.ArrayList;

public class LogManager {

    private ArrayList<LogLaneLogic> logArray = new ArrayList<LogLaneLogic>();

    public LogManager(int[] tileList) {
        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i] == 2) {
                logArray.add(new LogLaneLogic(i));
            }
        }
    }

    public void update() {
        for (LogLaneLogic each : logArray) {
            synchronized (each) {
                each.update();
            }
        }
    }

    public boolean onLog(int spriteX, int spriteY) {
        for (LogLaneLogic each : logArray) {
            if (each.checkForCollision(spriteX, spriteY)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<LogLaneLogic> getLogArray() {
        return logArray;
    }

    public int updateSprite(int spriteX, int spriteY) {
        for (LogLaneLogic each : logArray) {
            if (each.checkForCollision(spriteX, spriteY)) {
                return spriteX + each.getSpeed() * each.getDirection();
            }
        }
        return spriteX;
    }
}
