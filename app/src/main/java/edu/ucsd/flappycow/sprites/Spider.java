package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Spider extends IGameObstacle{
    /**
     * Static bitmap to reduce memory usage.
     */
    private static Bitmap globalBitmap;

    public static Bitmap getGlobalBitmap() {
        return globalBitmap;
    }

    public static void setGlobalBitmap(Bitmap globalBitmap) {
        Spider.globalBitmap = globalBitmap;
    }

    public Spider(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        if (globalBitmap == null) {
            globalBitmap = Util.getScaledBitmapAlpha8(gameActivity, R.drawable.spider_full);
        }
        this.setBitmap(globalBitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }
}
