package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.sprites.Background;
import edu.ucsd.flappycow.sprites.Frontground;
import edu.ucsd.flappycow.sprites.IGround;


public class GroundPresenter {

    private IGround ground;

    public GroundPresenter(String type) {

        this.ground = createInstance(type);
    }

    private IGround createInstance(String type) {
        // depending on parameters create object
        if(type.equals(ApplicationConstants.BACKGROUND)) {
            return new Background();
        } else if (type.equals(ApplicationConstants.FRONTGROUND)) {
            return new Frontground();
        }
        return null;
    }
    // draw, move, setSpeedX,

    public void move(int height, int width){
        ground.move(height, width);
    }

    public void setSpeedX(int speedX){
        ground.setSpeedX(speedX);
    }

    public void draw(Canvas canvas){
        ground.draw(canvas);
    }
}
