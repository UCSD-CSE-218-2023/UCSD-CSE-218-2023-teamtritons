package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.view.GameView;
import edu.ucsd.flappycow.model.PowerUp;

public class PowerUpPresenter {
    private PowerUp powerUpModel;
    GameFacade gameFacade;

    public PowerUpPresenter(GameFacade gameFacade){
        this.gameFacade = gameFacade;
    }

    public void draw(Canvas canvas) {
        powerUpModel.draw(canvas);
    }

    public boolean isOutOfRange() {
        return powerUpModel.isOutOfRange();
    }

    public void move(){
        powerUpModel.move(gameFacade.getWidth(), gameFacade.getHeight());
    }

    public boolean isColliding() {
        return powerUpModel.isColliding(gameFacade.getPlayer(), gameFacade.getHeightPixels());
    }

    public void onCollision() {
        powerUpModel.onCollision();
    }

    public boolean checkOutOfRange(){
        return powerUpModel.isOutOfRange();
    }

    public PowerUp getPowerUpModel() {
        return powerUpModel;
    }

    public void setPowerUpModel(PowerUp powerUpModel) {
        this.powerUpModel = powerUpModel;
    }

//    public GameView getGameFacade() {
//        return gameFacade;
//    }
//
//    public void setGameFacade(GameView gameFacade) {
//        this.gameFacade = gameFacade;
//    }
}
