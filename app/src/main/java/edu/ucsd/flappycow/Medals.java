package edu.ucsd.flappycow;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;

public class Medals extends Dialog implements IObserver<GameOverUpdate>{

    Medals(GameActivity gameActivity){
        super(gameActivity);
    }
    @Override
    public void onUpdate(GameOverUpdate data) {
        if(data.type.equals("medals")){
            SharedPreferences MEDAL_SAVE = data.gameActivity.getSharedPreferences(MainActivity.MEDAL_SAVE, 0);
            int medal = MEDAL_SAVE.getInt(MainActivity.MEDAL_KEY, 0);

            SharedPreferences.Editor editor = MEDAL_SAVE.edit();

            if (data.gameActivity.accomplishmentBox.isAchievement_gold()) {
                data.imageView.setImageBitmap(Util.getScaledBitmapAlpha8(data.gameActivity, R.drawable.gold));
                if (medal < 3) {
                    editor.putInt(MainActivity.MEDAL_KEY, 3);
                }
            } else if (data.gameActivity.accomplishmentBox.isAchievement_silver()) {
                data.imageView.setImageBitmap(Util.getScaledBitmapAlpha8(data.gameActivity, R.drawable.silver));
                if (medal < 2) {
                    editor.putInt(MainActivity.MEDAL_KEY, 2);
                }
            } else if (data.gameActivity.accomplishmentBox.isAchievement_bronze()) {
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
