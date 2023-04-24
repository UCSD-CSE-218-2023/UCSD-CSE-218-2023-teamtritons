package edu.ucsd.flappycow.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Toast extends PowerUp{
    /**
     * Static bitmap to reduce memory usage.
     */
    public static Bitmap globalBitmap;

    public static final int POINTS_TO_TOAST = 42;

    public Toast(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        if (globalBitmap == null) {
            globalBitmap = Util.getScaledBitmapAlpha8(gameActivity, R.drawable.toast);
        }
        this.setBitmap(globalBitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }

    /**
     * When eaten the player will turn into nyan cat.
     */
    @Override
    public void onCollision() {
        super.onCollision();
        this.getView().changeToNyanCat();
    }
    public static float getPointsToToast() {
        return POINTS_TO_TOAST;
    }

}
