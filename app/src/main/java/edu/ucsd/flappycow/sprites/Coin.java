package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;
import edu.ucsd.flappycow.MainActivity;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Coin extends PowerUp{
    /**
     * Static bitmap to reduce memory usage.
     */
    public static Bitmap globalBitmap;
    private static int sound = -1;

    public Coin(int viewWidth, int viewHeight, int viewSpeedX) {
        super(viewWidth, viewHeight, viewSpeedX);
        //TODO:presenter
//        if (globalBitmap == null) {
//            globalBitmap = Util.getScaledBitmapAlpha8(gameActivity, R.drawable.coin);
//        }
        this.setBitmap(globalBitmap);
        this.setColNr((byte)12);
        this.setWidth(this.getBitmap().getWidth() / (this.getColNr()));
        this.setHeight(this.getBitmap().getHeight());
        this.setFrameTime((short)1);
        //TODO:presenter
//        if (sound == -1) {
//            sound = GameActivity.soundPool.load(gameActivity, R.raw.coin, 1);
//        }
    }

    /**
     * When eaten the player will turn into nyan cat.
     */
    @Override
    public void onCollision() {
        super.onCollision();
//        playSound();
        //TODO:presenter
//        this.getGameActivity().increaseCoin();
    }
//TODO:presenter
//    private void playSound() {
//        GameActivity.soundPool.play(sound, MainActivity.volume, MainActivity.volume, 0, 0, 1);
//    }

    @Override
    public void move(int viewHeight, int viewWidth) {
        changeToNextFrame();
        super.move(viewHeight, viewWidth);
    }
}
