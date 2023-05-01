package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.sprites.Accessory;
import edu.ucsd.flappycow.sprites.Cow;
import edu.ucsd.flappycow.sprites.IPlayableCharacter;
import edu.ucsd.flappycow.sprites.NyanCat;
import edu.ucsd.flappycow.sprites.Rainbow;

public class PlayableCharacterPresenter {
    private IPlayableCharacter playableCharacterModel;
    private GameView gameView;

    public PlayableCharacterPresenter(GameView gameView, String type) {
        this.gameView = gameView;
        this.playableCharacterModel = createInstance(type);
    }


    private IPlayableCharacter createInstance(String type) {
        // depending on parameters create object
        if(type.equals(ApplicationConstants.COW)) {
            return new Cow(new Accessory(), gameView.getHeight(), gameView.getWidth(), gameView.getGameActivity().getResources().getDisplayMetrics().heightPixels);
        } else if (type.equals(ApplicationConstants.NYAN_CAT)) {
            return new NyanCat(new Rainbow(), gameView.getHeight(), gameView.getWidth(), gameView.getGameActivity().getResources().getDisplayMetrics().heightPixels);
        }
        return null;
    }

    public void move() {
        playableCharacterModel.move(gameView.getHeight(), gameView.getWidth());
    }

    public void setX(int x){
        playableCharacterModel.setX(x);
    }

    public void setY(int y) {
        playableCharacterModel.setY(y);
    }

    public void revive() {
        playableCharacterModel.revive(gameView.getHeight(), gameView.getWidth());
    }
    public boolean isDead() {
        return playableCharacterModel.isDead();
    }

    public void onTap(){
        playableCharacterModel.onTap(gameView.getHeight());
    }

    public void draw(Canvas canvas) {
        playableCharacterModel.draw(canvas);
    }

    public void dead() {
        playableCharacterModel.dead(gameView.getHeight());
    }

    public boolean isTouchingGround() {
        return playableCharacterModel.isTouchingGround(gameView.getHeight());
    }

    public boolean isTouchingEdge() {
        return playableCharacterModel.isTouchingEdge(gameView.getHeight());
    }

    public void setSpeedX(float speedX) {
        playableCharacterModel.setSpeedX(speedX);
    }

    public void setSpeedY(float speedY) {
        playableCharacterModel.setSpeedY(speedY);
    }

    public void wearMask() {
        playableCharacterModel.wearMask();
    }

    public IPlayableCharacter getPlayer() {
        return playableCharacterModel;
    }

}
