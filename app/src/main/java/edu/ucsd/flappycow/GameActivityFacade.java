package edu.ucsd.flappycow;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.presenter.GameActivityAchievementBoxPresenter;
import edu.ucsd.flappycow.view.AchievementBox;
import edu.ucsd.flappycow.view.AchievementBoxUpdate;
import edu.ucsd.flappycow.view.GameActivity;
import edu.ucsd.flappycow.view.GameActivityHandler;
import edu.ucsd.flappycow.view.GameOverDialog;
import edu.ucsd.flappycow.view.GameView;

public class GameActivityFacade {
    private GameView gameView;
    private GameActivity gameActivity;
    private AchievementBox accomplishmentBox;
    private GameOverDialog gameOverDialog;
    private GameActivityHandler handler;

    public GameActivityFacade(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
        this.gameView = new GameView(gameActivity);
        accomplishmentBox = new AchievementBox();
        gameOverDialog = new GameOverDialog(gameActivity);
        handler = new GameActivityHandler(gameActivity);

        if (gameActivity.getGameOverCounter() % gameActivity.getGamesPerAd() == 0) {
            setupAd();
        }


//        gameActivityAchievementBoxPresenter = new GameActivityAchievementBoxPresenter(this, accomplishmentBox);
//        gameActivityAchievementBoxPresenter.register(accomplishmentBox);
    }

    public void gameViewPause() {
        gameView.pause();
    }

    public void drawOnce() {
        gameView.drawOnce();
    }

    public void gameOver() {
        if (gameActivity.getGameOverCounter() % gameActivity.getGamesPerAd() == 0) {
            handler.sendMessage(Message.obtain(handler, ApplicationConstants.SHOW_AD));
        } else {
            handler.sendMessage(Message.obtain(handler, ApplicationConstants.SHOW_GAME_OVER_DIALOG));
        }
    }

    public void increaseCoinSideEffect() {
        if (gameActivity.getCoins() >= 50 && !accomplishmentBox.isAchievement_50_coins()) {
            accomplishmentBox.setAchievement_50_coins(true);
//            notify(new AchievementBoxUpdate(ApplicationConstants.ACHIEVEMENT_50_COINS, "true"));
            handler.sendMessage(Message.obtain(handler, 1, R.string.toast_achievement_50_coins, ApplicationConstants.SHOW_TOAST));
        }
    }

    public void increasePoints() {
        accomplishmentBox.setPoints(accomplishmentBox.getPoints()+1);
//        notify(new AchievementBoxUpdate(ApplicationConstants.POINTS, Integer.toString(accomplishmentBox.getPoints()+1)));

        gameView.getPlayer().upgradeBitmap(accomplishmentBox.getPoints());

        if (accomplishmentBox.getPoints() >= AchievementBox.getBronzePoints()) {
            if (!accomplishmentBox.isAchievement_bronze()) {
                accomplishmentBox.setAchievement_bronze(true);
//                notify(new AchievementBoxUpdate(ApplicationConstants.ACHIEVEMENT_BRONZE, "true"));
                handler.sendMessage(Message.obtain(handler, ApplicationConstants.SHOW_TOAST, R.string.toast_achievement_bronze, ApplicationConstants.SHOW_TOAST));
            }

            if (accomplishmentBox.getPoints() >= AchievementBox.getSilverPoints()) {
                if (!accomplishmentBox.isAchievement_silver()) {
                    accomplishmentBox.setAchievement_silver(true);
//                    notify(new AchievementBoxUpdate(ApplicationConstants.ACHIEVEMENT_SILVER, "true"));
                    handler.sendMessage(Message.obtain(handler, ApplicationConstants.SHOW_TOAST, R.string.toast_achievement_silver, ApplicationConstants.SHOW_TOAST));
                }

                if (accomplishmentBox.getPoints() >= AchievementBox.getGoldPoints()) {
                    if (!accomplishmentBox.isAchievement_gold()) {
                        accomplishmentBox.setAchievement_gold(true);
//                        notify(new AchievementBoxUpdate(ApplicationConstants.ACHIEVEMENT_GOLD, "true"));
                        handler.sendMessage(Message.obtain(handler, ApplicationConstants.SHOW_TOAST, R.string.toast_achievement_gold, ApplicationConstants.SHOW_TOAST));
                    }
                }
            }
        }
    }

    public void decreasePoints() {
        accomplishmentBox.setPoints(accomplishmentBox.getPoints()-1);
//        notify(new AchievementBoxUpdate(ApplicationConstants.POINTS, Integer.toString(accomplishmentBox.getPoints()-1)));
    }

    private void setupAd() {
        MobileAds.initialize(gameActivity, initializationStatus -> { /* no-op */ });

        String adUnitId = gameActivity.getResources().getString(R.string.ad_unit_id);

        // Make sure only adds appropriate for children of all ages are displayed.
        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "G");

        AdRequest adRequest = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();

        InterstitialAd.load(gameActivity, adUnitId, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                Log.i("Ads", "Ad was loaded.");
                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        handler.sendMessage(Message.obtain(handler, ApplicationConstants.SHOW_GAME_OVER_DIALOG));
                    }
                });
                gameActivity.setInterstitial(interstitialAd);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                if (loadAdError.getCode() == AdRequest.ERROR_CODE_NO_FILL) {
                    Log.i("Ads", "No ad was available.");
                } else {
                    Log.i("Ads", "Ad failed to load.");
                }
                Log.d("Ads", loadAdError.toString());
                gameActivity.setInterstitial(null);
            }
        });
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public GameActivity getGameActivity() {
        return gameActivity;
    }

    public void setGameActivity(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    public AchievementBox getAccomplishmentBox() {
        return accomplishmentBox;
    }

    public void setAccomplishmentBox(AchievementBox accomplishmentBox) {
        this.accomplishmentBox = accomplishmentBox;
    }

    public GameOverDialog getGameOverDialog() {
        return gameOverDialog;
    }

    public void setGameOverDialog(GameOverDialog gameOverDialog) {
        this.gameOverDialog = gameOverDialog;
    }

    public GameActivityHandler getHandler() {
        return handler;
    }

    public void setHandler(GameActivityHandler handler) {
        this.handler = handler;
    }
}