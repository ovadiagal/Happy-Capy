package com.example.happycapy.Logic;

public class PointsLogic {

    private int[] tileList;
    private static int points = 0;

    private int tileListIndexMin;
    private int tileListIndex;
    public PointsLogic(int[] tileList) {
        this.tileList = tileList;
        tileListIndex = tileList.length - 1;
        tileListIndexMin = tileList.length - 1;
    }

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int newPoints) {
        points = newPoints;
    }
    public void moveUp() {
        if (tileListIndex > 0) { //checks that the index is in bounds
            tileListIndex -= 1; //moves index closer to goal tile
        }
    }

    public void moveDown() {
        if (tileListIndex < tileList.length - 1) { //checks that the index is in bounds
            tileListIndex += 1; //moves index further than goal tile to keep track
        }
    }

    public int update() {
        // Update points
        if (tileListIndex < tileListIndexMin) { //furthest the player has gone
            tileListIndexMin = tileListIndex;
            if (tileListIndex != tileList.length - 1) {
                //using tile index as a multiplier for how many points you get based on type
                points += 10 * tileList[tileListIndex];
                // + getPlayerRow(), to get vehicle speed in row
            }
        }
        return points;
    }
    public void resetPoints() {
        tileListIndex = tileList.length - 1;
        tileListIndexMin = tileList.length - 1;
        points = Math.max(points - 100, 0);

    }

    public void melonTouched() {
        points += 100;
    }



}
