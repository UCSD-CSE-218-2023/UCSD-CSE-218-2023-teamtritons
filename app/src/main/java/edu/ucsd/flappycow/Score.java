package edu.ucsd.flappycow;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.TextView;

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
        if(gameActivityHandlerUpdate.type.equals("score")){
            SharedPreferences saves = gameActivityHandlerUpdate.gameActivity.getSharedPreferences(score_save_name, 0);
            int oldPoints = saves.getInt(best_score_key, 0);
            if (gameActivityHandlerUpdate.gameActivity.accomplishmentBox.points > oldPoints) {
                // Save new highscore
                SharedPreferences.Editor editor = saves.edit();
                editor.putInt(best_score_key, gameActivityHandlerUpdate.gameActivity.accomplishmentBox.points);
                this.tvBestScoreVal.setTextColor(Color.RED);
                editor.apply();
            }
            gameActivityHandlerUpdate.tvCurrentScoreVal.setText("" + gameActivityHandlerUpdate.gameActivity.accomplishmentBox.points);
            gameActivityHandlerUpdate.tvBestScoreVal.setText("" + oldPoints);
        }

       }

}