/**
 * The dialog shown when the game is over
 *
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quchen>
 */

package edu.ucsd.flappycow.view;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.util.IObserver;
import edu.ucsd.flappycow.util.ISubjectImpl;

public class GameOverDialog extends Dialog {
    public static final int REVIVE_PRICE = 5;

    /**
     * Name of the SharedPreference that saves the score
     */
    public static final String score_save_name = "score_save";

    /**
     * Key that saves the score
     */
    public static final String best_score_key = "score";

    /**
     * The game that invokes this dialog
     */
    private GameActivity gameActivity;

    private TextView tvCurrentScoreVal;
    private TextView tvBestScoreVal;

    private ImageView imageView;

    private ISubjectImpl<GameOverUpdate> GameButtonHandlerSub = new GameActivityHandlerSubjectImpl<>();


    public GameOverDialog(GameActivity gameActivity) {
        super(gameActivity);
        this.gameActivity = gameActivity;
        this.setContentView(R.layout.gameover);
        this.setCancelable(false);

        tvCurrentScoreVal = (TextView) findViewById(R.id.tv_current_score_value);
        tvBestScoreVal = (TextView) findViewById(R.id.tv_best_score_value);
        imageView = (ImageView) findViewById(R.id.medal);

        IObserver score = new Score();
        GameButtonHandlerSub.register(score);
        IObserver medals = new Medals(this.gameActivity);
        GameButtonHandlerSub.register(medals);
    }

    public void init() {
        Button okButton = (Button) findViewById(R.id.b_ok);

        okButton.setOnClickListener(this::onOKClick);

        Button reviveButton = (Button) findViewById(R.id.b_revive);

        reviveButton.setText(gameActivity.getReviveButtonText()
            + " " + REVIVE_PRICE * gameActivity.getNumberOfRevive() + " "
            + gameActivity.getCoinsText());
        reviveButton.setOnClickListener(this::onReviveClick);
        if (gameActivity.getCoins() < REVIVE_PRICE * gameActivity.getNumberOfRevive()) {
            reviveButton.setClickable(false);
        } else {
            reviveButton.setClickable(true);
        }

       GameButtonHandlerSub.notify(new GameOverUpdate(this.gameActivity, tvCurrentScoreVal, tvBestScoreVal, ApplicationConstants.SCORE));
       GameButtonHandlerSub.notify(new GameOverUpdate(this.gameActivity, imageView, ApplicationConstants.MEDALS));

    }

//    private void manageScore() {
//        SharedPreferences saves = gameActivity.getSharedPreferences(score_save_name, 0);
//        int oldPoints = saves.getInt(best_score_key, 0);
//        if (gameActivity.accomplishmentBox.points > oldPoints) {
//            // Save new highscore
//            SharedPreferences.Editor editor = saves.edit();
//            editor.putInt(best_score_key, gameActivity.accomplishmentBox.points);
//            tvBestScoreVal.setTextColor(Color.RED);
//            editor.apply();
//        }
//        tvCurrentScoreVal.setText("" + gameActivity.accomplishmentBox.points);
//        tvBestScoreVal.setText("" + oldPoints);
//    }

//    private void manageMedals() {
//        SharedPreferences MEDAL_SAVE = gameActivity.getSharedPreferences(MainActivity.MEDAL_SAVE, 0);
//        int medal = MEDAL_SAVE.getInt(MainActivity.MEDAL_KEY, 0);
//
//        SharedPreferences.Editor editor = MEDAL_SAVE.edit();
//
//        if (gameActivity.accomplishmentBox.achievement_gold) {
//            ((ImageView) findViewById(R.id.medal)).setImageBitmap(Util.getScaledBitmapAlpha8(gameActivity, R.drawable.gold));
//            if (medal < 3) {
//                editor.putInt(MainActivity.MEDAL_KEY, 3);
//            }
//        } else if (gameActivity.accomplishmentBox.achievement_silver) {
//            ((ImageView) findViewById(R.id.medal)).setImageBitmap(Util.getScaledBitmapAlpha8(gameActivity, R.drawable.silver));
//            if (medal < 2) {
//                editor.putInt(MainActivity.MEDAL_KEY, 2);
//            }
//        } else if (gameActivity.accomplishmentBox.achievement_bronze) {
//            ((ImageView) findViewById(R.id.medal)).setImageBitmap(Util.getScaledBitmapAlpha8(gameActivity, R.drawable.bronce));
//            if (medal < 1) {
//                editor.putInt(MainActivity.MEDAL_KEY, 1);
//            }
//        } else {
//            ((ImageView) findViewById(R.id.medal)).setVisibility(View.INVISIBLE);
//        }
//        editor.apply();
//    }

    private void saveCoins() {
        SharedPreferences coin_save = gameActivity.getSharedPreferences(gameActivity.getCoinSave(), 0);
        coin_save.getInt(gameActivity.getCoinKey(), 0);
        SharedPreferences.Editor editor = coin_save.edit();
        editor.putInt(gameActivity.getCoinKey(), gameActivity.getCoins());
        editor.apply();
    }

    private void onOKClick(View view) {
        saveCoins();
        if (gameActivity.getNumberOfRevive() <= 1) {
            gameActivity.saveAccomplishmentBox();
//            gameActivity.accomplishmentBox.save(gameActivity);
        }

        dismiss();
        gameActivity.finish();
    }

    private void onReviveClick(View view) {
        dismiss();
        gameActivity.setCoins(gameActivity.getCoins() - REVIVE_PRICE * gameActivity.getNumberOfRevive());
        saveCoins();
        gameActivity.getView().revive();

    }
}
