package edu.ucsd.flappycow.view;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.TextView;

import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.util.IObserver;
import static edu.ucsd.flappycow.util.Contract.require;
import static edu.ucsd.flappycow.util.Contract.ensure;

public class Score implements IObserver<GameOverUpdate> {
    /**
     * Name of the SharedPreference that saves the score
     */
    public static final String score_save_name = "score_save";

    /**
     * Key that saves the score
     */
    public static final String best_score_key = "score";

    TextView tvCurrentScoreVal;
    TextView tvBestScoreVal;

    @Override
    public void onUpdate(GameOverUpdate gameActivityHandlerUpdate) {
        require(gameActivityHandlerUpdate != null , "gameActivityHandlerUpdate != null");
        if(gameActivityHandlerUpdate.type.equals(ApplicationConstants.SCORE)){
            require(gameActivityHandlerUpdate.type.equals(ApplicationConstants.SCORE) , "Score update");
            SharedPreferences saves = gameActivityHandlerUpdate.gameActivity.getSharedPreferences(score_save_name, 0);
            int oldPoints = saves.getInt(best_score_key, 0);
            int currPoints = gameActivityHandlerUpdate.gameActivity.getAccomplishmentBoxPoints();
            if (currPoints > oldPoints) {
                // Save new highscore
                SharedPreferences.Editor editor = saves.edit();
                require(currPoints > oldPoints, "currPoints > oldPoints");
                editor.putInt(best_score_key, currPoints);
                gameActivityHandlerUpdate.tvCurrentScoreVal.setTextColor(Color.RED);
                editor.apply();
            }
            gameActivityHandlerUpdate.tvCurrentScoreVal.setText("" + currPoints);
            gameActivityHandlerUpdate.tvBestScoreVal.setText("" + oldPoints);
            ensure(currPoints >= 0, "Current Score is non negative");
            ensure(gameActivityHandlerUpdate.tvCurrentScoreVal.getText().equals("" + currPoints), "Current Score Text Set");
            ensure(gameActivityHandlerUpdate.tvBestScoreVal.getText().equals("" + oldPoints), "Best Score Text Set");

        }

       }

}