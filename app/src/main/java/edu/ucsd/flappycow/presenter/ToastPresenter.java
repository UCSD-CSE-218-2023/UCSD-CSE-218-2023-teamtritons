package edu.ucsd.flappycow.presenter;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.util.Util;
import edu.ucsd.flappycow.model.Toast;

public class ToastPresenter extends PowerUpPresenter{
    public ToastPresenter(GameFacade gameFacade) {
        super(gameFacade);

        Toast toast = new Toast(gameFacade.getSpeedX(), gameFacade.getWidth());
        setPowerUpModel(toast);

        toast.onInitBitmap(Util.getScaledBitmapAlpha8(gameFacade.getGameActivity(), R.drawable.toast));

        setPowerUpModel(toast);
    }

    public void onCollision() {
        super.onCollision();
        gameFacade.changeToNyanCat();
    }
}
