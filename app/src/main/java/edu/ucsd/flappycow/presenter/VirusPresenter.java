package edu.ucsd.flappycow.presenter;

import edu.ucsd.flappycow.view.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.model.Virus;

public class VirusPresenter extends PowerUpPresenter{
    public VirusPresenter(GameView gameView) {
        super(gameView);

        Virus virus = new Virus(gameView.getSpeedX(), gameView.getWidth());
        setPowerUpModel(virus);

        virus.onInitBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.virus));

        setPowerUpModel(virus);
    }

    public void onCollision() {
        super.onCollision();
        gameView.decreasePoints();
        gameView.changeToSick();
    }
}
