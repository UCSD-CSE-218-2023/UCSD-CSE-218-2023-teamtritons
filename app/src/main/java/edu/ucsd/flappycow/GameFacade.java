package edu.ucsd.flappycow;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Message;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.enums.Ground;
import edu.ucsd.flappycow.enums.PlayableCharacter;
import edu.ucsd.flappycow.model.IPlayableCharacter;
import edu.ucsd.flappycow.model.NyanCat;
import edu.ucsd.flappycow.model.Toast;
import edu.ucsd.flappycow.presenter.ButtonPresenter;
import edu.ucsd.flappycow.presenter.CoinPresenter;
import edu.ucsd.flappycow.presenter.CowPresenter;
import edu.ucsd.flappycow.presenter.GroundPresenter;
import edu.ucsd.flappycow.presenter.ObstaclePresenter;
import edu.ucsd.flappycow.presenter.PlayableCharacterPresenter;
import edu.ucsd.flappycow.presenter.PowerUpPresenter;
import edu.ucsd.flappycow.presenter.ToastPresenter;
import edu.ucsd.flappycow.presenter.TutorialPresenter;
import edu.ucsd.flappycow.presenter.VirusPresenter;
import edu.ucsd.flappycow.view.GameActivity;
import edu.ucsd.flappycow.view.GameView;
import static edu.ucsd.flappycow.util.Contract.invariant;

public
class GameFacade {


    private GameView gameView;
    private List<ObstaclePresenter> obstaclePresenters;
    private TutorialPresenter tutorialPresenter;
    private ButtonPresenter buttonPresenter;
    private Map<Ground, GroundPresenter> groundPresenterMap;
    private PlayableCharacterPresenter playableCharacterPresenter;
    private List<PowerUpPresenter> powerUpPresenters = new ArrayList<>();
    public GameFacade(GameView gameView) {
        this.gameView = gameView;
        obstaclePresenters = new ArrayList<>();
        tutorialPresenter = new TutorialPresenter(this);
        buttonPresenter = new ButtonPresenter(this);
        groundPresenterMap = new HashMap<>();
        groundPresenterMap.put(Ground.BACKGROUND, new GroundPresenter(this, Ground.BACKGROUND));
        groundPresenterMap.put(Ground.FRONTGROUND, new GroundPresenter(this, Ground.FRONTGROUND));
        playableCharacterPresenter = new CowPresenter(this, PlayableCharacter.COW);
        powerUpPresenters = new ArrayList<>();
    }

    /**
     * content of the timertask
     */
    public void run() {
        checkPasses();
        checkOutOfRange();
        checkCollision(this.getHeightPixels());
        createObstacle();
        move();
        draw();
    }

