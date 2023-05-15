package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.enums.GameLevel;
import edu.ucsd.flappycow.factory.AbstractFactory;
import edu.ucsd.flappycow.factory.GameLevelFactoryProvider;
import edu.ucsd.flappycow.model.IPowerUp;

public class PowerUpPresenter {
    private IPowerUp powerUpModel;
    GameFacade gameFacade;

    AbstractFactory abstractFactory;

    public PowerUpPresenter(GameFacade gameFacade){
        abstractFactory = GameLevelFactoryProvider.getFactory(GameLevel.LEVEL_1);
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

    public IPowerUp getPowerUpModel() {
        return powerUpModel;
    }

    public void setPowerUpModel(IPowerUp powerUpModel) {
        this.powerUpModel = powerUpModel;
    }
}
