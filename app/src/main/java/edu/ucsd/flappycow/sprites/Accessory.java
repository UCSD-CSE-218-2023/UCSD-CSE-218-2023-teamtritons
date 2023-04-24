package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

public class Accessory extends Sprite implements IAccessory{

    public Accessory(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
    }

    @Override
    public void moveTo(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        this.setBitmap(bitmap);
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
