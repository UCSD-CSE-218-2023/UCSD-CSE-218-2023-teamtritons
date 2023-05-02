package edu.ucsd.flappycow.model;


import android.graphics.Bitmap;

public class Tutorial extends Sprite {
    private boolean tutorialIsShown ;

    public Tutorial() {
        tutorialIsShown= true;
    }

    @Override
    public void onInitBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }

    /**
     * Sets the position to the center of the view.
     */
    @Override
    public void move(int viewWidth, int viewHeight) {
        this.setX(viewWidth / 2 - this.getWidth() / 2);
        this.setY(viewHeight / 2 - this.getHeight() / 2);
    }

    public boolean isTutorialIsShown() {
        return tutorialIsShown;
    }

    public void setTutorialIsShown(boolean tutorialIsShown) {
        this.tutorialIsShown = tutorialIsShown;
    }
}

