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

import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.presenter.ButtonPresenter;
import edu.ucsd.flappycow.presenter.GroundPresenter;
import edu.ucsd.flappycow.presenter.ObstaclePresenter;
import edu.ucsd.flappycow.presenter.PlayableCharacterPresenter;
import edu.ucsd.flappycow.presenter.PowerUpPresenter;
import edu.ucsd.flappycow.presenter.TutorialPresenter;
import edu.ucsd.flappycow.sprites.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import edu.ucsd.flappycow.sprites.Background;
import edu.ucsd.flappycow.sprites.Frontground;
import edu.ucsd.flappycow.sprites.IPlayableCharacter;

public class GameView extends SurfaceView{

    /** Milliseconds for game timer tick */
    public static final long UPDATE_INTERVAL = 50;        // = 20 FPSa

    /** The surfaceholder needed for the canvas drawing */
    private SurfaceHolder holder;

    private GameActivity gameActivity;
    private Background background;
    private Frontground frontground;

    private List<PowerUp> powerUps = new ArrayList<PowerUp>();

    private IGameButton pauseButton;
    volatile private boolean paused = true;

    private PlayableCharacterPresenter playableCharacterPresenter;

    private List<ObstaclePresenter> obstaclePresenters;
//    private TimerHandler timerHandler;
    private PowerUpPresenter powerUpPresenter;
    private TutorialPresenter tutorialPresenter;

    private Map<String, GroundPresenter> groundMap;

    private GroundPresenter groundPresenter;

    private ButtonPresenter buttonPresenter;

    private Timer timer = new Timer();
    private TimerTask timerTask;

    public GameView(Context context) {
        super(context);

        this.gameActivity = (GameActivity) context;
        setFocusable(true);

        holder = getHolder();
        playableCharacterPresenter = new PlayableCharacterPresenter(this, ApplicationConstants.COW);
        groundMap = new HashMap<>();
        groundMap.put(ApplicationConstants.BACKGROUND, new GroundPresenter(this, ApplicationConstants.BACKGROUND));
        groundMap.put(ApplicationConstants.FRONTGROUND, new GroundPresenter(this, ApplicationConstants.FRONTGROUND));
        buttonPresenter = new ButtonPresenter(this);

//        timerHandler = new TimerHandler(UPDATE_INTERVAL);
        powerUpPresenter = new PowerUpPresenter(this);
        tutorialPresenter = new TutorialPresenter(this);
        obstaclePresenters = new ArrayList<>();
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
        if (event.getAction() == MotionEvent.ACTION_DOWN  // Only for "touchdowns"
            && !playableCharacterPresenter.isDead()) { // No support for dead players
            if (tutorialPresenter.isTutorialIsShown()) {
                // dismiss tutorial
                tutorialPresenter.setTutorialIsShown(false);
                resume();
                playableCharacterPresenter.onTap();
            } else if (paused) {
                resume();
            } else if (buttonPresenter.isTouching((int) event.getX(), (int) event.getY()) && !this.paused) {
                pause();
            } else {
                playableCharacterPresenter.onTap();
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
        checkCollision();
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

    public void pause() {
        stopTimer();
        paused = true;
    }

    public void drawOnce() {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                if (tutorialPresenter.isTutorialIsShown()) {
                    tutorialPresenter.showTutorial();
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
        groundMap.get(ApplicationConstants.BACKGROUND).draw(canvas);
        for (ObstaclePresenter op : obstaclePresenters) {
            op.draw(canvas);
        }
        powerUpPresenter.draw(canvas);
        if (drawPlayer) {
            playableCharacterPresenter.draw(canvas);
        }
        groundMap.get(ApplicationConstants.FRONTGROUND).draw(canvas);
        buttonPresenter.draw(canvas);

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
    private void playerDeadFall(int viewHeight) {
        playableCharacterPresenter.dead();
        do {
            playableCharacterPresenter.move();
            draw();
            // sleep
            try {
                Thread.sleep(UPDATE_INTERVAL / 4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!playableCharacterPresenter.isTouchingGround());
    }

    /**
     * Checks whether an obstacle is passed.
     */
    private void checkPasses() {
        for (ObstaclePresenter op : obstaclePresenters) {
            if (op.isPassed(this.getPlayer().getX())) {
                if (!op.isAlreadyPassed()) {    // probably not needed
                    op.onPass();
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
    }

    /**
     * Checks collisions and performs the action
     */
    private void checkCollision() {
        for (ObstaclePresenter op : obstaclePresenters) {
            if (op.isColliding(getPlayer(), this.getGameActivity().getResources().getDisplayMetrics().heightPixels)) {
                op.onCollision();
                gameOver();
            }
        }
        powerUpPresenter.checkCollision();

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
        for (ObstaclePresenter op : obstaclePresenters) {
            op.setSpeedX(-getSpeedX());
            op.move();
        }
        powerUpPresenter.move();

        groundMap.get(ApplicationConstants.BACKGROUND).setSpeedX(-getSpeedX() / 2);
        groundMap.get(ApplicationConstants.BACKGROUND).move(this.getHeight(), this.getWidth());

        groundMap.get(ApplicationConstants.FRONTGROUND).setSpeedX(-getSpeedX() * 4 / 3);
        groundMap.get(ApplicationConstants.FRONTGROUND).move(this.getHeight(), this.getWidth());

        buttonPresenter.move(this.getHeight(), this.getWidth());

        playableCharacterPresenter.move();
    }

    /**
     * Changes the player to Nyan Cat
     */
    public void changeToNyanCat() {
        gameActivity.accomplishmentBox.setAchievement_toastification(true);
        gameActivity.handler.sendMessage(Message.obtain(gameActivity.handler, 1, R.string.toast_achievement_toastification, ApplicationConstants.SHOW_TOAST));

        IPlayableCharacter tmp = getPlayer();
        this.playableCharacterPresenter = new PlayableCharacterPresenter(this, ApplicationConstants.NYAN_CAT);
        this.playableCharacterPresenter.setX(tmp.getX());
        this.playableCharacterPresenter.setY(tmp.getY());
        this.playableCharacterPresenter.setSpeedX(tmp.getSpeedX());
        this.playableCharacterPresenter.setSpeedY(tmp.getSpeedY());

        GameActivity.getMediaPlayerPresenter().musicShouldPlay = true;
        GameActivity.getMediaPlayerPresenter().getMusicPlayer().start();
    }

    public void changeToSick() {
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
        playerDeadFall(this.getHeight());
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
        playableCharacterPresenter.setY(this.getHeight() / 2 - getPlayer().getWidth() / 2);
        playableCharacterPresenter.setX(this.getWidth() / 6);
        obstaclePresenters.clear();
        powerUpPresenter.clear();
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

    public GameActivity getGameActivity() {
        return this.gameActivity;
    }


    public IGameButton getPauseButton() {
        return pauseButton;
    }

    public PlayableCharacterPresenter getPlayableCharacterPresenter() {
        return playableCharacterPresenter;
    }
}