    /**
     * Draw Tutorial
     */
    public void showTutorial() {
        playableCharacterPresenter.move();
        buttonPresenter.move();
        while (!getSurfaceViewHolder().getSurface().isValid()) {
            /*wait*/
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Canvas canvas = getCanvas();
        drawCanvas(canvas, true);

        tutorialPresenter.move();
        tutorialPresenter.draw(canvas);
        getSurfaceViewHolder().unlockCanvasAndPost(canvas);
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

    /**
     * Draws all gameobjects on the surface
     */
    private void draw() {
        while (!getSurfaceViewHolder().getSurface().isValid()) {
            /*wait*/
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Canvas canvas = getCanvas();

        drawCanvas(canvas, true);

        getSurfaceViewHolder().unlockCanvasAndPost(canvas);
    }

    /**
     * Draws everything normal,
     * except the player will only be drawn, when the parameter is true
     *
     * @param drawPlayer
     */
    public void drawCanvas(Canvas canvas, boolean drawPlayer) {
        groundPresenterMap.get(Ground.BACKGROUND).draw(canvas);
        for (ObstaclePresenter r : obstaclePresenters) {
            r.draw(canvas);
        }
        for (PowerUpPresenter p : powerUpPresenters) {
            p.draw(canvas);
        }
        if (drawPlayer) {
            playableCharacterPresenter.draw(canvas);
        }
        groundPresenterMap.get(Ground.FRONTGROUND).draw(canvas);
        buttonPresenter.draw(canvas);

        // Score Text
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(getScoreTextMetrics());
//        canvas.drawText(getGameActivity().getResources().getString(R.string.onscreen_score_text) + " " + getGameActivity().getAccomplishmentBox().getPoints()
//        + " / " + getGameActivity().getResources().getString(R.string.onscreen_coin_text) + " " + getGameActivity().getCoins(),
//                0, getScoreTextMetrics(), paint);
        canvas.drawText(gameView.onScreenScoreText() + " " +         gameView.getAccomplishmentBoxPoints()
                        + " / " + gameView.onScreenCoinText() + " " + gameView.getCoins(),
                0, getScoreTextMetrics(), paint);
    }

    /**
     * A value for the position and size of the onScreen score Text
     */
    public int getScoreTextMetrics() {
        return (int) (getHeight() / 21.0f);
    }

    public void changeToSick() {
        playableCharacterPresenter.wearMask();
    }

    /**
     * Changes the player to Nyan Cat
     */
    public void changeToNyanCat() {
        gameView.setAchievement_toastification();
//        getGameActivity().getAccomplishmentBox().setAchievement_toastification(true);
        gameView.sendMessage();
        IPlayableCharacter tmp = this.playableCharacterPresenter.getPlayer();
        playableCharacterPresenter = new PlayableCharacterPresenter(this, PlayableCharacter.NYAN_CAT);
        playableCharacterPresenter.setX(tmp.getX());
        playableCharacterPresenter.setY(tmp.getY());
        playableCharacterPresenter.setSpeedX(tmp.getSpeedX());
        playableCharacterPresenter.setSpeedY(tmp.getSpeedY());
        gameView.setMusicShouldPlay(true);
//        getGameActivity().musicShouldPlay = true;
        gameView.startMusicPlayer();
//        GameActivity.musicPlayer.start();
    }

    /**
     * if no obstacle is present a new one is created
     */
    public void createObstacle() {
        if (obstaclePresenters.size() < 1) {
            obstaclePresenters.add(new ObstaclePresenter(this));
        }
    }

    /**
     * Sets the player into startposition
     * Removes obstacles.
     * Let's the character blink a few times.
     */
    public void setupRevive() {
        gameView.getGameOverDialog().hide();
//        getGameActivity().getGameOverDialog().hide();
        playableCharacterPresenter.setY(getHeight() / 2 - playableCharacterPresenter.getPlayer().getWidth() / 2);
        playableCharacterPresenter.setX(getWidth() / 6);
        obstaclePresenters.clear();
        powerUpPresenters.clear();
        playableCharacterPresenter.revive();
        for (int i = 0; i < 6; ++i) {
            while (!getSurfaceViewHolder().getSurface().isValid()) {/*wait*/}
            Canvas canvas = getCanvas();
            drawCanvas(canvas, i % 2 == 0);
            getSurfaceViewHolder().unlockCanvasAndPost(canvas);
            // sleep
            try {
                Thread.sleep(gameView.getUpdateInterval() * 6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameView.resume();
    }

    /**
     * Update sprite movements
     */
    private void move() {
        for (ObstaclePresenter o : obstaclePresenters) {
            o.setSpeedX(-getSpeedX());
            o.move();
        }
        for (PowerUpPresenter p : powerUpPresenters) {
            p.move();
        }
        groundPresenterMap.get(Ground.BACKGROUND).setSpeedX(-getSpeedX() / 2);
        groundPresenterMap.get(Ground.BACKGROUND).move();

        groundPresenterMap.get(Ground.FRONTGROUND).setSpeedX(-getSpeedX() * 4 / 3);
        groundPresenterMap.get(Ground.FRONTGROUND).move();
        buttonPresenter.move();
        playableCharacterPresenter.move();
    }

    /**
     * Checks collisions and performs the action
     */
    private void checkCollision(int heightPixels) {
        for (ObstaclePresenter o : obstaclePresenters) {
            if (o.isColliding(playableCharacterPresenter.getPlayer())) {
                o.onCollision();
                gameOver();
            }
        }

        for (int i = 0; i < powerUpPresenters.size(); i++) {
            if (this.powerUpPresenters.get(i).isColliding()) {
                this.powerUpPresenters.get(i).onCollision();
                this.powerUpPresenters.remove(i);
                i--;
            }
        }
        if (playableCharacterPresenter.isTouchingEdge()) {
            gameOver();
        }
    }

    /**
     * Let's the player fall down dead, makes sure the runcycle stops
     * and invokes the next method for the dialog and stuff.
     */
    public void gameOver() {
        gameView.pause();
        playerDeadFall();
        gameView.gameOver();
//        getGameActivity().gameOver();
    }

    public void revive() {
        gameView.increaseNumberOfRevive();
//        getGameActivity().numberOfRevive++;

        // This needs to run another thread, so the dialog can close.
        new Thread(this::setupRevive).start();
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
        for (int i = 0; i < powerUpPresenters.size(); i++) {
            if (this.powerUpPresenters.get(i).isOutOfRange()) {
                this.powerUpPresenters.remove(i);
                i--;
            }
        }
    }

    /**
     * Creates a toast with a certain chance
     */
    private void createPowerUp() {
//        // Toast
//        if (getGameActivity().getAccomplishmentBox().getPoints() >= Toast.POINTS_TO_TOAST /*&& powerUps.size() < 1*/ && !(playableCharacterPresenter.getPlayer() instanceof NyanCat)) {
        if (gameView.getAccomplishmentBoxPoints() >= Toast.POINTS_TO_TOAST /*&& powerUps.size() < 1*/ && !(playableCharacterPresenter.getPlayer() instanceof NyanCat)) {

            // If no powerUp is present and you have more than / equal 42 points
//            if (getGameActivity().getAccomplishmentBox().getPoints() == Toast.POINTS_TO_TOAST) {    // First time 100 % chance
            if(gameView.getAccomplishmentBoxPoints() == Toast.POINTS_TO_TOAST){
                powerUpPresenters.add(new ToastPresenter(this));
            } else if (Math.random() * 100 < 33) {    // 33% chance
                powerUpPresenters.add(new ToastPresenter(this));
            }
        }

        if ((powerUpPresenters.size() < 1) && (Math.random() * 100 < 20)) {
            // If no powerUp is present and 20% chance
            powerUpPresenters.add(new CoinPresenter(this));
        }

        if ((powerUpPresenters.size() < 1) && (Math.random() * 100 < 10)) {
            // If no powerUp is present and 10% chance (if also no coin)
            powerUpPresenters.add(new VirusPresenter(this));
        }

        invariant(powerUpPresenters.size()>=0, "Size of powerUpPresenters is always greater than or equal to 0");
    }

    /**
     * Let the player fall to the ground
     */
    private void playerDeadFall() {
        playableCharacterPresenter.dead();
        do {
            playableCharacterPresenter.move();
            draw();
            // sleep
            try {
                Thread.sleep(gameView.getUpdateInterval() / 4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public void onTouch(int x, int y) {
        if (tutorialPresenter.isTutorialIsShown()) {
            // dismiss tutorial
            tutorialPresenter.setTutorialIsShown(false);
            gameView.resume();
            playableCharacterPresenter.onTap();
        } else if (gameView.isPaused()) {
            gameView.resume();
        } else if (buttonPresenter.isTouching(x, y) && !gameView.isPaused()) {
            gameView.pause();
        } else {
            playableCharacterPresenter.onTap();
        }
    }

    public IPlayableCharacter getPlayer() {
        return playableCharacterPresenter.getPlayer();
    }

    public Canvas getCanvas() {
        return gameView.getCanvas();
    }

    public SurfaceHolder getSurfaceViewHolder() {
        return gameView.getSurfaceViewHolder();
    }

    public int getHeightPixels() {
        return gameView.getHeightPixels();
    }

    public int getWidthPixels() {
        return gameView.getWidthPixels();
    }

    public int getSpeedX() {
        return gameView.getSpeedX();
    }

    public GameActivity getGameActivity() {
        return gameView.getGameActivity();
    }

    public int getWidth() {
        return gameView.getWidth();
    }

    public int getHeight() {
        return gameView.getHeight();
    }
    public void gameActivityIncreaseCoin(){
        gameView.increaseCoin();
    }

    public void increasePoints(){
        gameView.increasePoints();
    }
    public void decreasePoints(){
        gameView.decreasePoints();
    }

    public boolean isPlayerDead() {
        return playableCharacterPresenter.isDead();
    }

}
