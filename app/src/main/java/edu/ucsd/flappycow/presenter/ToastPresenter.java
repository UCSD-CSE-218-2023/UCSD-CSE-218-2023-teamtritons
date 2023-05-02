package edu.ucsd.flappycow.presenter;

import edu.ucsd.flappycow.view.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.model.Toast;

public class ToastPresenter extends PowerUpPresenter{
    public ToastPresenter(GameView gameView) {
        super(gameView);

        Toast toast = new Toast(gameView.getSpeedX(), gameView.getWidth());
        setPowerUpModel(toast);

        toast.onInitBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.toast));

        setPowerUpModel(toast);
    }

    public void onCollision() {
        super.onCollision();
        gameView.changeToNyanCat();
    }
}
