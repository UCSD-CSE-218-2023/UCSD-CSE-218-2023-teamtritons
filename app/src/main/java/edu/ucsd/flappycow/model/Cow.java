package edu.ucsd.flappycow.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import static edu.ucsd.flappycow.util.Contract.require;
import static edu.ucsd.flappycow.util.Contract.ensure;



public class Cow extends IPlayableCharacter{
    private static final int POINTS_TO_SIR = 23;
    private static final int POINTS_TO_COOL = 35;

    /** The moo sound */
    private static int sound = -1;
    /** sunglasses, hats and stuff */
    private IAccessory accessory;
    public Cow(CowBuilder cowBuilder) {
        super(cowBuilder.height, cowBuilder.width);
        this.setColNr(new Integer(8).byteValue());
        this.setFrameTime((short)3);        // the frame will change every 3 runs
        this.setY(cowBuilder.heightPixels / 2);    // Startposition in in the middle of the screen
        this.accessory = cowBuilder.accessory;
    }

    @Override
    public void onInitBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getSpriteBitmap().getBitmap().getWidth()/(getColNr()));  // The image has 8 frames in a row
        this.setHeight(this.getSpriteBitmap().getBitmap().getHeight() / 4);           // and 4 in a column
    }

    public static int getSound() {
        return sound;
    }

    public static void setSound(int sound) {
        Cow.sound = sound;
    }

    public IAccessory getAccessory() {
        return accessory;
    }

    public void setAccessory(IAccessory accessory) {
        this.accessory = accessory;
    }

    public static int getPointsToSir() {
        return POINTS_TO_SIR;
    }

    public static int getPointsToCool() {
        return POINTS_TO_COOL;
    }
    @Override
    public void onTap(int viewHeight) {
        super.onTap(viewHeight);
//        playSound();
    }

    /**
     * Calls super.move
     * and manages the frames. (flattering cape)
     */
    @Override
    public void move(int viewWidth, int viewHeight) {
        require(viewWidth >= 0 , "View Width is non negative");
        require( viewHeight >= 0, "View Height is non negative");

        changeToNextFrame();
        super.move(viewWidth, viewHeight);

        // manage frames
        if (this.getRow() != 3) {
            // not dead
            if (this.getSpeedY() > getTabSpeed(viewHeight) / 3 && this.getSpeedY() < getMaxSpeed(viewHeight) * 1 / 3) {
                this.setRow((byte) 0);
            } else if (this.getSpeedY() > 0) {
                this.setRow((byte) 1);
            } else {
                this.setRow((byte) 2);
            }
        }

        if (this.accessory != null) {
            this.accessory.moveTo(this.getX(), this.getY());
        }
        ensure(this.getRow() >= 0 && this.getRow() <= 3, "Row is set");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.accessory != null && !this.isDead()) {
            this.accessory.draw(canvas);
        }
    }

    /**
     * Calls super.dead
     * And changes the frame to a dead cow -.-
     */
    @Override
    public void dead(int viewHeight) {
        require( viewHeight >= 0, "View Height is non negative");
        this.setRow(new Integer(3).byteValue());
        this.setFrameTime((short) 3);
        super.dead(viewHeight);
        ensure(this.getRow() == 3, "Row is set to 3");
        ensure(this.isDead() == true, "Cow is dead");
    }


    @Override
    public void revive(int viewWidth, int viewHeight) {
        super.revive(viewWidth, viewHeight);
    }


    @Override
    public void upgradeBitmap(int points) {
        super.upgradeBitmap(points);
    }

//    @Override
    public void wearMask() {
        super.wearMask();
    }

    public static class CowBuilder {
        private IAccessory accessory;
        private int width;
        private int heightPixels;
        private int height;
        public CowBuilder setAccessory(IAccessory accessory) {
            this.accessory = accessory;
            return this;
        }
        public CowBuilder setWidth(int width) {
            this.width = width;
            return this;
        }
        public CowBuilder setHeightPixels(int heightPixels) {
            this.heightPixels = heightPixels;
            return this;
        }
        public CowBuilder setHeight(int height) {
            this.height = height;
            return this;
        }
        public Cow build() {
            Cow cow = new Cow(this);
            return cow;
        }
    }

}
