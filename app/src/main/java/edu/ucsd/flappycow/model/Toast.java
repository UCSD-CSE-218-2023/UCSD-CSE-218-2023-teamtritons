package edu.ucsd.flappycow.model;

import android.graphics.Bitmap;

public class Toast extends IPowerUp {

    public static final int POINTS_TO_TOAST = 42;

    public Toast(int speedX, int viewWidth) {
        super(speedX, viewWidth);
    }

    @Override
    public void onInitBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getSpriteBitmap().getBitmap().getWidth());
        this.setHeight(this.getSpriteBitmap().getBitmap().getHeight());
    }
    /**
     * When eaten the player will turn into nyan cat.
     */
    @Override
    public void onCollision() {
        super.onCollision();
//        this.getView().changeToNyanCat();
    }
    public static float getPointsToToast() {
        return POINTS_TO_TOAST;
    }

}
