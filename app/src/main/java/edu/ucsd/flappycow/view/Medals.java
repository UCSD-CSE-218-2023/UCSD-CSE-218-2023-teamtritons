package edu.ucsd.flappycow.view;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.view.View;

import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.util.IObserver;
import edu.ucsd.flappycow.util.Util;
import edu.ucsd.flappycow.consts.ApplicationConstants;

public class Medals extends Dialog implements IObserver<GameOverUpdate> {

    Medals(GameActivity gameActivity){
        super(gameActivity);
    }
    @Override
    public void onUpdate(GameOverUpdate data) {
        if(data.type.equals(ApplicationConstants.MEDALS)){
            SharedPreferences MEDAL_SAVE = data.gameActivity.getSharedPreferences(MainActivity.MEDAL_SAVE, 0);
            int medal = MEDAL_SAVE.getInt(MainActivity.MEDAL_KEY, 0);

            SharedPreferences.Editor editor = MEDAL_SAVE.edit();

            if (data.gameActivity.isAchievementGold()) {
                data.imageView.setImageBitmap(Util.getScaledBitmapAlpha8(data.gameActivity, R.drawable.gold));
                if (medal < 3) {
                    editor.putInt(MainActivity.MEDAL_KEY, 3);
                }
            } else if (data.gameActivity.isAchievementSilver()) {
                data.imageView.setImageBitmap(Util.getScaledBitmapAlpha8(data.gameActivity, R.drawable.silver));
                if (medal < 2) {
                    editor.putInt(MainActivity.MEDAL_KEY, 2);
                }
            } else if (data.gameActivity.isAchievementBronze()) {
                data.imageView.setImageBitmap(Util.getScaledBitmapAlpha8(data.gameActivity, R.drawable.bronce));
                if (medal < 1) {
                    editor.putInt(MainActivity.MEDAL_KEY, 1);
                }
            } else {
                data.imageView.setVisibility(View.INVISIBLE);
            }
            editor.apply();
        }

    }
}
