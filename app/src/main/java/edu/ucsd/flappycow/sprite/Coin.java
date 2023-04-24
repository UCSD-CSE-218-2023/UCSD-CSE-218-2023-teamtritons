package edu.ucsd.flappycow.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.MainActivity;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;

public class Coin extends PowerUp{
    /**
     * Static bitmap to reduce memory usage.
     */
    public static Bitmap globalBitmap;
    private static int sound = -1;

    public Coin(GameView view, GameActivity gameActivity) {
        super(view, gameActivity);
        if (globalBitmap == null) {
            globalBitmap = Util.getScaledBitmapAlpha8(gameActivity, R.drawable.coin);
        }
        this.setBitmap(globalBitmap);
        this.setColNr((byte)12);
        this.setWidth(this.getBitmap().getWidth() / (this.getColNr()));
        this.setHeight(this.getBitmap().getHeight());
        this.setFrameTime((short)1);
        if (sound == -1) {
            sound = GameActivity.soundPool.load(gameActivity, R.raw.coin, 1);
        }
    }

    /**
     * When eaten the player will turn into nyan cat.
     */
    @Override
    public void onCollision() {
        super.onCollision();
        playSound();
        this.getGameActivity().increaseCoin();
    }

    private void playSound() {
        GameActivity.soundPool.play(sound, MainActivity.volume, MainActivity.volume, 0, 0, 1);
    }

    @Override
    public void move() {
        changeToNextFrame();
        super.move();
    }
}
