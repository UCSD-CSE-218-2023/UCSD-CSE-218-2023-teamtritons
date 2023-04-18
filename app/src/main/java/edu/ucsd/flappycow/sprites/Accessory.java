package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;


public class Accessory extends Sprite implements AccessoryImpl {

    public Accessory(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
    }

    @Override
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
    }

    @Override
    public void draw(Canvas canvas) {
        if (this.bitmap != null) {
            super.draw(canvas);
        }
    }
}
