/**
 * GameView
 * Probably the most important class for the game
 *
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quchen>
 */

package edu.ucsd.flappycow.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.presenter.ButtonPresenter;
import edu.ucsd.flappycow.presenter.CoinPresenter;
import edu.ucsd.flappycow.presenter.CowPresenter;
import edu.ucsd.flappycow.presenter.GroundPresenter;
import edu.ucsd.flappycow.presenter.PlayableCharacterPresenter;
import edu.ucsd.flappycow.presenter.PowerUpPresenter;
import edu.ucsd.flappycow.presenter.ToastPresenter;
import edu.ucsd.flappycow.presenter.TutorialPresenter;
import edu.ucsd.flappycow.presenter.ObstaclePresenter;

import edu.ucsd.flappycow.presenter.VirusPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import edu.ucsd.flappycow.model.NyanCat;
import edu.ucsd.flappycow.model.Obstacle;
import edu.ucsd.flappycow.model.IPlayableCharacter;
import edu.ucsd.flappycow.model.Toast;

public class GameView extends SurfaceView {

    /** Milliseconds for game timer tick */
    public static final long UPDATE_INTERVAL = 50;        // = 20 FPS

    private Timer timer = new Timer();
    private TimerTask timerTask;

    /** The surfaceholder needed for the canvas drawing */
    private SurfaceHolder holder;
    private GameActivity gameActivity;
    volatile private boolean paused = true;
    private GameFacade gameFacade;


    public GameView(Context context) {
        super(context);
        this.gameActivity = (GameActivity) context;
        setFocusable(true);
        holder = getHolder();
        gameFacade = new GameFacade(this);
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
                gameFacade.run();
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
                && !gameFacade.isPlayerDead()) { // No support for dead players
            gameFacade.onTouch((int)event.getX(), (int)event.getY());
        }
        return true;
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

    public void pause() {
        stopTimer();
        paused = true;
    }

    public void resume() {
        paused = false;
        startTimer();
    }


    public void sendMessage() {
        getGameActivity().getHandler().sendMessage(Message.obtain(getGameActivity().getHandler(), 1, R.string.toast_achievement_toastification, ApplicationConstants.SHOW_TOAST));
    }

    public GameActivity getGameActivity() {
        return this.gameActivity;
    }

    public void gameActivityIncreaseCoin(){
        this.getGameActivity().increaseCoin();
    }

    public void increasePoints(){
        this.getGameActivity().increasePoints();
    }

    public void decreasePoints(){
        this.getGameActivity().decreasePoints();
    }

    public int getWidthPixels(){
        return this.getGameActivity().getWidthPixels();
    }

    public int getHeightPixels(){
        return this.getGameActivity().getHeightPixels();
    }

    public SurfaceHolder getSurfaceViewHolder() {
        return holder;
    }

    public int getSpeedX() {
        // 16 @ 720x1280 px
        int speedDefault = this.getWidth() / 45;

        // 1,2 every 4 points @ 720x1280 px
        int speedIncrease = (int) (this.getWidth() / 600f * ( getAccomplishmentBoxPoints() / 4));

        int speed = speedDefault + speedIncrease;

        return Math.min(speed, 2 * speedDefault);
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public static long getUpdateInterval() {
        return UPDATE_INTERVAL;
    }

    public void drawOnce() {
        gameFacade.drawOnce();
    }

    public IPlayableCharacter getPlayer() {
        return gameFacade.getPlayer();
    }

    public void revive() {
        gameFacade.revive();
    }

    public int getAccomplishmentBoxPoints(){
        return getGameActivity().getAccomplishmentBoxPoints();
    }

    public void gameOver(){
        getGameActivity().gameOver();
    }

    public GameOverDialog getGameOverDialog() {
        return getGameActivity().getGameOverDialog();
    }

    public void setMusicShouldPlay(Boolean musicShouldPlay){
        getGameActivity().musicShouldPlay = musicShouldPlay;
    }

    public void setAchievement_toastification(){
        getGameActivity().setAchievement_toastification();
    }

    public int getCoins(){
        return getGameActivity().getCoins();
    }

    public void increaseCoin(){
        getGameActivity().increaseCoin();
    }

    public void increaseNumberOfRevive(){
        getGameActivity().increaseNumberOfRevive();
    }

    public String onScreenCoinText(){
        return getGameActivity().onScreenCoinText();
    }

    public String onScreenScoreText(){
        return getGameActivity().onScreenScoreText();
    }

    public void startMusicPlayer(){
        getGameActivity().startMusicPlayer();
    }
}
