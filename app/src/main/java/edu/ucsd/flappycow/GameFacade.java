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

public
class GameFacade {


    private GameView gameView;
    private List<ObstaclePresenter> obstaclePresenters;
    private TutorialPresenter tutorialPresenter;
    private ButtonPresenter buttonPresenter;
    private Map<String, GroundPresenter> groundPresenterMap;
    private PlayableCharacterPresenter playableCharacterPresenter;
    private List<PowerUpPresenter> powerUpPresenters = new ArrayList<>();
    public GameFacade(GameView gameView) {
        this.gameView = gameView;
        obstaclePresenters = new ArrayList<>();
        tutorialPresenter = new TutorialPresenter(this);
        buttonPresenter = new ButtonPresenter(this);
        groundPresenterMap = new HashMap<>();
        groundPresenterMap.put(ApplicationConstants.BACKGROUND, new GroundPresenter(this, ApplicationConstants.BACKGROUND));
        groundPresenterMap.put(ApplicationConstants.FRONTGROUND, new GroundPresenter(this, ApplicationConstants.FRONTGROUND));
        playableCharacterPresenter = new CowPresenter(this, ApplicationConstants.COW);
        powerUpPresenters = new ArrayList<>();
    }

    public void run() {
        checkPasses();
        checkOutOfRange();
        checkCollision(this.getGameActivity().getResources().getDisplayMetrics().heightPixels);
        createObstacle();
        move();
        draw();
    }

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

    public void drawCanvas(Canvas canvas, boolean drawPlayer) {
//        background.draw(canvas);
        groundPresenterMap.get(ApplicationConstants.BACKGROUND).draw(canvas);
//        for (ObstaclePresenter r : obstaclePresenters) {
//            r.draw(canvas);
//        }
        for (PowerUpPresenter p : powerUpPresenters) {
            p.draw(canvas);
        }
        if (drawPlayer) {
//            player.draw(canvas);
            playableCharacterPresenter.draw(canvas);
        }
//        frontground.draw(canvas);
        groundPresenterMap.get(ApplicationConstants.FRONTGROUND).draw(canvas);
        buttonPresenter.draw(canvas);

        // Score Text
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(getScoreTextMetrics());
        canvas.drawText(getGameActivity().getResources().getString(R.string.onscreen_score_text) + " " + getGameActivity().getAccomplishmentBox().getPoints()
                        + " / " + getGameActivity().getResources().getString(R.string.onscreen_coin_text) + " " + getGameActivity().getCoins(),
                0, getScoreTextMetrics(), paint);
    }

    public int getScoreTextMetrics() {
        return (int) (getHeight() / 21.0f);
    }

    public void changeToSick() {
        playableCharacterPresenter.wearMask();
    }

    public void changeToNyanCat() {
        getGameActivity().getAccomplishmentBox().setAchievement_toastification(true);
        gameView.sendMessage();
        IPlayableCharacter tmp = this.playableCharacterPresenter.getPlayer();
        this.playableCharacterPresenter = new PlayableCharacterPresenter(this, ApplicationConstants.NYAN_CAT);
        this.playableCharacterPresenter.setX(tmp.getX());
        this.playableCharacterPresenter.setY(tmp.getY());
        this.playableCharacterPresenter.setSpeedX(tmp.getSpeedX());
        this.playableCharacterPresenter.setSpeedY(tmp.getSpeedY());

        getGameActivity().musicShouldPlay = true;
        GameActivity.musicPlayer.start();
    }

    public void createObstacle() {
        if (obstaclePresenters.size() < 1) {
            obstaclePresenters.add(new ObstaclePresenter(this));
        }
    }

    public void setupRevive() {
//        gameActivity.gameOverDialog.hide();
        playableCharacterPresenter.setY(getHeight() / 2 - playableCharacterPresenter.getPlayer().getWidth() / 2);
        playableCharacterPresenter.setX(getWidth() / 6);
//        obstaclePresenters.clear();
        powerUpPresenters.clear();
//        player.revive();
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

    private void move() {
        for (ObstaclePresenter o : obstaclePresenters) {
            o.setSpeedX(-getSpeedX());
            o.move();
        }
        for (PowerUpPresenter p : powerUpPresenters) {
            p.move();
        }
        groundPresenterMap.get(ApplicationConstants.BACKGROUND).setSpeedX(-getSpeedX() / 2);
        groundPresenterMap.get(ApplicationConstants.BACKGROUND).move();

        groundPresenterMap.get(ApplicationConstants.FRONTGROUND).setSpeedX(-getSpeedX() * 4 / 3);
        groundPresenterMap.get(ApplicationConstants.FRONTGROUND).move();
        buttonPresenter.move();
        playableCharacterPresenter.move();
    }

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

    public void gameOver() {
        gameView.pause();
        playerDeadFall();
        getGameActivity().gameOver();
    }

    public void revive() {
        getGameActivity().numberOfRevive++;

        // This needs to run another thread, so the dialog can close.
        new Thread(this::setupRevive).start();
    }

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

    private void createPowerUp() {
//        powerUpPresenter.createPowerUp(gameActivity.accomplishmentBox.getPoints());
//        // Toast
        if (getGameActivity().getAccomplishmentBox().getPoints() >= Toast.POINTS_TO_TOAST /*&& powerUps.size() < 1*/ && !(playableCharacterPresenter.getPlayer() instanceof NyanCat)) {
            // If no powerUp is present and you have more than / equal 42 points
            if (getGameActivity().getAccomplishmentBox().getPoints() == Toast.POINTS_TO_TOAST) {    // First time 100 % chance
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
    }

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
//                tutorialIsShown = false;
            gameView.resume();
//                this.player.onTap();
            this.playableCharacterPresenter.onTap();
        } else if (gameView.isPaused()) {
            gameView.resume();
        } else if (buttonPresenter.isTouching(x, y) && !gameView.isPaused()) {
            gameView.pause();
        } else {
//                this.player.onTap();
            this.playableCharacterPresenter.onTap();
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
        getGameActivity().increaseCoin();
    }

    public void increasePoints(){
        getGameActivity().increasePoints();
    }
    public void decreasePoints(){
        getGameActivity().decreasePoints();
    }

    public boolean isPlayerDead() {
        return playableCharacterPresenter.isDead();
    }

}
