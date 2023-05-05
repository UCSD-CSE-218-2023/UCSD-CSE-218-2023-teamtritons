package edu.ucsd.flappycow.model;

import android.graphics.Bitmap;

public class WoodLog extends IGameObstacle {
    @Override
    public void onInitBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }
}
