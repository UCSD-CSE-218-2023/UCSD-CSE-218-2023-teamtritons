package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import edu.ucsd.flappycow.sprites.Coin;
import edu.ucsd.flappycow.sprites.IPlayableCharacter;
import edu.ucsd.flappycow.sprites.NyanCat;
import edu.ucsd.flappycow.sprites.PowerUp;
import edu.ucsd.flappycow.sprites.Toast;
import edu.ucsd.flappycow.sprites.Virus;

public class PowerUpPresenter {
    // draw, move, add to list, remove from list, clear list, isOutOfRange, get powerup at specific index from list, isColliding, onCollision,
    private List<PowerUp> powerUps;

    PowerUpPresenter(){
        powerUps = new ArrayList<PowerUp>();
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

    public void move(int viewHeight, int viewWidth){
        for (PowerUp p : powerUps) {
            p.move(viewHeight, viewWidth);
        }
    }

    public void clear(){
        powerUps.clear();
    }

    public void checkCollision(IPlayableCharacter player){
        for (int i = 0; i < powerUps.size(); i++) {
            if (powerUps.get(i).isColliding(player)) {
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

    public void createPowerUp(int accomplishmentBoxPoints, IPlayableCharacter player, int viewHeight, int viewWidth, int viewSpeedX){
        // Toast
        if (accomplishmentBoxPoints >= Toast.POINTS_TO_TOAST /*&& powerUps.size() < 1*/ && !(player instanceof NyanCat)) {
            // If no powerUp is present and you have more than / equal 42 points
            if (accomplishmentBoxPoints == Toast.POINTS_TO_TOAST) {    // First time 100 % chance
                this.add(new Toast(viewWidth, viewHeight, viewSpeedX));
            } else if (Math.random() * 100 < 33) {    // 33% chance
                this.add(new Toast(viewWidth, viewHeight, viewSpeedX));
            }
        }

        if ((this.getSize() < 1) && (Math.random() * 100 < 20)) {
            // If no powerUp is present and 20% chance
            this.add(new Coin(viewWidth, viewHeight, viewSpeedX));
        }

        if ((this.getSize() < 1) && (Math.random() * 100 < 10)) {
            // If no powerUp is present and 10% chance (if also no coin)
            this.add(new Virus(viewWidth, viewHeight, viewSpeedX));
        }
    }

}
