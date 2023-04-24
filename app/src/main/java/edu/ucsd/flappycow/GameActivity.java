/*
 * The Game Activity
 *
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quchen>
 */

package edu.ucsd.flappycow;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.List;


public class GameActivity<T> extends Activity implements ISubjectImpl<T>{
    /**
     * Name of the SharedPreference that saves the medals
     */
    public static final String coin_save = "coin_save";

    /**
     * Key that saves the medal
     */
    public static final String coin_key = "coin_key";

    /**
     * Will play things like mooing
     */
    public static SoundPool soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

    private static final int GAMES_PER_AD = 3;

    public static int getGameOverCounter() {
        return gameOverCounter;
    }

    public static void setGameOverCounter(int gameOverCounter) {
        GameActivity.gameOverCounter = gameOverCounter;
    }

    public long getBackPressed() {
        return backPressed;
    }

    public void setBackPressed(long backPressed) {
        this.backPressed = backPressed;
    }

    public InterstitialAd getInterstitial() {
        return interstitial;
    }

    public void setInterstitial(InterstitialAd interstitial) {
        this.interstitial = interstitial;
    }

    /**
     * Counts number of played games
     */
    private static int gameOverCounter = 1;

    /**
     * Will play songs like:
     * nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan
     * nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan
     * nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan
     * nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan nyan
     * Does someone know the second verse ???
     */
    public static MediaPlayer musicPlayer = null;

    /**
     * Whether the music should play or not
     */
    public boolean musicShouldPlay = false;

    /**
     * Time interval (ms) you have to press the backbutton twice in to exit
     */
    private static final long DOUBLE_BACK_TIME = 1000;

    /**
     * Saves the time of the last backbutton press
     */
    private long backPressed;

    /**
     * To do UI things from different threads
     */
    public GameActivityHandler handler;

    /**
     * Hold all accomplishments
     */
    AchievementBox accomplishmentBox;

    /**
     * The view that handles all kind of stuff
     */
    GameView view;

    /**
     * The amount of collected coins
     */
    int coins;

    /**
     * This will increase the revive price
     */
    public int numberOfRevive = 1;

    /**
     * The dialog displayed when the game is over
     */
    GameOverDialog gameOverDialog;

    /**
     * Interstitial ad.
     */
    private InterstitialAd interstitial;

    private List<IObserver> observerList;

