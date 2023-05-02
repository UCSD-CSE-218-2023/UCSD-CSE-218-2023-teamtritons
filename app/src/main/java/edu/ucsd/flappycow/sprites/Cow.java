package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.MainActivity;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Cow extends IPlayableCharacter{
    private static final int POINTS_TO_SIR = 23;
    private static final int POINTS_TO_COOL = 35;

    /** The moo sound */
    private static int sound = -1;

    /** sunglasses, hats and stuff */
    private IAccessory accessory;

    public Cow(GameView view, GameActivity gameActivity, int viewWidth, int viewHeight, IAccessory accessory) {
        super(view, gameActivity, viewWidth, viewHeight);
        this.setColNr(new Integer(8).byteValue());
        this.setFrameTime((short)3);        // the frame will change every 3 runs
        this.setY(gameActivity.getResources().getDisplayMetrics().heightPixels / 2);    // Startposition in in the middle of the screen
        // TODO: presenter
//        if (sound == -1) {
//            sound = GameActivity.soundPool.load(gameActivity, R.raw.cow, 1);
//        }

        this.accessory = accessory;
    }

    @Override
    public void onInitBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getBitmap().getWidth()/(getColNr()));  // The image has 8 frames in a row
        this.setHeight(this.getBitmap().getHeight() / 4);           // and 4 in a column
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

    // TODO: presenter
//    private void playSound() {
//        GameActivity.soundPool.play(sound, MainActivity.volume, MainActivity.volume, 0, 0, 1);
//    }

    @Override
    public void onTap() {
        super.onTap();
//        playSound();
    }

    /**
     * Calls super.move
     * and manages the frames. (flattering cape)
     */
    @Override
    public void move(int viewWidth, int viewHeight) {
        changeToNextFrame();
        super.move(viewWidth, viewHeight);

        // manage frames
        if (this.getRow() != 3) {
            // not dead
            if (this.getSpeedY() > getTabSpeed() / 3 && this.getSpeedY() < getMaxSpeed() * 1 / 3) {
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
    public void dead() {
        this.setRow(new Integer(3).byteValue());
        this.setFrameTime((short) 3);
        super.dead();
    }

    // TODO: presenter
    @Override
    public void revive(int viewWidth, int viewHeight) {
        super.revive(viewWidth, viewHeight);
//        this.accessory.setBitmap(Util.getScaledBitmapAlpha8(this.getGameActivity(), R.drawable.accessory_scumbag));
    }

    // TODO: presenter
    @Override
    public void upgradeBitmap(int points) {
        super.upgradeBitmap(points);
//        if (points == POINTS_TO_SIR) {
//            this.accessory.setBitmap(Util.getScaledBitmapAlpha8(this.getGameActivity(), R.drawable.accessory_sir));
//        } else if (points == POINTS_TO_COOL) {
//            this.accessory.setBitmap(Util.getScaledBitmapAlpha8(this.getGameActivity(), R.drawable.accessory_sunglasses));
//        }
    }

    // TODO: presenter
//    @Override
    public void wearMask() {
        super.wearMask();
////        this.accessory.setBitmap(Util.getScaledBitmapAlpha8(this.getGameActivity(), R.drawable.mask));
    }

}
