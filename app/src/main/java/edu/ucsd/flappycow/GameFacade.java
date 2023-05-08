package edu.ucsd.flappycow;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

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
import edu.ucsd.flappycow.presenter.GameActivityAchievementBoxPresenter;
import edu.ucsd.flappycow.presenter.GroundPresenter;
import edu.ucsd.flappycow.presenter.ObstaclePresenter;
import edu.ucsd.flappycow.presenter.PlayableCharacterPresenter;
import edu.ucsd.flappycow.presenter.PowerUpPresenter;
import edu.ucsd.flappycow.presenter.ToastPresenter;
import edu.ucsd.flappycow.presenter.TutorialPresenter;
import edu.ucsd.flappycow.presenter.VirusPresenter;
import edu.ucsd.flappycow.view.AchievementBox;
import edu.ucsd.flappycow.view.GameActivity;
import edu.ucsd.flappycow.view.GameOverDialog;
import edu.ucsd.flappycow.view.GameView;
import edu.ucsd.flappycow.view.MainActivity;

public
class GameFacade {


    private GameView gameView;
    private List<ObstaclePresenter> obstaclePresenters;
    private TutorialPresenter tutorialPresenter;
    private ButtonPresenter buttonPresenter;
    private Map<String, GroundPresenter> groundPresenterMap;
    GameOverDialog gameOverDialog;
    public static MediaPlayer musicPlayer = null;
    private static int gameOverCounter = 1;
    private static final int GAMES_PER_AD = 3;


    private PlayableCharacterPresenter playableCharacterPresenter;
    AchievementBox accomplishmentBox;

    private GameActivityAchievementBoxPresenter gameActivityAchievementBoxPresenter;

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

    public void onCreate(){
        accomplishmentBox = new AchievementBox();
        gameActivityAchievementBoxPresenter = new GameActivityAchievementBoxPresenter(getGameActivity(), accomplishmentBox);
        //view = new GameView(this);
        gameOverDialog = new GameOverDialog(getGameActivity());
        //handler = new GameActivityHandler(this);
        getGameActivity().setContentView(gameView);
        initMusicPlayer();
        getGameActivity().loadCoins();
        if (gameOverCounter % GAMES_PER_AD == 0) {
            setupAd();
        }
    }
    private void setupAd() {
        MobileAds.initialize(getGameActivity(), initializationStatus -> { /* no-op */ });

        String adUnitId = getGameActivity().getResources().getString(R.string.ad_unit_id);

        // Make sure only adds appropriate for children of all ages are displayed.
        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "G");

        AdRequest adRequest = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();

        InterstitialAd.load(getGameActivity(), adUnitId, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                Log.i("Ads", "Ad was loaded.");
                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        getGameActivity().sendMessage();
                    }
                });
                getGameActivity().setInterstitial(interstitialAd);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                if (loadAdError.getCode() == AdRequest.ERROR_CODE_NO_FILL) {
                    Log.i("Ads", "No ad was available.");
                } else {
                    Log.i("Ads", "Ad failed to load.");
                }
                Log.d("Ads", loadAdError.toString());
                getGameActivity().setInterstitial(null);
            }
        });
    }
    public void initMusicPlayer() {
        if (musicPlayer == null) {
            // to avoid unnecessary reinitialisation
            musicPlayer = MediaPlayer.create(getGameActivity(), R.raw.nyan_cat_theme);
            if (musicPlayer == null) {
                return;
            }
            musicPlayer.setLooping(true);
            musicPlayer.setVolume(MainActivity.volume, MainActivity.volume);
        }
        musicPlayer.seekTo(0);    // Reset song to position 0
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
        groundPresenterMap.get(ApplicationConstants.BACKGROUND).draw(canvas);
        for (ObstaclePresenter r : obstaclePresenters) {
            r.draw(canvas);
        }
        for (PowerUpPresenter p : powerUpPresenters) {
            p.draw(canvas);
        }
        if (drawPlayer) {
            playableCharacterPresenter.draw(canvas);
        }
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
        getGameActivity().getAccomplishmentBox().setAchievement_toastification(true);
        gameView.sendMessage();
        IPlayableCharacter tmp = this.playableCharacterPresenter.getPlayer();
        playableCharacterPresenter = new PlayableCharacterPresenter(this, ApplicationConstants.NYAN_CAT);
        playableCharacterPresenter.setX(tmp.getX());
        playableCharacterPresenter.setY(tmp.getY());
        playableCharacterPresenter.setSpeedX(tmp.getSpeedX());
        playableCharacterPresenter.setSpeedY(tmp.getSpeedY());

        getGameActivity().musicShouldPlay = true;
        GameActivity.musicPlayer.start();
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
        getGameActivity().getGameOverDialog().hide();
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
        groundPresenterMap.get(ApplicationConstants.BACKGROUND).setSpeedX(-getSpeedX() / 2);
        groundPresenterMap.get(ApplicationConstants.BACKGROUND).move();

        groundPresenterMap.get(ApplicationConstants.FRONTGROUND).setSpeedX(-getSpeedX() * 4 / 3);
        groundPresenterMap.get(ApplicationConstants.FRONTGROUND).move();
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
        getGameActivity().gameOver();
    }

    public void revive() {
        getGameActivity().numberOfRevive++;

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
