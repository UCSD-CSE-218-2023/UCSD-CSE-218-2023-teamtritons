package edu.ucsd.flappycow.sprites;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;

public abstract class IGameObstacle extends Sprite{
    public void init(int x, int y){
        this.setX(x);
        this.setY(y);
    };
}
