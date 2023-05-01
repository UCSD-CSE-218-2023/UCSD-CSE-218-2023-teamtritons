package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Toast extends PowerUp{

    public static final int POINTS_TO_TOAST = 42;

    public Toast(int viewWidth, int viewHeight, int viewSpeedX) {
        super(viewWidth, viewHeight, viewSpeedX);
    }

    @Override
    public void onInitBitmap() {
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }
    /**
     * When eaten the player will turn into nyan cat.
     */
    //TODO: presenter and fix
    @Override
    public void onCollision() {
        super.onCollision();
//        this.getView().changeToNyanCat();
    }
    public static float getPointsToToast() {
        return POINTS_TO_TOAST;
    }

}
