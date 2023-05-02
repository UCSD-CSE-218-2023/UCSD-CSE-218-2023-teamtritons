package edu.ucsd.flappycow.view;

import android.widget.ImageView;
import android.widget.TextView;

public class GameOverUpdate {

    GameActivity gameActivity;
    TextView tvCurrentScoreVal;
    TextView tvBestScoreVal;

    ImageView imageView;
    String type;


    public GameOverUpdate(GameActivity gameActivity, TextView tvCurrentScoreVal, TextView tvBestScoreVal, String type) {
        this.gameActivity = gameActivity;
        this.tvCurrentScoreVal = tvCurrentScoreVal;
        this.tvBestScoreVal = tvBestScoreVal;
        this.type = type;
    }

    public GameOverUpdate(GameActivity gameActivity, ImageView imageView, String type) {
        this.gameActivity = gameActivity;
        this.imageView = imageView;
        this.type = type;
    }

}
