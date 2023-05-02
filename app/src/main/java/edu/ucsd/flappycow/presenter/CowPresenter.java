package edu.ucsd.flappycow.presenter;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.MainActivity;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.sprites.Cow;

public class CowPresenter extends PlayableCharacterPresenter{
    public CowPresenter(GameView gameView, String type) {
        super(gameView, type);

        if(((Cow)this.getPlayableCharacterModel()).getSound() == -1) {
            ((Cow)this.getPlayableCharacterModel()).setSound(GameActivity.soundPool.load(this.getGameView().getGameActivity(), R.raw.cow, 1));
        }
    }

    public void wearMask() {
        this.getPlayer().wearMask();
        this.getAccessory().setBitmap(Util.getScaledBitmapAlpha8(this.getGameView().getGameActivity(), R.drawable.mask));
    }

    public void upgradeBitmap(int points) {
        this.getPlayer().upgradeBitmap(points);
        if (points == ((Cow)this.getPlayableCharacterModel()).getPointsToSir()) {
            this.getAccessory().setBitmap(Util.getScaledBitmapAlpha8(this.getGameView().getGameActivity(), R.drawable.accessory_sir));
        } else if (points == ((Cow)this.getPlayableCharacterModel()).getPointsToCool()) {
            this.getAccessory().setBitmap(Util.getScaledBitmapAlpha8(this.getGameView().getGameActivity(), R.drawable.accessory_sunglasses));
        }
    }

    public void revive(int viewWidth, int viewHeight) {
        this.getPlayer().revive(viewWidth, viewHeight);
        this.getAccessory().setBitmap(Util.getScaledBitmapAlpha8(this.getGameView().getGameActivity(), R.drawable.accessory_scumbag));
    }

    private void playSound() {
        int sound = ((Cow)this.getPlayableCharacterModel()).getSound();
        GameActivity.soundPool.play(sound, MainActivity.volume, MainActivity.volume, 0, 0, 1);
    }

    public void onTap() {
        this.getPlayer().onTap();
        playSound();
    }
}
