package edu.ucsd.flappycow.model;

public abstract class IGameObstacle extends Sprite{
    public void init(int x, int y){
        this.setX(x);
        this.setY(y);
    };
}
