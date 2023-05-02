package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import java.util.HashMap;
import java.util.Map;

import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.sprites.Background;
import edu.ucsd.flappycow.sprites.Frontground;
import edu.ucsd.flappycow.sprites.IGround;


public class GroundPresenter {

    private IGround ground;
    private GameView gameView;

    public GroundPresenter(GameView gameView, String type) {

        this.gameView = gameView;
        this.ground = createInstance(type);
    }


    private IGround createInstance(String type) {
        // depending on parameters create object
        if(type.equals(ApplicationConstants.BACKGROUND)) {
            Background background = new Background(gameView, gameView.getGameActivity());
            background.onInitBitmap(Util.getDownScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.bg));
            return background;
        } else if (type.equals(ApplicationConstants.FRONTGROUND)) {
            Frontground frontground = new Frontground(gameView, gameView.getGameActivity());
            frontground.onInitBitmap(Util.getDownScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.fg));
            return frontground;
        }
        return null;
    }
    // draw, move, setSpeedX,

//    public void move(int height, int width){
//        ground.move(height, width);
//    }

    public void move(){
        ground.move(gameView.getWidth(), gameView.getHeight());
    }

    public void setSpeedX(int speedX){
        ground.setSpeedX(speedX);
    }

    public void draw(Canvas canvas){
        ground.draw(canvas);
    }
}
