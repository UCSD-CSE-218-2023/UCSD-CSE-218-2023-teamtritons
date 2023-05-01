package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.sprites.Coin;
import edu.ucsd.flappycow.sprites.NyanCat;
import edu.ucsd.flappycow.sprites.PowerUp;
import edu.ucsd.flappycow.sprites.Toast;
import edu.ucsd.flappycow.sprites.Virus;

public class PowerUpPresenter {
    // draw, move, add to list, remove from list, clear list, isOutOfRange, get powerup at specific index from list, isColliding, onCollision,
    private List<PowerUp> powerUps;
    GameView gameView;

    public PowerUpPresenter(GameView gameView){
        powerUps = new ArrayList<PowerUp>();
        this.gameView = gameView;
    }

    private void add(PowerUp powerUp){
        powerUps.add(powerUp);
    }

    private int getSize(){
        return powerUps.size();
    }

    public void remove(int i){
        powerUps.remove(i);
    }

    public void draw(Canvas canvas){
        for (PowerUp p : powerUps) {
            p.draw(canvas);
        }
    }

    private boolean isOutOfRange(int i){
        return this.powerUps.get(i).isOutOfRange();
    }

    public void move(){
        for (PowerUp p : powerUps) {
            p.move(gameView.getHeight(), gameView.getWidth());
        }
    }

    public void clear(){
        powerUps.clear();
    }

    public void checkCollision(){
        for (int i = 0; i < powerUps.size(); i++) {
            if (powerUps.get(i).isColliding(gameView.getPlayer())) {
                powerUps.get(i).onCollision();
                powerUps.remove(i);
                i--;
            }
        }
    }

    public void checkOutOfRange(){
        for (int i = 0; i < powerUps.size(); i++) {
            if ( this.isOutOfRange(i) ) {
                this.remove(i);
                i--;
            }
        }
    }

    public void createPowerUp(int accomplishmentBoxPoints){
        // Toast
        if (accomplishmentBoxPoints >= Toast.POINTS_TO_TOAST /*&& powerUps.size() < 1*/ && !(gameView.getPlayer() instanceof NyanCat)) {
            // If no powerUp is present and you have more than / equal 42 points
            if (accomplishmentBoxPoints == Toast.POINTS_TO_TOAST) {    // First time 100 % chance
                this.add(new Toast(gameView.getWidth(), gameView.getHeight(), gameView.getSpeedX()));
            } else if (Math.random() * 100 < 33) {    // 33% chance
                this.add(new Toast(gameView.getWidth(), gameView.getHeight(), gameView.getSpeedX()));
            }
        }

        if ((this.getSize() < 1) && (Math.random() * 100 < 20)) {
            // If no powerUp is present and 20% chance
            this.add(new Coin(gameView.getWidth(), gameView.getHeight(), gameView.getSpeedX()));
        }

        if ((this.getSize() < 1) && (Math.random() * 100 < 10)) {
            // If no powerUp is present and 10% chance (if also no coin)
            this.add(new Virus(gameView.getWidth(), gameView.getHeight(), gameView.getSpeedX()));
        }
    }

}
