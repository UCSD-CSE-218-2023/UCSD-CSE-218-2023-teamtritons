package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Background extends IGround {
    /** Static bitmap to reduce memory usage */
    private static Bitmap globalBitmap;

    public Background(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);

//        if (globalBitmap == null) {
//            globalBitmap = Util.getDownScaledBitmapAlpha8(gameActivity, R.drawable.bg);
//        }
//        this.setBitmap(globalBitmap);
    }

//    public static Bitmap getGlobalBitmap() {
//        return globalBitmap;
//    }
//
//    public static void setGlobalBitmap(Bitmap globalBitmap) {
//        Background.globalBitmap = globalBitmap;
//    }
    @Override
    public void onInitBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }
}
