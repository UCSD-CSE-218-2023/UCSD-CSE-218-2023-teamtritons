package edu.ucsd.flappycow.model;

import android.graphics.Bitmap;

public class Background extends IGround {
    @Override
    public void onInitBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }
}
