package edu.ucsd.flappycow.sprite;

import android.graphics.Bitmap;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Frontground extends IGround {
    /**
     * Height of the ground relative to the height of the bitmap
     */
    private static final float GROUND_HEIGHT = (1f * /*45*/ 35) / 720;

    /** Static bitmap to reduce memory usage */
    private static Bitmap globalBitmap;

    public Frontground(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        if (globalBitmap == null) {
            globalBitmap = Util.getDownScaledBitmapAlpha8(gameActivity, R.drawable.fg);
        }
        this.setBitmap(globalBitmap);
    }

    public static Bitmap getGlobalBitmap() {
        return globalBitmap;
    }

    public static void setGlobalBitmap(Bitmap globalBitmap) {
        Frontground.globalBitmap = globalBitmap;
    }

    public static float getGroundHeight() {
        return GROUND_HEIGHT;
    }



}
