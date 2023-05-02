package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class NyanCat extends IPlayableCharacter{
    /** The rainbow tail behind the cat */
    private Rainbow rainbow;

    public NyanCat(GameView view, GameActivity gameActivity, int viewWidth, int viewHeight, Rainbow rainbow) {
        super(view, gameActivity, viewWidth, viewHeight);
        this.setY(gameActivity.getResources().getDisplayMetrics().heightPixels / 2);
        this.rainbow = rainbow;
    }

    @Override
    public void onInitBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight() / 2);
    }

    /**
     * Moves itself via super.move
     * and moves the rainbow and manages its frames
     */
    @Override
    public void move(int viewWidth, int viewHeight) {
        super.move(viewWidth, viewHeight);
        if (rainbow != null) {
            manageRainbowMovement(viewWidth, viewHeight);
        }
    }

    private void manageRainbowMovement(int viewWidth, int viewHeight) {
        rainbow.setY(this.getY());       // nyan cat and rainbow bitmap have the same height
        rainbow.setX(this.getX() - rainbow.getWidth());
        rainbow.move(viewWidth, viewHeight);

        // manage frames of the rainbow
        if (this.getSpeedY() > getTabSpeed(viewHeight) / 3 && this.getSpeedY() < getMaxSpeed(viewHeight) * 1 / 3) {
            rainbow.setRow(new Integer(0).byteValue());
        } else if (this.getSpeedY() > 0) {
            rainbow.setRow((byte) 1);
        } else {
            rainbow.setRow((byte) 2);
        }
    }

    /**
     * Draws itself via super.draw
     * and the rainbow.
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (rainbow != null && !this.isDead()) {
            rainbow.draw(canvas);
        }
    }

    /**
     * Calls super.dead,
     * removes the rainbow tail
     * and set the bitmapframe to a dead cat -.-
     */
    @Override
    public void dead(int viewHeight) {
        super.dead(viewHeight);
        this.setRow((byte) 1);

        // Maybe an explosion
    }

    @Override
    public void revive(int viewWidth, int viewHeight) {
        super.revive(viewWidth, viewHeight);
        manageRainbowMovement(viewWidth, viewHeight);
    }
}
