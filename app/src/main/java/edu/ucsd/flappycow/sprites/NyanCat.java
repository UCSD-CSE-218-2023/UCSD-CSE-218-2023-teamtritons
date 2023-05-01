package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class NyanCat extends IPlayableCharacter{
    /** Static bitmap to reduce memory usage */
    private static Bitmap globalBitmap;

    /** The rainbow tail behind the cat */
    private Rainbow rainbow;

    public NyanCat(Rainbow rainbow, int viewHeight, int viewWidth, int activityHeightPixels) {
        super(viewHeight, viewWidth);
        //TODO: presenter
//        if (globalBitmap == null) {
//            globalBitmap = Util.getScaledBitmapAlpha8(gameActivity, R.drawable.nyan_cat);
//        }
        this.setBitmap(globalBitmap);
        this.setWidth(this.getBitmap().getWidth());
        this.setHeight(this.getBitmap().getHeight() / 2);
        this.setY(activityHeightPixels/ 2);
        this.rainbow = rainbow;
    }

    /**
     * Moves itself via super.move
     * and moves the rainbow and manages its frames
     */
    @Override
    public void move(int viewHeight, int viewWidth) {
        super.move(viewHeight, viewWidth);

        if (rainbow != null) {
            manageRainbowMovement(viewHeight, viewWidth);
        }
    }

    private void manageRainbowMovement(int viewHeight, int viewWidth) {
        rainbow.setY(this.getY());       // nyan cat and rainbow bitmap have the same height
        rainbow.setX(this.getX() - rainbow.getWidth());
        rainbow.move(viewHeight, viewWidth);

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
    public void revive(int viewHeight, int viewWidth) {
        super.revive(viewHeight, viewWidth);
        manageRainbowMovement(viewHeight, viewWidth);
    }

}
