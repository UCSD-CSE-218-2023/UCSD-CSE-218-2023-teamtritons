package edu.ucsd.flappycow.presenter;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.enums.GameLevel;
import edu.ucsd.flappycow.enums.PlayableCharacter;
import edu.ucsd.flappycow.factory.AbstractFactory;
import edu.ucsd.flappycow.factory.GameLevelFactoryProvider;
import edu.ucsd.flappycow.model.Sound;
import edu.ucsd.flappycow.view.GameActivity;
import edu.ucsd.flappycow.view.MainActivity;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.util.Util;
import edu.ucsd.flappycow.model.Cow;

public class CowPresenter extends PlayableCharacterPresenter{
    AbstractFactory abstractFactory;


    Sound sound;

    public CowPresenter(GameFacade gameFacade, PlayableCharacter type) {
        super(gameFacade, type);
        sound = new Sound();

        abstractFactory = GameLevelFactoryProvider.getFactory(GameLevel.LEVEL_1);

        if(((Cow)this.getPlayableCharacterModel()).getSound() == -1) {
            ((Cow)this.getPlayableCharacterModel()).setSound(gameFacade.getGameActivity().getSoundPool().load(getGameFacade().getGameActivity(), R.raw.cow, 1));
        }
    }

    public void wearMask() {
        this.getPlayer().wearMask();
        System.out.println(getAccessory() + "-----");
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

//    private void playSound() {
//        int sound = ((Cow)this.getPlayableCharacterModel()).getSound();
//        getGameFacade().getGameActivity().getSoundPool().play(sound, MainActivity.volume, MainActivity.volume, 0, 0, 1);
//    }

    public void onTap() {
        this.getPlayer().onTap(getGameFacade().getHeight());
        sound.setSound(((Cow)this.getPlayableCharacterModel()).getSound());
        sound.playSound(getGameFacade());

        //playSound();
    }
}
