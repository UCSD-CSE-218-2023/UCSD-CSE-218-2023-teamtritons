package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.sprites.Coin;
import edu.ucsd.flappycow.sprites.NyanCat;
import edu.ucsd.flappycow.sprites.PowerUp;
import edu.ucsd.flappycow.sprites.Toast;
import edu.ucsd.flappycow.sprites.Virus;

public class PowerUpPresenter {
    private PowerUp powerUpModel;
    GameView gameView;

    public PowerUpPresenter(GameView gameView){
        this.gameView = gameView;
    }

    public void draw(Canvas canvas) {
        powerUpModel.draw(canvas);
    }

    public boolean isOutOfRange() {
        return powerUpModel.isOutOfRange();
    }

    public void move(){
        powerUpModel.move(gameView.getWidth(), gameView.getHeight());
    }

    public boolean isColliding() {
        return powerUpModel.isColliding(gameView.getPlayer(), gameView.getGameActivity().getResources().getDisplayMetrics().heightPixels);
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

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }
}
