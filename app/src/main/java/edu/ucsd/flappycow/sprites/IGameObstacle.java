package edu.ucsd.flappycow.sprites;

public abstract class IGameObstacle extends Sprite{

    public IGameObstacle() {
        super();
    }

    public void init(int x, int y){
        this.setX(x);
        this.setY(y);
    };
}
