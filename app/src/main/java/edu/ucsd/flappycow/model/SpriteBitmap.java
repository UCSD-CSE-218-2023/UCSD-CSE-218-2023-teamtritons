package edu.ucsd.flappycow.model;

import android.graphics.Bitmap;

public class SpriteBitmap {
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void onInitBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
