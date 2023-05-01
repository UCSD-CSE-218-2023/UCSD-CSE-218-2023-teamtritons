package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Background extends IGround {
    /** Static bitmap to reduce memory usage */
    private static Bitmap globalBitmap;

    public Background() {
        super();
        //TODO:presenter
//        if (globalBitmap == null) {
//            globalBitmap = Util.getDownScaledBitmapAlpha8(gameActivity, R.drawable.bg);
//        }
        this.setBitmap(globalBitmap);
    }

    public static Bitmap getGlobalBitmap() {
        return globalBitmap;
    }

    public static void setGlobalBitmap(Bitmap globalBitmap) {
        Background.globalBitmap = globalBitmap;
    }
}
