package edu.ucsd.flappycow.presenter;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.view.GameActivity;
import edu.ucsd.flappycow.view.GameView;
import edu.ucsd.flappycow.view.MainActivity;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.model.Coin;

public class CoinPresenter extends PowerUpPresenter{

    public CoinPresenter(GameFacade gameFacade) {
        super(gameFacade);

        Coin coin = new Coin(gameFacade.getSpeedX(), gameFacade.getWidth());
        setPowerUpModel(coin);

        coin.onInitBitmap(Util.getScaledBitmapAlpha8(gameFacade.getGameActivity(), R.drawable.coin));

        setPowerUpModel(coin);

        if (((Coin)this.getPowerUpModel()).getSound() == -1) {
            ((Coin)this.getPowerUpModel()).setSound(GameActivity.soundPool.load(gameView.getGameActivity(), R.raw.coin, 1));
        }
    }
//
    private void playSound() {
        GameActivity.soundPool.play(((Coin)this.getPowerUpModel()).getSound(), MainActivity.volume, MainActivity.volume, 0, 0, 1);
    }

    public void onCollision() {
        super.onCollision();
        playSound();
        this.gameView.gameActivityIncreaseCoin();
    }
}
