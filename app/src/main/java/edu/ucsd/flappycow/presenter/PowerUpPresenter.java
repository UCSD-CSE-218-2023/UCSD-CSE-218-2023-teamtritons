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
    // draw, move, add to list, remove from list, clear list, isOutOfRange, get powerup at specific index from list, isColliding, onCollision,
    private List<PowerUp> powerUps; //MODEL
    GameView gameView;  // VIEW

    public PowerUpPresenter(GameView gameView){
        powerUps = new ArrayList<PowerUp>();
        this.gameView = gameView;
    }

    private void add(PowerUp powerUp){
        if(powerUp instanceof Coin)
            powerUp.onInitBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.coin));
        else if(powerUp instanceof Toast)
            powerUp.onInitBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.toast));
        else if(powerUp instanceof Virus)
            powerUp.onInitBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.virus));
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
//            p.move(gameView.getHeight(), gameView.getWidth());
            p.move();
        }
    }

    public void clear(){
        powerUps.clear();
    }

    public void checkCollision(int heightPixels){
        for (int i = 0; i < powerUps.size(); i++) {
//            if (powerUps.get(i).isColliding(gameView.getPlayer(), gameView.getGameActivity().getResources().getDisplayMetrics().heightPixels)) {
            if (powerUps.get(i).isColliding(gameView.getPlayer(), heightPixels)) {
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
            if (accomplishmentBoxPoints == Toast.POINTS_TO_TOAST || Math.random() * 100 < 33) {    // First time 100 % chance
//                Toast toast = new Toast(gameView.getWidth(), gameView.getHeight(), gameView.getSpeedX());
                Toast toast = new Toast(gameView, gameView.getGameActivity());
                this.add(toast);
                toast.setBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.toast));
//                toast.onInitBitmap();
            }
        }

        if ((this.getSize() < 1) && (Math.random() * 100 < 20)) {
            // If no powerUp is present and 20% chance
//            Coin coin = new Coin(gameView.getWidth(), gameView.getHeight(), gameView.getSpeedX());
            Coin coin = new Coin(gameView, gameView.getGameActivity());
            this.add(coin);
            coin.setBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.coin));
//            coin.onInitBitmap();
        }

        if ((this.getSize() < 1) && (Math.random() * 100 < 10)) {
            // If no powerUp is present and 10% chance (if also no coin)
//            Virus virus = new Virus(gameView.getWidth(), gameView.getHeight(), gameView.getSpeedX());
            Virus virus = new Virus(gameView, gameView.getGameActivity());
            this.add(virus);
            virus.setBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.virus));
//            virus.onInitBitmap();
        }
    }

}
