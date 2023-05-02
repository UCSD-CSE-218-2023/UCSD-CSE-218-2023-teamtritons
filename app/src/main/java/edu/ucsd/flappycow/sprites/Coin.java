package edu.ucsd.flappycow.sprites;

import android.graphics.Bitmap;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.MainActivity;
import edu.ucsd.flappycow.R;

public class Coin extends PowerUp{
    private static int sound = -1;

    public Coin(GameView view, GameActivity gameActivity, int speedX) {
        super(view, gameActivity, speedX);
        this.setColNr((byte)12);
        this.setFrameTime((short)1);
//        if (sound == -1) {
//            sound = GameActivity.soundPool.load(gameActivity, R.raw.coin, 1);
//        }
    }

    @Override
    public void onInitBitmap(Bitmap bitmap) {
        super.onInitBitmap(bitmap);
        this.setWidth(this.getBitmap().getWidth() / (this.getColNr()));
        this.setHeight(this.getBitmap().getHeight());
    }

    /**
     * When eaten the player will turn into nyan cat.
     */
    @Override
    public void onCollision() {
        super.onCollision();
//        playSound();
//        this.getGameActivity().increaseCoin();
    }

    // TODO: presenter
//    private void playSound() {
//        GameActivity.soundPool.play(sound, MainActivity.volume, MainActivity.volume, 0, 0, 1);
//    }

    @Override
    public void move(int viewWidth, int viewHeight) {
        changeToNextFrame();
        super.move(viewWidth, viewHeight);
    }

    public static int getSound() {
        return sound;
    }

    public static void setSound(int sound) {
        Coin.sound = sound;
    }
}
