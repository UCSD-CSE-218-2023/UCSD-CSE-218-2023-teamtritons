/**
 * GameView
 * Probably the most important class for the game
 *
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quchen>
 */

package edu.ucsd.flappycow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.presenter.ButtonPresenter;
import edu.ucsd.flappycow.presenter.GroundPresenter;
import edu.ucsd.flappycow.presenter.PlayableCharacterPresenter;
import edu.ucsd.flappycow.presenter.PowerUpPresenter;
import edu.ucsd.flappycow.presenter.TutorialPresenter;
import edu.ucsd.flappycow.presenter.ObstaclePresenter;

import edu.ucsd.flappycow.sprites.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import edu.ucsd.flappycow.sprites.Background;
import edu.ucsd.flappycow.sprites.Coin;
import edu.ucsd.flappycow.sprites.Cow;
import edu.ucsd.flappycow.sprites.Frontground;
import edu.ucsd.flappycow.sprites.NyanCat;
import edu.ucsd.flappycow.sprites.Obstacle;
import edu.ucsd.flappycow.sprites.PauseButton;
import edu.ucsd.flappycow.sprites.IPlayableCharacter;
import edu.ucsd.flappycow.sprites.PowerUp;
import edu.ucsd.flappycow.sprites.Toast;
import edu.ucsd.flappycow.sprites.Tutorial;
import edu.ucsd.flappycow.sprites.Virus;

public class GameView extends SurfaceView {

    /** Milliseconds for game timer tick */
    public static final long UPDATE_INTERVAL = 50;        // = 20 FPS

    private Timer timer = new Timer();
    private TimerTask timerTask;

    /** The surfaceholder needed for the canvas drawing */
    private SurfaceHolder holder;

    private GameActivity gameActivity;
//    private IPlayableCharacter player;
//    private Background background;
//    private Frontground frontground;

    private List<Obstacle> obstacles = new ArrayList<Obstacle>();
//    private List<PowerUp> powerUps = new ArrayList<PowerUp>();

  //  private List<Obstacle> obstacles = new ArrayList<Obstacle>();
    private List<PowerUp> powerUps = new ArrayList<PowerUp>();


//    private IGameButton pauseButton;
    volatile private boolean paused = true;

//    private Tutorial tutorial;
    private TutorialPresenter tutorialPresenter;


    private ButtonPresenter pauseButton;

    private PowerUpPresenter powerUpPresenter;

    private Map<String, GroundPresenter> groundPresenterMap;


    private PlayableCharacterPresenter playableCharacterPresenter;

    private List<ObstaclePresenter> obstaclePresenters = new ArrayList<ObstaclePresenter>();


    public GameView(Context context) {
        super(context);
        this.gameActivity = (GameActivity) context;
        setFocusable(true);

        holder = getHolder();
//        player = new Cow(this, gameActivity, new Accessory(this, gameActivity));
        playableCharacterPresenter = new PlayableCharacterPresenter(this, ApplicationConstants.COW);
        groundPresenterMap = new HashMap<>();
        groundPresenterMap.put(ApplicationConstants.BACKGROUND, new GroundPresenter(this, ApplicationConstants.BACKGROUND));
        groundPresenterMap.put(ApplicationConstants.FRONTGROUND, new GroundPresenter(this, ApplicationConstants.FRONTGROUND));
//        background = new Background(this, gameActivity);
//        frontground = new Frontground(this, gameActivity);
//        this.pauseButton = new PauseButton(this, gameActivity);
        this.pauseButton = new ButtonPresenter(this);
        this.powerUpPresenter = new PowerUpPresenter(this);
        tutorialPresenter = new TutorialPresenter(this);
    }

    private void startTimer() {
        setUpTimerTask();
        timer = new Timer();
        timer.schedule(timerTask, UPDATE_INTERVAL, UPDATE_INTERVAL);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        if (timerTask != null) {
            timerTask.cancel();
        }
    }

