package com.example.happycapy.Logic;

import android.graphics.Bitmap;

import com.example.happycapy.Entities.Log;

import java.util.ArrayList;
import java.util.Random;

public class LogLaneLogic {

    private ArrayList<Integer> positions = new ArrayList<>();
    private int direction; // 1 is to the right, -1 is to the left
    private int lane;
    private int counter;
    private int type; // 0 is regular, 1 is magic log
    private Random rand = new Random();
    private int speed;


    public LogLaneLogic(int lane) {
        if (lane >= 0) {
            this.direction = (rand.nextInt() % 2 == 0) ? 1 : -1;
            this.type =  (rand.nextInt() % 3 == 0) ? 1 : 0;
            this.lane = lane;

            if (type == 1) {
                this.speed = 5 + rand.nextInt(5);
            } else {
                this.speed = 2 + rand.nextInt(5);
            }
            positions.add(rand.nextInt(200));
            positions.add(600 + rand.nextInt(500));
        }
    }


    public void update() {
        // update all the positions
        synchronized (positions) {
            for (int i = 0; i < positions.size(); i++) {
                positions.set(i, positions.get(i) + (speed * direction));
                if ((positions.get(i) > 1300) || (positions.get(i) < -400)) {
                    positions.remove(i);
                    if (direction == 1) {
                        positions.add(-400);
                    } else {
                        positions.add(1200);
                    }
                }
            }
        }
    }

    public ArrayList<Integer> getPositions() {
        return (ArrayList<Integer>) positions;
    }

    public Bitmap getSprite() {
        if (this.type == 1) {
            return Log.MAGIC_LOG.getSprite();
        }
        return Log.LOG.getSprite();
    }

    public int getLane() {
        return lane;
    }

    public boolean checkForCollision(int spriteX, int spriteY) {
        for (int i = 0; i < positions.size(); i++) {
            int pos = positions.get(i);
            if (spriteX > pos && spriteX < pos + 120 && spriteY == this.lane * 120) {
                return true;
            }
        }
        return false;
    }

    public int getDirection() {
        return this.direction;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setType(int type) {
        if (type == 1) {
            this.type = type;
        } else if (type == 0) {
            this.type = type;
        }
    }

    public int getType() {
        return this.type;
    }

    public void setSpeed(int speed) {
        if (speed >= 2 && speed < 11) {
            this.speed = speed;
        }
    }

    public void setDirection(int direction) {
        if (direction == -1 || direction == 1) {
            this.direction = direction;
        }
    }



}
