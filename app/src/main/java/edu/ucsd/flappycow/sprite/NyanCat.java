package edu.ucsd.flappycow.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.sprites.IRainbow;

public class NyanCat extends IPlayableCharacter{
    /** Static bitmap to reduce memory usage */
    private static Bitmap globalBitmap;

    /** The rainbow tail behind the cat */
    private Rainbow rainbow;

    public NyanCat(GameView view, GameActivity gameActivity, Rainbow rainbow) {
        super(view, gameActivity);
        if (globalBitmap == null) {
            globalBitmap = Util.getScaledBitmapAlpha8(gameActivity, R.drawable.nyan_cat);
        }
        this.setBitmap(globalBitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight() / 2);
        this.setY(gameActivity.getResources().getDisplayMetrics().heightPixels / 2);
        this.rainbow = rainbow;
    }

    /**
     * Moves itself via super.move
     * and moves the rainbow and manages its frames
     */
    @Override
    public void move() {
        super.move();

        if (rainbow != null) {
            manageRainbowMovement();
        }
    }

    private void manageRainbowMovement() {
        rainbow.setY(this.getY());       // nyan cat and rainbow bitmap have the same height
        rainbow.setX(this.getX() - rainbow.getWidth());
        rainbow.move();

        // manage frames of the rainbow
        if (this.getSpeedY() > getTabSpeed() / 3 && this.getSpeedY() < getMaxSpeed() * 1 / 3) {
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
    public void dead() {
        super.dead();
        this.setRow((byte) 1);

        // Maybe an explosion
    }

    @Override
    public void revive() {
        super.revive();
        manageRainbowMovement();
    }

}
