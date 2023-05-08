package edu.ucsd.flappycow.presenter;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.view.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.model.Virus;

public class VirusPresenter extends PowerUpPresenter{
    public VirusPresenter(GameFacade gameFacade) {
        super(gameFacade);

        Virus virus = new Virus(gameFacade.getSpeedX(), gameFacade.getWidth());
        setPowerUpModel(virus);

        virus.onInitBitmap(Util.getScaledBitmapAlpha8(gameFacade.getGameActivity(), R.drawable.virus));

        setPowerUpModel(virus);
    }

    public void onCollision() {
        super.onCollision();
        gameFacade.decreasePoints();
        gameFacade.changeToSick();
    }
}
