package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
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
            Accessory accessory = new Accessory(gameView, gameView.getGameActivity());
            Cow cow = new Cow(gameView, gameView.getGameActivity(), accessory);
            cow.onInitBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.cow));
            return cow;
        } else if (type.equals(ApplicationConstants.NYAN_CAT)) {
//            Rainbow rainbow = new Rainbow();
            Rainbow rainbow = new Rainbow(gameView, gameView.getGameActivity());
            NyanCat nyanCat = new NyanCat(gameView, gameView.getGameActivity(), rainbow);
//            NyanCat nyanCat = new NyanCat(rainbow, gameView.getHeight(), gameView.getWidth(), gameView.getGameActivity().getResources().getDisplayMetrics().heightPixels);
//            rainbow.setBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.rainbow));
//            nyanCat.setBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.nyan_cat));
//            rainbow.onInitBitmap();
//            nyanCat.onInitBitmap();
//            return nyanCat;
            nyanCat.onInitBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.nyan_cat));
            return nyanCat;
        }
        return null;
    }

    public void move() {
//        playableCharacterModel.move(gameView.getHeight(), gameView.getWidth());
        playableCharacterModel.move();
    }

    public void setX(int x){
        playableCharacterModel.setX(x);
    }

    public void setY(int y) {
        playableCharacterModel.setY(y);
    }

    public void revive() {
//        playableCharacterModel.revive(gameView.getHeight(), gameView.getWidth());
        playableCharacterModel.revive();
    }
    public boolean isDead() {
        return playableCharacterModel.isDead();
    }

    public void onTap(){
//        playableCharacterModel.onTap(gameView.getHeight());
        playableCharacterModel.onTap();
    }

    public void draw(Canvas canvas) {
        playableCharacterModel.draw(canvas);
    }

    public void dead() {
//        playableCharacterModel.dead(gameView.getHeight());
        playableCharacterModel.dead();
    }

    public boolean isTouchingGround() {
//        return playableCharacterModel.isTouchingGround(gameView.getHeight());
        return playableCharacterModel.isTouchingGround();
    }

    public boolean isTouchingEdge() {
//        return playableCharacterModel.isTouchingEdge(gameView.getHeight());
        return playableCharacterModel.isTouchingEdge();
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
