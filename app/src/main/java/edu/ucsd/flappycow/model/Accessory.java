package edu.ucsd.flappycow.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Accessory extends Sprite implements IAccessory{

    @Override
    public void moveTo(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getSpriteBitmap().getBitmap().getWidth());
        this.setHeight(this.getSpriteBitmap().getBitmap().getHeight());
    }

    @Override
    public void draw(Canvas canvas) {
        if(this.getSpriteBitmap().getBitmap() != null) {
            super.draw(canvas);
        }
    }
}
