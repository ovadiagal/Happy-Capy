package com.example.happycapy;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.happycapy.Entities.Capybara;
import com.example.happycapy.Entities.Enemy;
import com.example.happycapy.Logic.EnemyLaneLogic;
import com.example.happycapy.Logic.LogLaneLogic;
import com.example.happycapy.Logic.MapLogic;
import com.example.happycapy.Logic.PointsLogic;
import com.example.happycapy.Logic.SpriteLogic;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {
    /* Sprint 5 Unit Tests */
    @Test
    public void testInvalidSetLogSpeed() {
        LogLaneLogic l = new LogLaneLogic(6);
        l.setSpeed(20);
        assertNotEquals(20, l.getSpeed());
    }
    @Test
    public void testSetLogSpeed() {
        LogLaneLogic l = new LogLaneLogic(6);
        l.setSpeed(7);
        assertEquals(l.getSpeed(), 7);
    }

    @Test
    public void testSetLogInvalidDirection() {
        LogLaneLogic l = new LogLaneLogic(6);
        l.setDirection(-20);
        assertTrue(l.getDirection() == -1 || l.getDirection() == 1);
    }
    @Test
    public void testSetLogDirection() {
        LogLaneLogic l = new LogLaneLogic(6);
        l.setDirection(-1);
        assertEquals(-1, l.getDirection());
    }

    @Test
    public void testLogDirection() {
        LogLaneLogic l = new LogLaneLogic(6);
        assertTrue(l.getDirection() == -1 || l.getDirection() == 1);
    }
    @Test
    public void testLogLane() {
        LogLaneLogic l = new LogLaneLogic(6);
        assertEquals(6, l.getLane());
    }

    @Test
    public void testInvalidLogLane() {
        LogLaneLogic l = new LogLaneLogic(-4);
        assertNotEquals(-4, l.getLane());
    }

    @Test
    public void testRegLogType() {
        LogLaneLogic l = new LogLaneLogic(3);
        l.setType(0);
        assertEquals(0, l.getType());
    }

    @Test
    public void testMagicLogType() {
        LogLaneLogic l = new LogLaneLogic(3);
        l.setType(1);
        assertEquals(1, l.getType());
    }

    @Test
    public void testInvalidLogType() {
        LogLaneLogic l = new LogLaneLogic(3);
        l.setType(5);
        assertTrue(l.getType() == 1 || l.getType() == 0);
    }



  /* Sprint 4 Unit Tests */

    @Test
    public void testOnWater() {
        int[] tileList = {1, 2, 1};
        MapLogic m = new MapLogic(tileList, 0, 2);
        m.moveUp();
        assertTrue(m.onWater());
    }

    @Test
    public void testNotOnWater() {
        int[] tileList = {1, 3, 1};
        MapLogic m = new MapLogic(tileList, 0, 2);
        m.moveUp();
        assertFalse(m.onWater());
    }

    @Test
    public void testSetPlayerCol() {
        int[] tileList = {1, 3, 1, 1, 2, 1};
        MapLogic m = new MapLogic(tileList, 0, 0);
        m.setPlayerPos(2, 0);
        assertEquals(2, m.getPlayerCol());
    }

    @Test
    public void testSetPlayerRow() {
        int[] tileList = {1, 3, 1, 1, 2, 1};
        MapLogic m = new MapLogic(tileList, 0, 0);
        m.setPlayerPos(0, 3);
        assertEquals(3, m.getPlayerRow());
    }
    //@Test
//    public void testResetPoints() {
//        int[] tileList = {1, 2, 3, 2, 1, 1};
//        PointsLogic p = new PointsLogic((tileList));
//        p.moveDown();
//        p.update();
//        p.moveUp();
//        p.update();
//        p.resetPoints();
//        assertEquals(5, p.getPoints());
//    }

    @Test
    public void testDecrementLives() {
        SpriteLogic sp = new SpriteLogic(3, "BJ", null);
        sp.decreaseLives();
        sp.decreaseLives();
        assertEquals(1, sp.getLives());
    }

    @Test
    public void testSnakeSpeed() {
        EnemyLaneLogic e = new EnemyLaneLogic("snake", 1, 2);
        assertEquals(2, e.getSpeed());
    }

    //Patrick Test 2
    @Test
    public void testTigerSpeed() {
        EnemyLaneLogic e = new EnemyLaneLogic("tiger", 1, 2);
        assertEquals(4, e.getSpeed());
    }

    @Test
    public void testSnakeSize() {
        EnemyLaneLogic e = new EnemyLaneLogic("snake", 1, 2);
        assertEquals(228, e.getSpriteSize());
    }

    @Test
    public void testTigerSize() {
        EnemyLaneLogic e = new EnemyLaneLogic("tiger", 1, 2);
        assertEquals(150, e.getSpriteSize());
    }


    /* Sprint 3 Unit Tests */

    //Ava Test 1
//    @Test
//    public void getStartingPoints() {
//        int[] tileList = {1, 2, 3, 2, 1, 1};
//        PointsLogic p = new PointsLogic(tileList);
//        assertEquals(5, p.getPoints());
//    }

    //Ava Test 2
//    @Test
//    public void pointsMoveDown() {
//        int[] tileList = {1, 2, 3, 2, 1, 1};
//        PointsLogic p = new PointsLogic(tileList);
//        p.moveDown();
//        p.update();
//        assertEquals(5, p.getPoints());
//    }

    //Gal Test 1
    /*
    @Test
    public void pointsMoveUp() {
        int[] tileList = {1, 2, 3, 2, 1, 0};
        PointsLogic p = new PointsLogic(tileList);
        p.moveUp();
        p.update();
        p.moveUp();
        p.update();
        assertEquals(30, p.getPoints());
    }
     */

    //Gal Test 2

    /*
    @Test
    public void pointsMoveUpDown() {
        int[] tileList = {1, 2, 3, 2, 1, 0};
        PointsLogic p = new PointsLogic(tileList);
        p.moveUp();
        p.update();
        p.moveDown();
        p.update();
        p.moveUp();
        p.update();
        assertEquals(10, p.getPoints());
    }
     */

    //BJ Test 1
    @Test
    public void testGetLane() {
        EnemyLaneLogic ELL = new EnemyLaneLogic("snake", 1,2);
        assertEquals(2, ELL.getLane());
    }

    //BJ Test 2
    @Test public void randomizedDirection() {

        EnemyLaneLogic ELL = new EnemyLaneLogic("snake",-1, 2);
        EnemyLaneLogic ELL2 = new EnemyLaneLogic("snake",-1, 3);
        while (ELL.getDirection() == ELL2.getDirection()) {
            ELL = new EnemyLaneLogic("snake", -1, 2);
        }

        assertNotEquals(ELL.getDirection(), ELL2.getDirection());

    }
    //Patrick Test1
    @Test
    public void getCapybaraType() {
        SpriteLogic s = new SpriteLogic(3, "Patrick","pink");
        assertEquals("pink", s.getStringType());
    }
    //Patrick Test2
    @Test
    public void setStringType() {
        SpriteLogic s = new SpriteLogic(3,"Patrick","0");
        s.setStringType("blue");
        assertEquals("blue", s.getStringType());
    }

    //Connor Test 1
//    @Test
//    public void pointsMovePastGoal() {
//        int[] tileList = {1, 2, 0};
//        PointsLogic p = new PointsLogic(tileList);
//        p.moveUp();
//        p.update();
//        p.moveUp();
//        p.update();
//        p.moveUp();
//        p.update();
//        p.moveUp();
//        p.update();
//        assertEquals(35, p.getPoints());
//    }


    //Connor Test 2
    @Test
    public void testBadgerSpeed() {
        EnemyLaneLogic e = new EnemyLaneLogic("badger", 1, 2);
        assertEquals(7, e.getSpeed());
    }


/* Sprint 2 Unit Tests */
    //Bharath Test1
    @Test
    public void getLives() {
        SpriteLogic s = new SpriteLogic(3, "BJ", "green");
        assertEquals(3,s.getLives());

    }

    @Test
    //Bharath Test2
    public void getName() {
        SpriteLogic s = new SpriteLogic(3, "BJ", "green");
        assertEquals("BJ", s.getName());
    }

    @Test
    public void setCustomLives() {
        SpriteLogic s = new SpriteLogic(5, "Gal", "blue");
        s.setLives(3);
        assertEquals(3, s.getLives());
    }

    //Gal Test 2
    @Test
    public void setDefaultLives() {
        SpriteLogic s = new SpriteLogic(5, "Gal", "blue");
        s.setLives(3);
        assertEquals(3, s.getLives());
    }

    //Ava Test 2
    @Test
    public void setName() {
        SpriteLogic s = new SpriteLogic(6, "Ava", "pink");
        s.setName("Ava Gavin");
        assertEquals("Ava Gavin", s.getName());
    }

    @Test
    public void setBadName() {
        SpriteLogic s = new SpriteLogic(6, "Ava", "pink");
        s.setName("");
        assertEquals("default", s.getName());
    }

    //Josh Test
//    @Test
//    public void testMoveUp() {
//        int[] testTileList = {0, 2, 2, 1, 2, 2, 1, 3, 3, 3, 3, 1, 2, 2, 1};
//        // initialize a state of the game where the player is in the center of the screen
//        MapLogic testML = new MapLogic(testTileList, 4, 7);
//        int initPlayerCol = testML.getPlayerCol();
//        int initPlayerRow = testML.getPlayerRow();
//
//        //when you move up, you should not move in the left right direction and the player's row should
//        // decrease by 1
//        testML.moveUp();
//        assertEquals(initPlayerCol, testML.getPlayerCol());
//        assertEquals(initPlayerRow - 1, testML.getPlayerRow());
//    }

    //Josh Test
//    @Test
//    public void testMoveDown() {
//        int[] testTileList = {0, 2, 2, 1, 2, 2, 1, 3, 3, 3, 3, 1, 2, 2, 1};
//        // initialize a state of the game where the player is in the center of the screen
//        MapLogic testML = new MapLogic(testTileList, 4, 7);
//        int initPlayerCol = testML.getPlayerCol();
//        int initPlayerRow = testML.getPlayerRow();
//
//        //when you move down, you should not move in the left-right direction and player's row should
//        // increase by 1
//        testML.moveDown();
//        assertEquals(initPlayerCol, testML.getPlayerCol());
//        assertEquals(initPlayerRow + 1, testML.getPlayerRow());
//    }

    // Samyak's tests
//    @Test
//    public void testMoveRight() {
//        int[] testTileList = {0, 2, 2, 1, 2, 2, 1, 3, 3, 3, 3, 1, 2, 2, 1};
//        MapLogic testML = new MapLogic(testTileList, 4, 7);
//        int initPlayerCol = testML.getPlayerCol();
//        int initPlayerRow = testML.getPlayerRow();
//        testML.moveRight();
//        assertEquals(initPlayerCol + 1, testML.getPlayerCol());
//        assertEquals(initPlayerRow, testML.getPlayerRow());
//    }

    // Samyaks Test
//    @Test
//    public void testMoveLeft() {
//        int[] testTileList = {0, 2, 2, 1, 2, 2, 1, 3, 3, 3, 3, 1, 2, 2, 1};
//        MapLogic testML = new MapLogic(testTileList, 4, 7);
//        int initPlayerCol = testML.getPlayerCol();
//        int initPlayerRow = testML.getPlayerRow();
//        testML.moveLeft();
//        assertEquals(initPlayerCol - 1, testML.getPlayerCol());
//        assertEquals(initPlayerRow, testML.getPlayerRow());
//    }

    //Josh Test
    @Test
    public void noMovementOutsideScreen() {
        int[] testTileList = {0, 2, 2, 1, 2, 2, 1, 3, 3, 3, 3, 1, 2, 2, 1};
        MapLogic testML = new MapLogic(testTileList);

        //simulate touching up button
        for (int i = 0; i < 30; i++) {
            testML.moveRight();
        }
        assertEquals(true, (testML.getPlayerCol() >= 0) && (testML.getPlayerCol() < 9) &&
                (testML.getPlayerRow() >= 0) && (testML.getPlayerRow() < 15));

        //sim touching down button
        for (int i = 0; i < 30; i++) {
            testML.moveDown();
        }
        assertEquals(true, (testML.getPlayerCol() >= 0) && (testML.getPlayerCol() < 9) &&
                (testML.getPlayerRow() >= 0) && (testML.getPlayerRow() < 15));

        // sim touching left
        for (int i = 0; i < 30; i++) {
            testML.moveLeft();
        }
        assertEquals(true, (testML.getPlayerCol() >= 0) && (testML.getPlayerCol() < 9) &&
                (testML.getPlayerRow() >= 0) && (testML.getPlayerRow() < 15));

        // sim touching right
        for (int i = 0; i < 30; i++) {
            testML.moveRight();
        }
        assertEquals(true, (testML.getPlayerCol() >= 0) && (testML.getPlayerCol() < 9) &&
                (testML.getPlayerRow() >= 0) && (testML.getPlayerRow() < 15));
    }

    //Philip Test
    @Test
    public void testCurrentTile() {
        int[] testTileList = {0, 2, 2, 1, 2, 2, 1, 3, 3, 3, 3, 1, 2, 2, 1};
        MapLogic testML = new MapLogic(testTileList);

        //sim moving up 1
        for (int i = testTileList.length - 1; i >= 0; i--) {
            assertEquals(testTileList[i], testML.getCurrentMap()[i][0]);
            testML.moveUp();
        }
    }

    //Philip Test
    @Test
    public void getPlayerColum() {
        int[] testTileList = {0, 2, 2, 1, 2, 2, 1, 3, 3, 3, 3, 1, 2, 2, 1};
        MapLogic testML = new MapLogic(testTileList);
        assertEquals(4, testML.getPlayerCol());
    }


    @Test
    public void checkValidPos() {
        int[] tileList = {1,2,3,1,0,1,1};
        MapLogic mapLogic = new MapLogic(tileList, 2, 7);
        assertTrue(mapLogic.checkValidPosition());
    }

    @Test
    public void checkInvalidColPos() {
        int[] tileList = {1,2,3,1,0,1,1};
        MapLogic mapLogic = new MapLogic(tileList, 200, 7);
        assertFalse(mapLogic.checkValidPosition());
    }

    @Test
    public void checkInvalidRowPos() {
        int[] tileList = {1,2,3,1,0,1,1};
        MapLogic mapLogic = new MapLogic(tileList, 2, 200);
        assertFalse(mapLogic.checkValidPosition());
    }




}