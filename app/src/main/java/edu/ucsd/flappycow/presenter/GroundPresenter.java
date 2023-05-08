package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.util.Util;
import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.model.Background;
import edu.ucsd.flappycow.model.Frontground;
import edu.ucsd.flappycow.model.IGround;


public class GroundPresenter {

    private IGround ground;
    private GameFacade gameFacade;

    public GroundPresenter(GameFacade gameFacade, String type) {

        this.gameFacade = gameFacade;
        this.ground = createInstance(type);
    }


    private IGround createInstance(String type) {
        // depending on parameters create object
        if(type.equals(ApplicationConstants.BACKGROUND)) {
            Background background = new Background();
            background.onInitBitmap(Util.getDownScaledBitmapAlpha8(gameFacade.getGameActivity(), R.drawable.bg));
            return background;
        } else if (type.equals(ApplicationConstants.FRONTGROUND)) {
            Frontground frontground = new Frontground();
            frontground.onInitBitmap(Util.getDownScaledBitmapAlpha8(gameFacade.getGameActivity(), R.drawable.fg));
            return frontground;
        }
        return null;
    }
    // draw, move, setSpeedX,

//    public void move(int height, int width){
//        ground.move(height, width);
//    }

    public void move(){
        ground.move(gameFacade.getWidth(), gameFacade.getHeight());
    }

    public void setSpeedX(int speedX){
        ground.setSpeedX(speedX);
    }

    public void draw(Canvas canvas){
        ground.draw(canvas);
    }
}
