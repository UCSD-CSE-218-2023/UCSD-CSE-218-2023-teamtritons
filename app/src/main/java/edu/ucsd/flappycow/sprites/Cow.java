package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import edu.ucsd.flappycow.MainActivity;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Cow extends IPlayableCharacter{
    private static final int POINTS_TO_SIR = 23;
    private static final int POINTS_TO_COOL = 35;

    /** Static bitmap to reduce memory usage. */
    private static Bitmap globalBitmap;

    /** The moo sound */
    private static int sound = -1;

    /** sunglasses, hats and stuff */
    private IAccessory accessory;

    public Cow(IAccessory accessory, int viewHeight, int viewWidth, int activityHeightPixels) {
        super(viewHeight, viewWidth);
        //TODO: presenter
//        if (globalBitmap == null) {
//            globalBitmap = Util.getScaledBitmapAlpha8(gameActivity, R.drawable.cow);
//        }
        this.setBitmap(globalBitmap);
        this.setColNr(new Integer(8).byteValue());
        this.setWidth(this.getBitmap().getWidth()/(getColNr()));  // The image has 8 frames in a row
        this.setHeight(this.getBitmap().getHeight() / 4);           // and 4 in a column
        this.setFrameTime((short)3);        // the frame will change every 3 runs
        this.setY(activityHeightPixels/ 2);    // Startposition in in the middle of the screen

        //TODO: presenter
//        if (sound == -1) {
//            sound = GameActivity.soundPool.load(gameActivity, R.raw.cow, 1);
//        }

        this.accessory = accessory;
    }

    public static Bitmap getGlobalBitmap() {
        return globalBitmap;
    }

    public static void setGlobalBitmap(Bitmap globalBitmap) {
        Cow.globalBitmap = globalBitmap;
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

    //TODO:presenter
//    private void playSound() {
//        GameActivity.soundPool.play(sound, MainActivity.volume, MainActivity.volume, 0, 0, 1);
//    }

    @Override
    public void onTap(int viewHeight) {
        super.onTap(viewHeight);
        //TODO:presenter
//        playSound();
    }

    /**
     * Calls super.move
     * and manages the frames. (flattering cape)
     */
    @Override
    public void move(int viewHeight, int viewWidth) {
        changeToNextFrame();
        super.move(viewHeight, viewWidth);

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
        this.setRow(new Integer(3).byteValue());
        this.setFrameTime((short) 3);
        super.dead(viewHeight);
    }

    @Override
    public void revive(int viewHeight, int viewWidth) {
        super.revive(viewHeight, viewWidth);
        //TODO:presenter
//        this.accessory.setBitmap(Util.getScaledBitmapAlpha8(this.getGameActivity(), R.drawable.accessory_scumbag));
    }

    @Override
    public void upgradeBitmap(int points) {
        super.upgradeBitmap(points);
        if (points == POINTS_TO_SIR) {
            //TODO:presenter
//            this.accessory.setBitmap(Util.getScaledBitmapAlpha8(this.getGameActivity(), R.drawable.accessory_sir));
        } else if (points == POINTS_TO_COOL) {
            //TODO:presenter
//            this.accessory.setBitmap(Util.getScaledBitmapAlpha8(this.getGameActivity(), R.drawable.accessory_sunglasses));
        }
    }

    @Override
    public void wearMask() {
        super.wearMask();
        //TODO:presenter
//        this.accessory.setBitmap(Util.getScaledBitmapAlpha8(this.getGameActivity(), R.drawable.mask));
    }

}
