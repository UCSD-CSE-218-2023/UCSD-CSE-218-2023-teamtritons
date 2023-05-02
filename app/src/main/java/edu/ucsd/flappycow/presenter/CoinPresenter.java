package edu.ucsd.flappycow.presenter;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.MainActivity;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.sprites.Coin;
import edu.ucsd.flappycow.sprites.Cow;
import edu.ucsd.flappycow.sprites.Toast;

public class CoinPresenter extends PowerUpPresenter{

    public CoinPresenter(GameView gameView) {
        super(gameView);

        Coin coin = new Coin(gameView, gameView.getGameActivity(), gameView.getSpeedX(), gameView.getWidth());
        setPowerUpModel(coin);

        coin.onInitBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.coin));

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
        this.gameView.getGameActivity().increaseCoin();
    }
}
