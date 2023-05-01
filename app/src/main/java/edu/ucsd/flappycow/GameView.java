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
import edu.ucsd.flappycow.sprites.*;

import java.util.ArrayList;
import java.util.List;
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
import edu.ucsd.flappycow.sprites.Toast;
import edu.ucsd.flappycow.sprites.Tutorial;
import edu.ucsd.flappycow.sprites.Virus;

public class GameView extends SurfaceView{

    /** Milliseconds for game timer tick */
    public static final long UPDATE_INTERVAL = 50;        // = 20 FPSa

    /** The surfaceholder needed for the canvas drawing */
    private SurfaceHolder holder;

    private GameActivity gameActivity;
    private Background background;
    private Frontground frontground;
    private List<Obstacle> obstacles = new ArrayList<Obstacle>();

    private IGameButton pauseButton;
    volatile private boolean paused = true;

    private PlayableCharacterPresenter playableCharacterPresenter;
    private TimerHandler timerHandler;
    private PowerUpPresenter powerUpPresenter;
    private TutorialPresenter tutorialPresenter;

    private GroundPresenter backgroundPresenter;
    private GroundPresenter frontgroundPresenter;

    private ButtonPresenter buttonPresenter;

    public GameView(Context context) {
        super(context);

        this.gameActivity = (GameActivity) context;
        setFocusable(true);

        holder = getHolder();
        playableCharacterPresenter = new PlayableCharacterPresenter(this, ApplicationConstants.COW);
        backgroundPresenter = new GroundPresenter(ApplicationConstants.BACKGROUND);
        frontgroundPresenter = new GroundPresenter(ApplicationConstants.FRONTGROUND);
        buttonPresenter = new ButtonPresenter();

        timerHandler = new TimerHandler(UPDATE_INTERVAL);
        powerUpPresenter = new PowerUpPresenter(this);
        tutorialPresenter = new TutorialPresenter(this);
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
        timerHandler.stopTimer();
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
        timerHandler.startTimer(this);
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
        backgroundPresenter.draw(canvas);
        for (Obstacle r : obstacles) {
            r.draw(canvas);
        }
        powerUpPresenter.draw(canvas);
        if (drawPlayer) {
            playableCharacterPresenter.draw(canvas);
        }
        frontgroundPresenter.draw(canvas);
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
        for (Obstacle o : obstacles) {
            if (o.isPassed(this.getPlayer().getX())) {
                if (!o.isAlreadyPassed) {    // probably not needed
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
    }

    /**
     * Checks whether the obstacles or powerUps are out of range and deletes them
     */
    private void checkOutOfRange() {
        for (int i = 0; i < obstacles.size(); i++) {
            if (this.obstacles.get(i).isOutOfRange()) {
                this.obstacles.remove(i);
                i--;
            }
        }

        powerUpPresenter.checkOutOfRange();
    }

    /**
     * Checks collisions and performs the action
     */
    private void checkCollision() {
        for (Obstacle o : obstacles) {
            if (o.isColliding(getPlayer())) {
                o.onCollision();
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
        if (obstacles.size() < 1) {
            obstacles.add(new Obstacle(new Spider(), new WoodLog(), this.getSpeedX(), this.gameActivity.getResources().getDisplayMetrics().heightPixels, this.gameActivity.getResources().getDisplayMetrics().widthPixels));
        }
    }

    /**
     * Update sprite movements
     */
    private void move() {
        for (Obstacle o : obstacles) {
            o.setSpeedX(-getSpeedX());
            o.move(this.getHeight(), this.getWidth());
        }
        powerUpPresenter.move();

        backgroundPresenter.setSpeedX(-getSpeedX() / 2);
        backgroundPresenter.move(this.getHeight(), this.getWidth());

        frontgroundPresenter.setSpeedX(-getSpeedX() * 4 / 3);
        frontgroundPresenter.move(this.getHeight(), this.getWidth());

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
        obstacles.clear();
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

    @Override
    public SurfaceHolder getHolder() {
        return holder;
    }
}
