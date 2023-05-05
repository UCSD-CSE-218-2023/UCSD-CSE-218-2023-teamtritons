package edu.ucsd.flappycow.model;

import android.graphics.Bitmap;

public class PauseButton extends IGameButton{
    /**
     * Sets the button in the right upper corner.
     */
    @Override
    public void move(int viewWidth, int viewHeight) {
        this.setX(viewWidth - this.getWidth());
        this.setY(0);
    }
    @Override
    public void onInitBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }
}
