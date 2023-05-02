package edu.ucsd.flappycow.sprites;


import android.graphics.Bitmap;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Tutorial extends Sprite {
    public static Bitmap globalBitmap;
    private boolean tutorialIsShown ;

    public Tutorial(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
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
    public void move() {
        this.setX(this.getView().getWidth() / 2 - this.getWidth() / 2);
        this.setY(this.getView().getHeight() / 2 - this.getHeight() / 2);
    }

    public boolean isTutorialIsShown() {
        return tutorialIsShown;
    }

    public void setTutorialIsShown(boolean tutorialIsShown) {
        this.tutorialIsShown = tutorialIsShown;
    }
}

