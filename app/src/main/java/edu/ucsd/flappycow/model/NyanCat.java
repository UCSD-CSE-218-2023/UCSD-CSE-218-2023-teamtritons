package edu.ucsd.flappycow.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import static edu.ucsd.flappycow.util.Contract.require;
import static edu.ucsd.flappycow.util.Contract.ensure;

public class NyanCat extends IPlayableCharacter{
    /** The rainbow tail behind the cat */
    private Rainbow rainbow;

    private NyanCat(NyanCatBuilder nyanCatBuilder) {
        super(nyanCatBuilder.viewWidth, nyanCatBuilder.viewHeight);
        this.setY(nyanCatBuilder.heightPixels / 2);
        this.rainbow = nyanCatBuilder.rainbow;
    }

    @Override
    public void onInitBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getSpriteBitmap().getBitmap().getWidth());
        this.setHeight(this.getSpriteBitmap().getBitmap().getHeight() / 2);
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
        require(viewWidth >= 0 , "View Width is non negative");
        require( viewHeight >= 0, "View Height is non negative");

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
        ensure(this.getRow() >= 0 && this.getRow() <= 3, "Row is set");

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

    public static class NyanCatBuilder {
        private Rainbow rainbow;
        private int viewWidth;
        private int viewHeight;
        private int heightPixels;

        public NyanCatBuilder setRainbow(Rainbow rainbow) {
            this.rainbow = rainbow;
            return this;
        }
        public NyanCatBuilder setViewWidth(int viewWidth) {
            this.viewWidth = viewWidth;
            return this;
        }
        public NyanCatBuilder setViewHeight(int viewHeight) {
            this.viewHeight = viewHeight;
            return this;
        }
        public NyanCatBuilder setHeightPixels(int heightPixels) {
            this.heightPixels = heightPixels;
            return this;
        }
        public NyanCat build() {
            NyanCat nyanCat = new NyanCat(this);
            return nyanCat;
        }
    }
}
