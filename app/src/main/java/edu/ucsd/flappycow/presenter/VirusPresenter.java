package edu.ucsd.flappycow.presenter;

import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.sprites.Coin;
import edu.ucsd.flappycow.sprites.Toast;
import edu.ucsd.flappycow.sprites.Virus;

public class VirusPresenter extends PowerUpPresenter{
    public VirusPresenter(GameView gameView) {
        super(gameView);

        Virus virus = new Virus(gameView, gameView.getGameActivity(), gameView.getSpeedX(), gameView.getWidth());
        setPowerUpModel(virus);

        virus.onInitBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.virus));

        setPowerUpModel(virus);
    }

    public void onCollision() {
        super.onCollision();
        gameView.getGameActivity().decreasePoints();
        gameView.changeToSick();
    }
}