    private void setUpTimerTask() {
        stopTimer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                GameView.this.run();
            }
        };
    }

    @Override
    public boolean performClick() {
        return super.performClick();
        // Just to remove the stupid warning
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
//        if (event.getAction() == MotionEvent.ACTION_DOWN  // Only for "touchdowns"
//            && !this.player.isDead()) { // No support for dead players
        if (event.getAction() == MotionEvent.ACTION_DOWN  // Only for "touchdowns"
                && !this.playableCharacterPresenter.isDead()) { // No support for dead players

            if (tutorialPresenter.isTutorialIsShown()) {
                // dismiss tutorial
                tutorialPresenter.setTutorialIsShown(false);
//                tutorialIsShown = false;
                resume();
//                this.player.onTap();
                this.playableCharacterPresenter.onTap();
            } else if (paused) {
                resume();
            } else if (pauseButton.isTouching((int) event.getX(), (int) event.getY()) && !this.paused) {
                pause();
            } else {
//                this.player.onTap();
                this.playableCharacterPresenter.onTap();
            }
        }
        return true;
    }

    /**
     * content of the timertask
     */
    public void run() {
        checkPasses();
        checkOutOfRange();
        checkCollision(this.getGameActivity().getResources().getDisplayMetrics().heightPixels);
        createObstacle();
        move();
        draw();
    }


    public Canvas getCanvas() {
        Canvas canvas;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            canvas = holder.lockHardwareCanvas();
        } else {
            canvas = holder.lockCanvas();
        }

        return canvas;
    }

    /**
     * Draw Tutorial
     */
    public void showTutorial() {
        tutorialPresenter.showTutorial();
//        player.move();
//        playableCharacterPresenter.move();
//        pauseButton.move();
//
//        while (!holder.getSurface().isValid()) {
//            /*wait*/
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        Canvas canvas = getCanvas();
//        drawCanvas(canvas, true);
//        tutorialPresenter.move();
//        tutorial.move();
//        tutorial.draw(canvas);
//        holder.unlockCanvasAndPost(canvas);
    }

    public void pause() {
        stopTimer();
        paused = true;
    }

    public void drawOnce() {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                if (tutorialPresenter.isTutorialIsShown()) {
                    showTutorial();
                } else {
                    draw();
                }
            }
        })).start();
    }

    public void resume() {
        paused = false;
        startTimer();
    }

    /**
     * Draws all gameobjects on the surface
     */
    private void draw() {
        while (!holder.getSurface().isValid()) {
            /*wait*/
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Canvas canvas = getCanvas();

        drawCanvas(canvas, true);

        holder.unlockCanvasAndPost(canvas);
    }

    /**
     * Draws everything normal,
     * except the player will only be drawn, when the parameter is true
     *
     * @param drawPlayer
     */
    public void drawCanvas(Canvas canvas, boolean drawPlayer) {
//        background.draw(canvas);
        groundPresenterMap.get(ApplicationConstants.BACKGROUND).draw(canvas);
        for (ObstaclePresenter r : obstaclePresenters) {
            r.draw(canvas);
        }
        powerUpPresenter.draw(canvas);
//        for (PowerUp p : powerUps) {
//            p.draw(canvas);
//        }
        if (drawPlayer) {
//            player.draw(canvas);
            playableCharacterPresenter.draw(canvas);
        }
//        frontground.draw(canvas);
        groundPresenterMap.get(ApplicationConstants.FRONTGROUND).draw(canvas);
        pauseButton.draw(canvas);

        // Score Text
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(getScoreTextMetrics());
        canvas.drawText(gameActivity.getResources().getString(R.string.onscreen_score_text) + " " + gameActivity.accomplishmentBox.getPoints()
                + " / " + gameActivity.getResources().getString(R.string.onscreen_coin_text) + " " + gameActivity.coins,
            0, getScoreTextMetrics(), paint);
    }

    /**
     * Let the player fall to the ground
     */
    private void playerDeadFall() {
//        player.dead();
        playableCharacterPresenter.dead();
        do {
//            player.move();
            playableCharacterPresenter.move();
            draw();
            // sleep
            try {
                Thread.sleep(UPDATE_INTERVAL / 4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        } while (!player.isTouchingGround());
        } while (!playableCharacterPresenter.isTouchingGround());
    }

    /**
     * Checks whether an obstacle is passed.
     */
    private void checkPasses() {
        for (ObstaclePresenter o : obstaclePresenters) {
            if (o.isPassed()) {
                if (!o.isAlreadyPassed()) {    // probably not needed
                    o.onPass();
                    createPowerUp();
                }
            }
        }
    }

    /**
     * Creates a toast with a certain chance
     */
    private void createPowerUp() {
        powerUpPresenter.createPowerUp(gameActivity.accomplishmentBox.getPoints());
//        // Toast
//        if (gameActivity.accomplishmentBox.getPoints() >= Toast.POINTS_TO_TOAST /*&& powerUps.size() < 1*/ && !(player instanceof NyanCat)) {
//            // If no powerUp is present and you have more than / equal 42 points
//            if (gameActivity.accomplishmentBox.getPoints() == Toast.POINTS_TO_TOAST) {    // First time 100 % chance
//                powerUps.add(new Toast(this, gameActivity));
//            } else if (Math.random() * 100 < 33) {    // 33% chance
//                powerUps.add(new Toast(this, gameActivity));
//            }
//        }
//
//        if ((powerUps.size() < 1) && (Math.random() * 100 < 20)) {
//            // If no powerUp is present and 20% chance
//            powerUps.add(new Coin(this, gameActivity));
//        }
//
//        if ((powerUps.size() < 1) && (Math.random() * 100 < 10)) {
//            // If no powerUp is present and 10% chance (if also no coin)
//            powerUps.add(new Virus(this, gameActivity));
//        }
    }

    /**
     * Checks whether the obstacles or powerUps are out of range and deletes them
     */
    private void checkOutOfRange() {
        for (int i = 0; i < obstaclePresenters.size(); i++) {
            if (this.obstaclePresenters.get(i).isOutOfRange()) {
                this.obstaclePresenters.remove(i);
                i--;
            }
        }
        powerUpPresenter.checkOutOfRange();
//        for (int i = 0; i < powerUps.size(); i++) {
//            if (this.powerUps.get(i).isOutOfRange()) {
//                this.powerUps.remove(i);
//                i--;
//            }
//        }
    }

    /**
     * Checks collisions and performs the action
     */
    private void checkCollision(int heightPixels) {
        for (ObstaclePresenter o : obstaclePresenters) {
            if (o.isColliding(getPlayer())) {
                o.onCollision();
                gameOver();
            }
        }
//        powerUpPresenter.checkCollision();
        powerUpPresenter.checkCollision();

//        for (int i = 0; i < powerUps.size(); i++) {
//            if (this.powerUps.get(i).isColliding(player)) {
//                this.powerUps.get(i).onCollision();
//                this.powerUps.remove(i);
//                i--;
//            }
//        }
        if (playableCharacterPresenter.isTouchingEdge()) {
            gameOver();
        }
    }

    /**
     * if no obstacle is present a new one is created
     */
    private void createObstacle() {
        if (obstaclePresenters.size() < 1) {
            obstaclePresenters.add(new ObstaclePresenter(this));
        }
    }

    /**
     * Update sprite movements
     */
    private void move() {
        for (ObstaclePresenter o : obstaclePresenters) {
            o.setSpeedX(-getSpeedX());
            o.move();
        }
        powerUpPresenter.move();
//        for (PowerUp p : powerUps) {
//            p.move();
//        }


        groundPresenterMap.get(ApplicationConstants.BACKGROUND).setSpeedX(-getSpeedX() / 2);
        groundPresenterMap.get(ApplicationConstants.BACKGROUND).move();

        groundPresenterMap.get(ApplicationConstants.FRONTGROUND).setSpeedX(-getSpeedX() * 4 / 3);
        groundPresenterMap.get(ApplicationConstants.FRONTGROUND).move();

//        background.setSpeedX(-getSpeedX() / 2);
//        background.move();
//
//        frontground.setSpeedX(-getSpeedX() * 4 / 3);
//        frontground.move();

        pauseButton.move();

//        player.move();
        playableCharacterPresenter.move();
    }

    /**
     * Changes the player to Nyan Cat
     */
    public void changeToNyanCat() {
        gameActivity.accomplishmentBox.setAchievement_toastification(true);
        gameActivity.handler.sendMessage(Message.obtain(gameActivity.handler, 1, R.string.toast_achievement_toastification, ApplicationConstants.SHOW_TOAST));

//        IPlayableCharacter tmp = this.player;
//        this.player = new NyanCat(this, gameActivity, new Rainbow(this, gameActivity));
//        this.player.setX(tmp.getX());
//        this.player.setY(tmp.getY());
//        this.player.setSpeedX(tmp.getSpeedX());
//        this.player.setSpeedY(tmp.getSpeedY());

        IPlayableCharacter tmp = this.playableCharacterPresenter.getPlayer();
        this.playableCharacterPresenter = new PlayableCharacterPresenter(this, ApplicationConstants.NYAN_CAT);
        this.playableCharacterPresenter.setX(tmp.getX());
        this.playableCharacterPresenter.setY(tmp.getY());
        this.playableCharacterPresenter.setSpeedX(tmp.getSpeedX());
        this.playableCharacterPresenter.setSpeedY(tmp.getSpeedY());

        gameActivity.musicShouldPlay = true;
        GameActivity.musicPlayer.start();
    }

    public void changeToSick() {
//        this.player.wearMask();
        this.playableCharacterPresenter.wearMask();
    }

    /**
     * return the speed of the obstacles/cow
     */
    public int getSpeedX() {
        // 16 @ 720x1280 px
        int speedDefault = this.getWidth() / 45;

        // 1,2 every 4 points @ 720x1280 px
        int speedIncrease = (int) (this.getWidth() / 600f * (gameActivity.accomplishmentBox.getPoints() / 4));

        int speed = speedDefault + speedIncrease;

        return Math.min(speed, 2 * speedDefault);
    }

    /**
     * Let's the player fall down dead, makes sure the runcycle stops
     * and invokes the next method for the dialog and stuff.
     */
    public void gameOver() {
        pause();
        playerDeadFall();
        gameActivity.gameOver();
    }

    public void revive() {
        gameActivity.numberOfRevive++;

        // This needs to run another thread, so the dialog can close.
        new Thread(this::setupRevive).start();
    }

    /**
     * Sets the player into startposition
     * Removes obstacles.
     * Let's the character blink a few times.
     */
    private void setupRevive() {
        gameActivity.gameOverDialog.hide();
        playableCharacterPresenter.setY(this.getHeight() / 2 - playableCharacterPresenter.getPlayer().getWidth() / 2);
        playableCharacterPresenter.setX(this.getWidth() / 6);
        obstaclePresenters.clear();
        powerUpPresenter.clear();
//        player.revive();
        playableCharacterPresenter.revive();
        for (int i = 0; i < 6; ++i) {
            while (!holder.getSurface().isValid()) {/*wait*/}
            Canvas canvas = getCanvas();
            drawCanvas(canvas, i % 2 == 0);
            holder.unlockCanvasAndPost(canvas);
            // sleep
            try {
                Thread.sleep(UPDATE_INTERVAL * 6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        resume();
    }

    /**
     * A value for the position and size of the onScreen score Text
     */
    public int getScoreTextMetrics() {
        return (int) (this.getHeight() / 21.0f);
        /*/ game.getResources().getDisplayMetrics().density)*/
    }

    public IPlayableCharacter getPlayer() {
        return playableCharacterPresenter.getPlayer();
    }

    public ButtonPresenter getPauseButton() {
        return pauseButton;
    }

    public GameActivity getGameActivity() {
        return this.gameActivity;
    }

}
