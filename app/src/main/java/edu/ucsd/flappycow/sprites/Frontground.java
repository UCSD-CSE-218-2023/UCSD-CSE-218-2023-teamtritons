package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

public class Frontground extends IGround {
    /**
     * Height of the ground relative to the height of the bitmap
     */
    private static final float GROUND_HEIGHT = (1f * /*45*/ 35) / 720;

    public Frontground(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
    }

    public static float getGroundHeight() {
        return GROUND_HEIGHT;
    }



}
