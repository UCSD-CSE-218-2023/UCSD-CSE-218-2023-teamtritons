package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.sprites.Accessory;
import edu.ucsd.flappycow.sprites.Cow;
import edu.ucsd.flappycow.sprites.IGameObstacle;
import edu.ucsd.flappycow.sprites.IPlayableCharacter;
import edu.ucsd.flappycow.sprites.NyanCat;
import edu.ucsd.flappycow.sprites.Obstacle;
import edu.ucsd.flappycow.sprites.Rainbow;
import edu.ucsd.flappycow.sprites.Spider;
import edu.ucsd.flappycow.sprites.WoodLog;

public class ObstaclePresenter {
//    private IGameObstacle playableCharacterModel;
    private GameView gameView;
    private Obstacle obstacleModel;

    public ObstaclePresenter(GameView gameView) {
        this.gameView = gameView;
        this.obstacleModel = createInstance();
    }


    public Obstacle createInstance() {
        return new Obstacle(new Spider(), new WoodLog(), gameView.getSpeedX(), gameView.getGameActivity().getResources().getDisplayMetrics().heightPixels, gameView.getGameActivity().getResources().getDisplayMetrics().widthPixels);
    }

    public void draw(Canvas canvas) {
        obstacleModel.draw(canvas);
    }

    public boolean isPassed(int viewPlayerX) {
        return obstacleModel.isPassed(viewPlayerX);
    }

    public boolean isAlreadyPassed() {
        return obstacleModel.isAlreadyPassed();
    }

    public void onPass() {
        obstacleModel.onPass();
    }

    public boolean isOutOfRange() {
        return obstacleModel.isOutOfRange();
    }

    public boolean isColliding(IPlayableCharacter playableCharacter) {
        return obstacleModel.isColliding(playableCharacter);
    }

    public void onCollision() {
        obstacleModel.onCollision();
    }

    public void setSpeedX(float speedX) {
        obstacleModel.setSpeedX(speedX);
    }

    public void move() {
        obstacleModel.move(gameView.getHeight(), gameView.getWidth());
    }
}