    @Override
    public void register(IObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(IObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notify(T data) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accomplishmentBox = new AchievementBox();
        view = new GameView(this);
        gameOverDialog = new GameOverDialog(this);
        handler = new GameActivityHandler(this);
        setContentView(view);
        initMusicPlayer();
        loadCoins();
        if (gameOverCounter % GAMES_PER_AD == 0) {
            setupAd();
        }
    }


    /**
     * Initializes the player with the nyan cat song
     * and sets the position to 0.
     */
    public void initMusicPlayer() {
        if (musicPlayer == null) {
            // to avoid unnecessary reinitialisation
            musicPlayer = MediaPlayer.create(this, R.raw.nyan_cat_theme);
            if (musicPlayer == null) {
                return;
            }
            musicPlayer.setLooping(true);
            musicPlayer.setVolume(MainActivity.volume, MainActivity.volume);
        }
        musicPlayer.seekTo(0);    // Reset song to position 0
    }

    private void loadCoins() {
        SharedPreferences saves = this.getSharedPreferences(coin_save, 0);
        this.coins = saves.getInt(coin_key, 0);
    }

    /**
     * Pauses the view and the music
     */
    @Override
    protected void onPause() {
        view.pause();
        if (musicPlayer != null && musicPlayer.isPlaying()) {
            musicPlayer.pause();
        }
        super.onPause();
    }

    /**
     * Resumes the view (but waits the view waits for a tap)
     * and starts the music if it should be running.
     * Also checks whether the Google Play Services are available.
     */
    @Override
    protected void onResume() {
        view.drawOnce();
        if (musicPlayer != null && musicShouldPlay) {
            musicPlayer.start();
        }
        super.onResume();
    }

    /**
     * Prevent accidental exits by requiring a double press.
     */
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - backPressed < DOUBLE_BACK_TIME) {
            super.onBackPressed();
        } else {
            backPressed = System.currentTimeMillis();
            Toast.makeText(this, getResources().getString(R.string.on_back_press), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Sends the handler the command to show the GameOverDialog.
     * Because it needs an UI thread.
     */
    public void gameOver() {
        if (gameOverCounter % GAMES_PER_AD == 0) {
            handler.sendMessage(Message.obtain(handler, GameActivityHandler.SHOW_AD));
        } else {
            handler.sendMessage(Message.obtain(handler, GameActivityHandler.SHOW_GAME_OVER_DIALOG));
        }

    }

    public void increaseCoin() {
        this.coins++;
        if (coins >= 50 && !accomplishmentBox.achievement_50_coins) {
//            accomplishmentBox.achievement_50_coins = true;
            accomplishmentBox.setAchievement_50_coins(true);
            handler.sendMessage(Message.obtain(handler, 1, R.string.toast_achievement_50_coins, GameActivityHandler.SHOW_TOAST));
        }
    }

    public void decreaseCoin() {
        this.coins--;
    }

    /**
     * What should happen, when an obstacle is passed?
     */
    public void increasePoints() {
//        accomplishmentBox.points++;

        accomplishmentBox.setPoints(accomplishmentBox.getPoints()+1);

        this.view.getPlayer().upgradeBitmap(accomplishmentBox.points);

        if (accomplishmentBox.points >= AchievementBox.BRONZE_POINTS) {
            if (!accomplishmentBox.achievement_bronze) {
//                accomplishmentBox.achievement_bronze = true;
                accomplishmentBox.setAchievement_bronze(true);
                handler.sendMessage(Message.obtain(handler, GameActivityHandler.SHOW_TOAST, R.string.toast_achievement_bronze, GameActivityHandler.SHOW_TOAST));
            }

            if (accomplishmentBox.points >= AchievementBox.SILVER_POINTS) {
                if (!accomplishmentBox.achievement_silver) {
//                    accomplishmentBox.achievement_silver = true;
                    accomplishmentBox.setAchievement_silver(true);
                    handler.sendMessage(Message.obtain(handler, GameActivityHandler.SHOW_TOAST, R.string.toast_achievement_silver, GameActivityHandler.SHOW_TOAST));
                }

                if (accomplishmentBox.points >= AchievementBox.GOLD_POINTS) {
                    if (!accomplishmentBox.achievement_gold) {
//                        accomplishmentBox.achievement_gold = true;
                        accomplishmentBox.setAchievement_gold(true);
                        handler.sendMessage(Message.obtain(handler, GameActivityHandler.SHOW_TOAST, R.string.toast_achievement_gold, GameActivityHandler.SHOW_TOAST));
                    }
                }
            }
        }
    }

    public void decreasePoints() {
//        accomplishmentBox.points--;
        accomplishmentBox.setPoints(accomplishmentBox.getPoints()-1);
    }

    /**
     * Shows the GameOverDialog when a message with code 0 is received.
     */
    

    private void setupAd() {
        MobileAds.initialize(this, initializationStatus -> { /* no-op */ });

        String adUnitId = getResources().getString(R.string.ad_unit_id);

        // Make sure only adds appropriate for children of all ages are displayed.
        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "G");

        AdRequest adRequest = new AdRequest.Builder()
            .addNetworkExtrasBundle(AdMobAdapter.class, extras)
            .build();

        InterstitialAd.load(this, adUnitId, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                Log.i("Ads", "Ad was loaded.");
                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        handler.sendMessage(Message.obtain(handler, GameActivityHandler.SHOW_GAME_OVER_DIALOG));
                    }
                });
                GameActivity.this.interstitial = interstitialAd;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                if (loadAdError.getCode() == AdRequest.ERROR_CODE_NO_FILL) {
                    Log.i("Ads", "No ad was available.");
                } else {
                    Log.i("Ads", "Ad failed to load.");
                }
                Log.d("Ads", loadAdError.toString());
                GameActivity.this.interstitial = null;
            }
        });
    }
}
