package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.util.Util;
import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.model.Accessory;
import edu.ucsd.flappycow.model.Cow;
import edu.ucsd.flappycow.model.IAccessory;
import edu.ucsd.flappycow.model.IPlayableCharacter;
import edu.ucsd.flappycow.model.NyanCat;
import edu.ucsd.flappycow.model.Rainbow;

public class PlayableCharacterPresenter {
    private IPlayableCharacter playableCharacterModel;
    private GameFacade gameFacade;

    private IAccessory accessory;

    public PlayableCharacterPresenter(GameFacade gameFacade, String type) {
        this.gameFacade = gameFacade;
        this.playableCharacterModel = createInstance(type);

    }


    private IPlayableCharacter createInstance(String type) {
        // depending on parameters create object
        if(type.equals(ApplicationConstants.COW)) {
            accessory = new Accessory();
            Cow cow = new Cow(gameFacade.getWidth(), gameFacade.getHeight(), gameFacade.getHeightPixels(), accessory);
            cow.onInitBitmap(Util.getScaledBitmapAlpha8(gameFacade.getGameActivity(), R.drawable.cow));
            return cow;
        } else if (type.equals(ApplicationConstants.NYAN_CAT)) {
            Rainbow rainbow = new Rainbow();
            rainbow.onInitBitmap(Util.getScaledBitmapAlpha8(gameFacade.getGameActivity(), R.drawable.rainbow));

            NyanCat nyanCat = new NyanCat(gameFacade.getWidth(), gameFacade.getHeight(), gameFacade.getHeightPixels(), rainbow);
            nyanCat.onInitBitmap(Util.getScaledBitmapAlpha8(gameFacade.getGameActivity(), R.drawable.nyan_cat));
            return nyanCat;
        }
        return null;
    }

    public void move() {
        playableCharacterModel.move(gameFacade.getWidth(), gameFacade.getHeight());
    }

    public void setX(int x){
        playableCharacterModel.setX(x);
    }

    public void setY(int y) {
        playableCharacterModel.setY(y);
    }

    public void revive() {
        playableCharacterModel.revive(gameFacade.getWidth(), gameFacade.getHeight());
    }
    public boolean isDead() {
        return playableCharacterModel.isDead();
    }

    public void onTap(){
        playableCharacterModel.onTap(gameFacade.getHeight());
    }

    public void draw(Canvas canvas) {
        playableCharacterModel.draw(canvas);
    }

    public void dead() {
//        playableCharacterModel.dead(gameView.getHeight());
        playableCharacterModel.dead(gameFacade.getHeight());
    }

    public boolean isTouchingGround() {
        return playableCharacterModel.isTouchingGround(gameFacade.getHeight());
    }

    public boolean isTouchingEdge() {
        return playableCharacterModel.isTouchingEdge(gameFacade.getHeight());
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

    public IAccessory getAccessory() {
        return accessory;
    }

    public void setAccessory(IAccessory accessory) {
        this.accessory = accessory;
    }

    public IPlayableCharacter getPlayableCharacterModel() {
        return playableCharacterModel;
    }

    public void setPlayableCharacterModel(IPlayableCharacter playableCharacterModel) {
        this.playableCharacterModel = playableCharacterModel;
    }

    public GameFacade getGameFacade() {
        return gameFacade;
    }
}
