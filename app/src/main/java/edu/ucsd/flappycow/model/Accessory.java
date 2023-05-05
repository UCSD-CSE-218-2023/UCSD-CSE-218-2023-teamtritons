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
        super.setBitmap(bitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight());
    }

    @Override
    public void draw(Canvas canvas) {
        if(this.getBitmap() != null) {
            super.draw(canvas);
        }
    }
}
