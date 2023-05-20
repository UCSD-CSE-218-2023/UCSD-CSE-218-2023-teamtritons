package edu.ucsd.flappycow.model;

import android.graphics.Bitmap;

public class Virus extends IPowerUp {

    public Virus(int speedX, int viewWidth) {
        super(speedX, viewWidth);
    }

    @Override
    public void onInitBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }

    /**
     * When eaten the player will become infected.
     */
    @Override
    public void onCollision() {
        super.onCollision();
//        // TODO: presenter
//        this.getGameActivity().decreasePoints();
//        this.getView().changeToSick();
    }
}
