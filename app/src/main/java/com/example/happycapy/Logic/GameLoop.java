package com.example.happycapy.Logic;

import android.content.Intent;

import com.example.happycapy.GameOver;
import com.example.happycapy.GamePanel;
import com.example.happycapy.GameScreen;
import com.example.happycapy.WinScreen;

public class GameLoop implements Runnable {
    private Thread gameThread;
    private GamePanel gamePanel;
    private GameScreen gameScreen;


    public GameLoop(GamePanel gamePanel, GameScreen gameScreen) {
        this.gamePanel = gamePanel;
        gameThread = new Thread(this);
        this.gameScreen = gameScreen;
    }

    @Override
    public void run() {



        long lastFPScheck = System.currentTimeMillis();
        int fps = 0;

        long lastDelta = System.nanoTime();
        long nanoSecond = 1_000_000_000; // one second in nano times
        // Whatever we put inside of here will be executed when we run our thread
        // We need our gameloop to run over n over!

        while (gamePanel.getRunning()) {
            long nowDelta = System.nanoTime();
            double timeSinceLastDelta = nowDelta - lastDelta;
            // now we have a time in nanoseconds from the last delta
            double delta = timeSinceLastDelta / nanoSecond;

            gamePanel.update(delta);
            gamePanel.render();

            lastDelta = nowDelta;

            fps++;


            long now = System.currentTimeMillis();
            if (now - lastFPScheck >= 1000) {
                System.out.println("FPS: " + fps);
                fps = 0;
                lastFPScheck += 1000;
            }
        }

        //gameThread.stop();
        Intent intent;
        if (gamePanel.getWin()) {
            intent = new Intent(gameScreen.getGameContext(), WinScreen.class);
        } else {
            intent = new Intent(gameScreen.getGameContext(), GameOver.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        gameScreen.getGameContext().startActivity(intent);

    }

    public void startGameLoop() {
        gameThread.start();
    }

}
