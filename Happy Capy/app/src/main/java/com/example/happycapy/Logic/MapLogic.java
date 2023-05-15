package com.example.happycapy.Logic;


import java.util.ArrayList;

public class MapLogic {
    private int playerCol;
    private int playerRow;
    private int[][] currentMap = new int[15][9];
    private boolean valid;

    private int spriteX;
    private int spriteY;


    public MapLogic(int[] tileList, int playerCol, int playerRow) {
        // set up currentMap to reflect types of tiles
        try {
            for (int i = 0; i < currentMap.length; i++) {
                for (int j = 0; j < currentMap[i].length; j++) {
                    currentMap[i][j] = tileList[i];
                }
            }
        } catch (Exception e) {
            // we'll save this for later;
            int x = 0;
        }
        this.playerCol = playerCol;
        this.playerRow = playerRow;
        this.spriteX = playerCol * 120;
        this.spriteY = playerRow * 120;

    }

    public MapLogic(int[] tileList) {
        this(tileList, 4, 14);
    }

    public boolean checkValidPosition() {
        return (playerCol >= 0) && (playerCol < 9) && (playerRow >= 0) && (playerRow < 15);
    }


    public void moveRight() {
        if ((this.spriteX + 120) < 1080) {
            this.spriteX += 120;
        }
    }

    public void moveLeft() {
        if ((this.spriteX - 120) >= 0) {
            this.spriteX -= 120;
        }
    }

    public void moveUp() {
        if ((this.spriteY - 120) >= 0) {
            this.spriteY -= 120;
        }
    }

    public void snap(LogManager logManager, String direction) {
        //sprite must snap to either log or tile, we must find the closest
        if (onWater()) {
            int tile = 120 * (Math.round((spriteX + 60) / 120)); //sprite's tile
            int nearestLog = 1000000000;
            //finding nearest tile
            ArrayList<LogLaneLogic> logArray = logManager.getLogArray();
            for (LogLaneLogic lane : logArray) {
                if (lane.getLane() * 120 == spriteY) {
                    for (int i : lane.getPositions()) {
                        if (Math.abs((i - spriteX)) < Math.abs((nearestLog - spriteX))) {
                            nearestLog = i;
                        }
                    }
                }
            }
            int nearestLog2 = nearestLog + 120; // logs take up 2 tiles.
            // pick which side of log to snap to

            //        if (Math.abs(tile - spriteX) < Math.abs(nearestLog - spriteX)
            //        && Math.abs(tile - spriteX) < Math.abs(nearestLog2 - spriteX)) {
            //            // snapping to the nearest tile is easiest,
            //            this.spriteX = tile;
            //} else
            int logSnap;
            if (Math.abs(nearestLog - spriteX) < Math.abs(nearestLog2 - spriteX)) {
                logSnap = nearestLog + 10;
            } else {
                logSnap = nearestLog2 - 10;
            }

            if (Math.abs(logSnap - spriteX) < 80) {
                this.spriteX = logSnap;
            }
            //            if (direction == "vertical") {
            //                if (Math.abs(logSnap - spriteX) < 80) {
            //                    this.spriteX = logSnap;
            //                }
            //            } else {
            //                // If I'm not moving horizontally, I need to snap to
            //                the other part of the log
            //
            //            }
        } else {
            this.spriteX = 120 * (Math.round((spriteX + 60) / 120)); // Snaps to the nearest tile
        }
    }



    //        int tile = 1000000000;
    //        // Now let's find a snap to the nearest log
    //        int nearestLog = 1000000000; // set to an arbitrarily large number.
    //        ArrayList<LogLaneLogic> logArray = logManager.getLogArray();
    //        for (LogLaneLogic lane : logArray) {
    //            if (lane.getLane() * 120 == spriteY) {
    //                // If they're on the same lane, we can begin to look for the nearest log.
    //                for (int i : lane.getPositions()) {
    //                    if (Math.abs((i - spriteX)) < Math.abs((nearestLog - spriteX))) {
    //                        nearestLog = i;
    //                    }
    //                }
    //            }
    //        }
    //        int nearestLog2 = nearestLog + 120; // logs take up 2 tiles.
    //        // Now we need to pick if we should snap to a log (right half)
    //        // to a log (left half), or to a tile!
    //
    ////        if (Math.abs(tile - spriteX) < Math.abs(nearestLog - spriteX)
    // && Math.abs(tile - spriteX) < Math.abs(nearestLog2 - spriteX)) {
    ////            // snapping to the nearest tile is easiest,
    ////            this.spriteX = tile;
    //        //} else
    //            if (Math.abs(nearestLog - spriteX) < Math.abs(tile - spriteX)
    //            && Math.abs(nearestLog - spriteX) < Math.abs(nearestLog2 - spriteX)) {
    //            this.spriteX = nearestLog;
    //        } else {
    //            this.spriteX = nearestLog2;
    //        }

    // Now let's check if there is a log!
    //        if (onWater()) {
    //            // If we're on water, let's find the nearest tile to snap to.
    //
    //        }


    public void moveDown() {
        if ((this.spriteY + 120) < 1800) {
            this.spriteY += 120;
        }
    }

    public boolean onWater() {
        return currentMap[spriteY / 120][0] == 2;
    }

    public int getPlayerCol() {
        return playerCol;
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public void setPlayerPos(int column, int row) {
        this.playerCol = column;
        this.playerRow = row;
        spriteX = playerCol * 120;
        spriteY = playerRow * 120;
    }

    public int[][] getCurrentMap() {
        return currentMap;
    }

    public int spriteX() {
        return spriteX;
    }
    public int spriteY() {
        return spriteY;
    }
    public void setSpriteX(int spriteX) {
        this.spriteX = spriteX;
    }


}


