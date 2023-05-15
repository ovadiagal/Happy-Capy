package com.example.happycapy.Logic;

import android.graphics.Bitmap;

import com.example.happycapy.Entities.Enemy;

import java.util.ArrayList;
import java.util.Random;

public class EnemyLaneLogic {

    private ArrayList<Integer> positions = new ArrayList<>();
    private int direction; // 1 is to the right, -1 is to the left
    private int lane;
    private int speed;
    private Random rand = new Random();
    private int turnBack = 0;
    private int originalDir;
    private String[] availableTypes = {"tiger", "snake", "badger"};
    private String stringType;
    private int spriteSize;

    public EnemyLaneLogic(int lane) {
        this(null, 0, lane);
    }

    public EnemyLaneLogic(String stringType, int direction, int lane) {
        if (stringType == null) {
            this.stringType = availableTypes[rand.nextInt(3)];
        } else {
            this.stringType = stringType;
        }
        this.direction = (rand.nextInt() % 2 == 0) ? 1 : -1;
        this.lane = lane;
        positions.add(rand.nextInt(300));
        positions.add(500 + rand.nextInt(500));
        originalDir = direction;
        if (this.stringType.equals("badger")) {
            speed = 7;
            spriteSize = 72;
        } else if (this.stringType.equals("tiger")) {
            speed = 4;
            spriteSize = 150;
        } else if (this.stringType.equals("snake")) {
            speed = 2;
            spriteSize = 228;
        }
    }

    public void update() {
        // update all the positions
        synchronized (positions) {
            for (int i = 0; i < positions.size(); i++) {
                positions.set(i, positions.get(i) + (speed * direction));
                if ((positions.get(i) > 1300) || (positions.get(i) < -200)) {
                    positions.remove(i);
                    if (direction == 1) {
                        positions.add(-200);
                    } else {
                        positions.add(1200);
                    }
                }
            }
        }
    }

    public boolean checkForCollision(int playerX, int playerY) {
        if (playerY == lane * 120) {
            for (int i = 0; i < positions.size(); i++) {
                if ((playerX < positions.get(i) + spriteSize - 25)
                        && (playerX > positions.get(i) + 25)
                        || ((playerX + 120 < positions.get(i) + spriteSize - 25)
                        && (playerX + 120 > positions.get(i) + 25))) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Integer> getPositions() {
        return (ArrayList<Integer>) positions;
    }

    private void printList() {
        for (int each : positions) {
            System.out.println(each);
        }
    }

    public int getLane() {
        return lane;
    }

    public Enemy getType() {
        switch (stringType) {
        case "badger":
            return Enemy.BADGER;
        case "tiger":
            return Enemy.TIGER;
        case "snake":
            return Enemy.SNAKE;
        default:
            return null;
        }
    }

    public String getStringType() {
        return stringType;
    }

    public Bitmap getSprite() {
        return this.getType().getSprite(direction);
    }
    public int getDirection() {
        return this.direction;
    }

    public int getSpeed() {
        return this.speed;
    }
    public int getSpriteSize() {
        return this.spriteSize;
    }


}
