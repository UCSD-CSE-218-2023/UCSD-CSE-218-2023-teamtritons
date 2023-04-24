package edu.ucsd.flappycow.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class WoodLog extends IGameObstacle{
    public static Bitmap getGlobalBitmap() {
        return globalBitmap;
    }

    public static void setGlobalBitmap(Bitmap globalBitmap) {
        WoodLog.globalBitmap = globalBitmap;
    }

    /**
     * Static bitmap to reduce memory usage.
     */
    private static Bitmap globalBitmap;
    public WoodLog(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        if (globalBitmap == null) {
            globalBitmap = Util.getScaledBitmapAlpha8(gameActivity, R.drawable.log_full);
        }
        this.setBitmap(globalBitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }
}
