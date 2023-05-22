package edu.ucsd.flappycow.presenter;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.enums.PowerUp;
import edu.ucsd.flappycow.factory.PowerUpFactory;
import edu.ucsd.flappycow.model.Sound;
import edu.ucsd.flappycow.view.GameActivity;
import edu.ucsd.flappycow.view.MainActivity;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.util.Util;
import edu.ucsd.flappycow.model.Coin;

public class CoinPresenter extends PowerUpPresenter{


    Sound sound;

    public CoinPresenter(GameFacade gameFacade) {
        super(gameFacade);

        Coin coin = (Coin) abstractFactory.createPowerUp(PowerUp.COIN, gameFacade.getSpeedX(), gameFacade.getWidth());
        setPowerUpModel(coin);

        coin.onInitBitmap(Util.getScaledBitmapAlpha8(gameFacade.getGameActivity(), R.drawable.coin));

        setPowerUpModel(coin);

        if (((Coin)this.getPowerUpModel()).getSound() == -1) {
            ((Coin)this.getPowerUpModel()).setSound(gameFacade.getGameActivity().getSoundPool().load(gameFacade.getGameActivity(), R.raw.coin, 1));
        }

        sound = new Sound();
    }

//    private void playSound() {
//        gameFacade.getGameActivity().getSoundPool().play(((Coin)this.getPowerUpModel()).getSound(), MainActivity.volume, MainActivity.volume, 0, 0, 1);
//    }

    public void onCollision() {
        super.onCollision();
        //playSound();
        sound.playSound(gameFacade);
        gameFacade.getGameActivity().getSoundPool().play(((Coin)this.getPowerUpModel()).getSound(), MainActivity.volume, MainActivity.volume, 0, 0, 1);


        gameFacade.gameActivityIncreaseCoin();
    }
}
