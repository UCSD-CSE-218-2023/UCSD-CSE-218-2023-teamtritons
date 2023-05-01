package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
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
        Spider spider = new Spider();
        WoodLog woodLog = new WoodLog();

        Obstacle obstacle = new Obstacle(spider, woodLog, gameView.getSpeedX(), gameView.getGameActivity().getResources().getDisplayMetrics().heightPixels, gameView.getGameActivity().getResources().getDisplayMetrics().widthPixels);

        spider.setBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.spider_full));
        woodLog.setBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.log_full));

        spider.onInitBitmap();
        woodLog.onInitBitmap();
        obstacle.onInitBitmap();

        return obstacle;
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

    public boolean isColliding(IPlayableCharacter playableCharacter, int heightPixels) {
        return obstacleModel.isColliding(playableCharacter, heightPixels);
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
