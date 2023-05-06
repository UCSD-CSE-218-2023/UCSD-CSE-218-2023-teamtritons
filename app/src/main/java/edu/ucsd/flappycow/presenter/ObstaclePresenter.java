package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.view.GameActivity;
import edu.ucsd.flappycow.view.GameView;
import edu.ucsd.flappycow.view.MainActivity;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.model.IPlayableCharacter;
import edu.ucsd.flappycow.model.Obstacle;
import edu.ucsd.flappycow.model.Spider;
import edu.ucsd.flappycow.model.WoodLog;

public class ObstaclePresenter {
    private GameView gameView;
    private Obstacle obstacleModel;

    public ObstaclePresenter(GameView gameView) {
        this.gameView = gameView;
        this.obstacleModel = createInstance();

        if (obstacleModel.getCollideSound() == -1) {
            obstacleModel.setCollideSound(GameActivity.soundPool.load(gameView.getGameActivity(), R.raw.crash, 1));
        }
        // TODO: presenter
        if (obstacleModel.getPassSound() == -1) {
            obstacleModel.setPassSound(GameActivity.soundPool.load(gameView.getGameActivity(), R.raw.pass, 1));
        }
    }


    public Obstacle createInstance() {
        Spider spider = new Spider();
        WoodLog woodLog = new WoodLog();

        spider.onInitBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.spider_full));
        woodLog.onInitBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.log_full));

        Obstacle obstacle = new Obstacle(spider, woodLog, gameView.getWidthPixels(), gameView.getHeightPixels(), gameView.getSpeedX());

        return obstacle;
    }

    public void draw(Canvas canvas) {
        obstacleModel.draw(canvas);
    }

    public boolean isPassed() {
       return obstacleModel.isPassed(gameView.getSpeedX());
    }
    public boolean isAlreadyPassed() {
        return obstacleModel.isAlreadyPassed();
    }

    public void onPass() {
        if(!obstacleModel.isAlreadyPassed()) {
            GameActivity.soundPool.play(obstacleModel.getPassSound(), MainActivity.volume / obstacleModel.getSoundVolumeDivider(), MainActivity.volume / obstacleModel.getSoundVolumeDivider(), 0, 0, 1);
            gameView.increasePoints();
        }

        obstacleModel.onPass();
    }

    public boolean isOutOfRange() {
        return obstacleModel.isOutOfRange();
    }

    public boolean isColliding(IPlayableCharacter playableCharacter) {
        return obstacleModel.isColliding(playableCharacter, gameView.getHeightPixels());
    }

    public void onCollision() {
        obstacleModel.onCollision();
        GameActivity.soundPool.play(obstacleModel.getCollideSound(), MainActivity.volume / obstacleModel.getSoundVolumeDivider(), MainActivity.volume / obstacleModel.getSoundVolumeDivider(), 0, 0, 1);
    }

    public void setSpeedX(float speedX) {
        obstacleModel.setSpeedX(speedX);
    }

    public void move(){
        obstacleModel.move(gameView.getWidth(), gameView.getHeight());
    }
}
