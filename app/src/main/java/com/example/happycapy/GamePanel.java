package com.example.happycapy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.happycapy.Entities.Melon;
import com.example.happycapy.Entities.Tile;
import com.example.happycapy.Logic.EnemyLaneLogic;
import com.example.happycapy.Logic.EnemyManager;
import com.example.happycapy.Logic.GameLoop;
import com.example.happycapy.Logic.LogLaneLogic;
import com.example.happycapy.Logic.LogManager;
import com.example.happycapy.Logic.MapLogic;
import com.example.happycapy.Logic.MelonManager;
import com.example.happycapy.Logic.SpriteLogic;
import com.example.happycapy.Logic.PointsLogic;

import java.util.ArrayList;
import java.util.Random;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    // GamePanel is where all the magic happens. It is a SurfaceView
    // (meaning it receives its own thread, stopping lag)
    // and is responsible for repeatedly drawing onto the screen.
    // The GamePanel class is run by a gameLoop that repeatedly
    // prompts the GamePanel to update data and render.
    private SurfaceHolder holder;
    private GameLoop gameLoop;
    private Bitmap mySprite;
    private Bitmap upButton;
    private Bitmap downButton;
    private Bitmap leftButton;
    private Bitmap rightButton;
    private int[] tileList =  {0, 2, 2, 1, 2, 2, 2, 1, 3, 3, 3, 1, 3, 3, 1};

    //{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
    //goal = 0, safe = 1, water = 2, road = 3
    private String difficulty;
    private String playerName;
    private int points = 0;
    private MapLogic mapLogic;
    //private ArrayList<EnemyLaneLogic> enemyManager = new ArrayList<EnemyLaneLogic>();
    //private ArrayList<LogLaneLogic> logManager = new ArrayList<LogLaneLogic>();
    private SpriteLogic spriteLogic = GameScreen.getSpriteLogic();
    private PointsLogic pointsLogic;
    private GameScreen gameScreen;
    private EnemyManager enemyManager;
    private LogManager logManager;
    private Random rand = new Random();
    private boolean running;
    private boolean win = false;

    private boolean endlessMode = false;
    private MelonManager melonManager;

    private MelonManager melonManager2;
    private MelonManager melonManager3;

    private MelonManager melonManager4;
    private MelonManager melonManager5;

    private boolean rainbow;
    private boolean jesus = false;



    public GamePanel(Context context) {
        super(context);
        running = true;
        gameScreen = (GameScreen) context;
        holder = getHolder();
        holder.addCallback(this); // / looking for a callback,
        gameLoop = new GameLoop(this, gameScreen);

        for (int i = 1; i < tileList.length - 1; i++) {
            tileList[i] = rand.nextInt(3) + 1;
        }

        playerName = GameScreen.getPlayerName();

        if (playerName.equals("Gal")) {
            rainbow = true;
        } else if (playerName.equals("Ava")) {
            for (int i = 1; i < tileList.length - 1; i++) {
                tileList[i] = 2;
            }
        } else if (playerName.equals("BJ")) {
            for (int i = 1; i < tileList.length - 1; i++) {
                tileList[i] = 3;
            }
        } else if (playerName.equals("Connor")) {
            melonManager2 = new MelonManager(tileList);
            melonManager3 = new MelonManager(tileList);
            melonManager4 = new MelonManager(tileList);
            melonManager5 = new MelonManager(tileList);
        } else if (playerName.equals("Patrick")) {
            for (int i = 1; i < tileList.length - 1; i++) {
                tileList[i] = 1;
            }
        } else if (playerName.equals("Jesus")) {
            jesus = true;
        }

        endlessMode = spriteLogic.getEndless();

        mapLogic = new MapLogic(tileList);
        pointsLogic = new PointsLogic(tileList);
        enemyManager = new EnemyManager(tileList);
        logManager = new LogManager(tileList);
        melonManager = new MelonManager(tileList);

        SpriteLogic spriteLogic = GameScreen.getSpriteLogic();
        mySprite = spriteLogic.getType().getSprite(0, 1);


        if (spriteLogic.getLives() == 1) {
            difficulty = "HARD";
        } else if (spriteLogic.getLives() == 3) {
            difficulty = "MEDIUM";
        } else if (spriteLogic.getLives() == 5) {
            difficulty = "EASY";
        }

        playerName = GameScreen.getPlayerName();



    }

    public void render() {
        Canvas c = holder.lockCanvas();

        // Set up black background
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        c.drawPaint(paint);

        // Render components
        renderTiles(c);
        renderText(c);
        //renderButtons(c);
        renderEnemies(c);
        renderLogs(c);

        if (melonManager.getOnScreen()) {
            c.drawBitmap(Melon.MELON.getSprite(), melonManager.getX(), melonManager.getY(), null);
        }

        if (melonManager2 != null) {
            if (melonManager2.getOnScreen()) {
                c.drawBitmap(Melon.MELON.getSprite(), melonManager2.getX(),
                        melonManager2.getY(), null);
            }
        }
        if (melonManager3 != null) {
            if (melonManager3.getOnScreen()) {
                c.drawBitmap(Melon.MELON.getSprite(), melonManager3.getX(),
                        melonManager3.getY(), null);
            }
        }
        if (melonManager4 != null) {
            if (melonManager4.getOnScreen()) {
                c.drawBitmap(Melon.MELON.getSprite(), melonManager4.getX(),
                        melonManager4.getY(), null);
            }
        }
        if (melonManager5 != null) {
            if (melonManager5.getOnScreen()) {
                c.drawBitmap(Melon.MELON.getSprite(), melonManager5.getX(),
                        melonManager5.getY(), null);
            }
        }


        // Draw sprite
        c.drawBitmap(mySprite, mapLogic.spriteX(), mapLogic.spriteY(), null);
        points = pointsLogic.update();

        holder.unlockCanvasAndPost(c);
    }

    public void update(double delta) {
        // leaving this for later, currently can be blank.
        double x = delta;
        boolean collided = false;

        // Update enemies
        enemyManager.update();
        enemyManager.collided(mapLogic.spriteX(), mapLogic.spriteY());

        logManager.update();
        if (logManager.onLog(mapLogic.spriteX(), mapLogic.spriteY())) {
            mapLogic.setSpriteX(logManager.updateSprite(mapLogic.spriteX(), mapLogic.spriteY()));
        }

        if (melonManager.checkForMelonCollision(mapLogic.spriteX(), mapLogic.spriteY())) {
            melonManager.remove();
            pointsLogic.melonTouched();
        }
      
        if (melonManager2 != null) {
            if (melonManager2.checkForMelonCollision(mapLogic.spriteX(), mapLogic.spriteY())) {
                melonManager2.remove();
                pointsLogic.melonTouched();
            }

            if (melonManager3.checkForMelonCollision(mapLogic.spriteX(), mapLogic.spriteY())) {
                melonManager3.remove();
                pointsLogic.melonTouched();
            }
            if (melonManager4.checkForMelonCollision(mapLogic.spriteX(), mapLogic.spriteY())) {
                melonManager4.remove();
                pointsLogic.melonTouched();
            }
            if (melonManager5.checkForMelonCollision(mapLogic.spriteX(), mapLogic.spriteY())) {
                melonManager5.remove();
                pointsLogic.melonTouched();
            }
        }
        //movingHorizontal = mapLogic.animateHorizontal(movingHorizontal, spriteDirection);
        //movingVertical = mapLogic.animateVertical(movingVertical, upOrDown);
        if (mapLogic.spriteX() < -80 || mapLogic.spriteX() > 1080) {
            mapLogic.setPlayerPos(4, 14);
            pointsLogic.resetPoints();
            spriteLogic.decreaseLives();
            if (spriteLogic.getLives() == 0) {
                running = false;
            }
        }
        if (mapLogic.onWater() && !jesus) {
            if (!logManager.onLog(mapLogic.spriteX(), mapLogic.spriteY())) {
                mapLogic.setPlayerPos(4, 14);
                pointsLogic.resetPoints();
                spriteLogic.decreaseLives();
                if (spriteLogic.getLives() == 0) {
                    running = false;
                }
            }
        } else if (enemyManager.collided(mapLogic.spriteX(), mapLogic.spriteY())) {
            mapLogic.setPlayerPos(4, 14);
            pointsLogic.resetPoints();
            spriteLogic.decreaseLives();
            if (spriteLogic.getLives() == 0) {
                running = false;
            }
        }

        if (mapLogic.spriteY() == 0) {
            if (endlessMode) {
                reset();
            } else {
                win = true;
                running = false;
            }
        }
    }


    //    private boolean movingHorizontal = false;
    //    private boolean movingVertical = false;
    //    private int spriteDirection = 1;
    //    private int upOrDown = 0;

    //    public boolean getMovingHorizontal() {
    //        return movingHorizontal;
    //    }
    //
    //    public boolean getMovingVertical() {
    //        return movingVertical;
    //    }

    public void changeColor() {
        if (rainbow) {
            if (spriteLogic.getStringType().equals("brown")) {
                spriteLogic.setStringType("pink");
            } else if (spriteLogic.getStringType().equals("pink")) {
                spriteLogic.setStringType("green");
            } else {
                spriteLogic.setStringType("brown");
            }
        }
    }

    public void up() {
        changeColor();
        pointsLogic.moveUp();
        mapLogic.moveUp();
        mapLogic.snap(logManager, "vertical");
        //upOrDown = -1;
        //movingVertical = true;
        //spriteDirection = 1;
        mySprite = spriteLogic.getType().getSprite(0, 1);
    }

    public void down() {
        changeColor();
        pointsLogic.moveDown();
        mapLogic.moveDown();
        mapLogic.snap(logManager, "vertical");
        //mapLogic.snap();
        //upOrDown = 1;
        //movingVertical = true;
        //spriteDirection = 1;
        mySprite = spriteLogic.getType().getSprite(0, 1);
    }

    public void right() {
        changeColor();
        mapLogic.moveRight();
        mapLogic.snap(logManager, "horizontal");
        //movingHorizontal = true;
        //spriteDirection = 2;
        mySprite = spriteLogic.getType().getSprite(0, 2);
    }

    public void left() {
        changeColor();
        mapLogic.moveLeft();
        mapLogic.snap(logManager, "horizontal");
        //movingHorizontal = true;
        //spriteDirection = 0;
        mySprite = spriteLogic.getType().getSprite(0, 0);
    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            // && !movingHorizontal && !movingVertical) {

            // theres def a better way to do this that will be scalable!!!
            // but it works for now :)
            // check for up button
            if (((motionEvent.getX() > 850) && (motionEvent.getX() < 950))
                    && ((motionEvent.getY() > 1510) && (motionEvent.getY() < 1610))) {
                up();
            // now check for down button
            } else if (((motionEvent.getX() > 850) && (motionEvent.getX() < 950))
                    && ((motionEvent.getY() > 1710) && (motionEvent.getY() < 1810))) {
                down();
            // now check for right button
            } else if (((motionEvent.getX() > 950) && (motionEvent.getX() < 1050))
                    && ((motionEvent.getY() > 1610) && (motionEvent.getY() < 1710))) {
                right();
            } else if (((motionEvent.getX() > 750) && (motionEvent.getX() < 850))
                    && ((motionEvent.getY() > 1610) && (motionEvent.getY() < 1710))) {
                left();
            }
        }
        return true;
    }

    private void renderTiles(Canvas c) {
        for (int i = 0; i < tileList.length; i++) {
            for (int j = 0; j < 9; j++) {
                switch (tileList[i]) {
                case 0:
                    c.drawBitmap(Tile.GOAL.getTile(),
                            j * 120, i * 120, null);
                    break;
                case 1:
                    c.drawBitmap(Tile.SAFE.getTile(),
                            j * 120, i * 120, null);
                    break;
                case 2:
                    c.drawBitmap(Tile.WATER.getTile(),
                            j * 120, i * 120, null);
                    break;
                case 3:
                    c.drawBitmap(Tile.ROAD.getTile(),
                            j * 120, i * 120, null);
                    break;
                default:
                    System.out.println("Bad tile");
                }
            }
        }
    }

    private void renderLogs(Canvas c) {
        ArrayList<LogLaneLogic> logs = logManager.getLogArray();
        synchronized (logs) {
            for (LogLaneLogic each : logs) {
                synchronized (each) {
                    for (int pos : each.getPositions()) {
                        c.drawBitmap(each.getSprite(), pos, each.getLane() * 120, null);
                    }
                }
            }
        }
    }


    private void renderEnemies(Canvas c) {
        ArrayList<EnemyLaneLogic> enemies = enemyManager.getEnemyArray();
        synchronized (enemies) {
            for (EnemyLaneLogic each : enemies) {
                synchronized (each) {
                    for (int pos : each.getPositions()) {
                        c.drawBitmap(each.getSprite(), pos, each.getLane() * 120, null);
                    }
                }
            }
        }
    }

    private void renderButtons(Canvas c) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        downButton = BitmapFactory.decodeResource(getResources(),
                R.drawable.down_button, options);
        downButton = Bitmap.createScaledBitmap(downButton,
                downButton.getWidth() * 2, downButton.getHeight() * 2, false);
        c.drawBitmap(downButton, 850, 1710, null);

        rightButton = BitmapFactory.decodeResource(getResources(),
                R.drawable.right_button, options);
        rightButton = Bitmap.createScaledBitmap(rightButton,
                rightButton.getWidth() * 2, rightButton.getHeight() * 2, false);
        c.drawBitmap(rightButton, 950, 1610, null);

        leftButton = BitmapFactory.decodeResource(getResources(),
                R.drawable.left_button, options);
        leftButton = Bitmap.createScaledBitmap(leftButton,
                leftButton.getWidth() * 2, leftButton.getHeight() * 2, false);
        c.drawBitmap(leftButton, 750, 1610, null);
        upButton = BitmapFactory.decodeResource(getResources(),
                R.drawable.up_button, options);
        upButton = Bitmap.createScaledBitmap(upButton,
                upButton.getWidth() * 2, upButton.getHeight() * 2, false);
        c.drawBitmap(upButton, 850, 1510, null);
    }

    private void renderText(Canvas c) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(55);
        paint.setFakeBoldText(true);
        String livesString = String.valueOf(GameScreen.getSpriteLogic().getLives());
        c.drawText("Lives: " + livesString, 20, c.getHeight() - 125, paint);
        /*doing points based off of max tile row reached and using the tile index as a multiplier
        can add once we do vehicles getting the speed of the vehicles movement and using it as
        a multiplier to give more points the faster the cars are moving */
        c.drawText(String.valueOf(points), 20, c.getHeight() - 2, paint);
        c.drawText(difficulty,  20, c.getHeight() - 75, paint);
        paint.setTextAlign(Paint.Align.CENTER);
        c.drawText(playerName,  c.getWidth() / 2, c.getHeight() - 2, paint);

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startGameLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    public int[] getTileList() {
        return tileList;
    }


    public void setPoints(int points) {
        this.points = points;
    }

    public boolean getRunning() {
        return running;
    }

    public boolean getWin() {
        return win;
    }

    private void reset() {
        for (int i = 1; i < tileList.length - 1; i++) {
            tileList[i] = rand.nextInt(3) + 1;
        }

        if (playerName.equals("Gal")) {
            rainbow = true;
        } else if (playerName.equals("Ava")) {
            for (int i = 1; i < tileList.length - 1; i++) {
                tileList[i] = 2;
            }
        } else if (playerName.equals("BJ")) {
            for (int i = 1; i < tileList.length - 1; i++) {
                tileList[i] = 3;
            }
        } else if (playerName.equals("Connor")) {
            melonManager2 = new MelonManager(tileList);
            melonManager3 = new MelonManager(tileList);
            melonManager4 = new MelonManager(tileList);
            melonManager5 = new MelonManager(tileList);
        } else if (playerName.equals("Patrick")) {
            for (int i = 1; i < tileList.length - 1; i++) {
                tileList[i] = 1;
            }
        } else if (playerName.equals("Jesus")) {
            jesus = true;
        }

        mapLogic = new MapLogic(tileList);
        pointsLogic = new PointsLogic(tileList);
        enemyManager = new EnemyManager(tileList);
        logManager = new LogManager(tileList);
        melonManager = new MelonManager(tileList);

        if (melonManager2 != null) {
            melonManager2 = new MelonManager(tileList);
            melonManager3 = new MelonManager(tileList);
            melonManager4 = new MelonManager(tileList);
            melonManager5 = new MelonManager(tileList);
        }

    }


}
