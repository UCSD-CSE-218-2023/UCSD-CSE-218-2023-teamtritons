package edu.ucsd.flappycow.presenter;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.view.GameActivity;
import edu.ucsd.flappycow.view.MainActivity;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.util.Util;
import edu.ucsd.flappycow.model.Cow;

public class CowPresenter extends PlayableCharacterPresenter{
    public CowPresenter(GameFacade gameFacade, String type) {
        super(gameFacade, type);

        if(((Cow)this.getPlayableCharacterModel()).getSound() == -1) {
            ((Cow)this.getPlayableCharacterModel()).setSound(GameActivity.soundPool.load(getGameFacade().getGameActivity(), R.raw.cow, 1));
        }
    }

    public void wearMask() {
        this.getPlayer().wearMask();
        this.getAccessory().setBitmap(Util.getScaledBitmapAlpha8(getGameFacade().getGameActivity(), R.drawable.mask));
    }

    public void upgradeBitmap(int points) {
        this.getPlayer().upgradeBitmap(points);
        if (points == ((Cow)this.getPlayableCharacterModel()).getPointsToSir()) {
            this.getAccessory().setBitmap(Util.getScaledBitmapAlpha8(getGameFacade().getGameActivity(), R.drawable.accessory_sir));
        } else if (points == ((Cow)this.getPlayableCharacterModel()).getPointsToCool()) {
            this.getAccessory().setBitmap(Util.getScaledBitmapAlpha8(getGameFacade().getGameActivity(), R.drawable.accessory_sunglasses));
        }
    }

    public void revive() {
        this.getPlayer().revive(getGameFacade().getWidth(), getGameFacade().getHeight());
        this.getAccessory().setBitmap(Util.getScaledBitmapAlpha8(getGameFacade().getGameActivity(), R.drawable.accessory_scumbag));
    }

    private void playSound() {
        int sound = ((Cow)this.getPlayableCharacterModel()).getSound();
        GameActivity.soundPool.play(sound, MainActivity.volume, MainActivity.volume, 0, 0, 1);
    }

    public void onTap() {
        this.getPlayer().onTap(getGameFacade().getHeight());
        playSound();
    }
}
