package edu.ucsd.flappycow.sprite;


import android.graphics.Bitmap;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.sprite.Sprite;

public class Tutorial extends Sprite {
    public static Bitmap globalBitmap;

    public Tutorial(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        if (globalBitmap == null) {
            globalBitmap = Util.getScaledBitmapAlpha8(gameActivity, R.drawable.tutorial);
        }
        this.setBitmap(globalBitmap);
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

}

