package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.GameActivity;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.MainActivity;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.sprites.IPlayableCharacter;
import edu.ucsd.flappycow.sprites.Obstacle;
import edu.ucsd.flappycow.sprites.Spider;
import edu.ucsd.flappycow.sprites.WoodLog;

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
        Spider spider = new Spider(gameView, gameView.getGameActivity());
        WoodLog woodLog = new WoodLog(gameView, gameView.getGameActivity());

        spider.onInitBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.spider_full));
        woodLog.onInitBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.log_full));

        Obstacle obstacle = new Obstacle(gameView, gameView.getGameActivity(), spider, woodLog, gameView.getGameActivity().getResources().getDisplayMetrics().widthPixels, gameView.getGameActivity().getResources().getDisplayMetrics().heightPixels, gameView.getSpeedX());

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
            gameView.getGameActivity().increasePoints();
        }

        obstacleModel.onPass();
    }

    public boolean isOutOfRange() {
        return obstacleModel.isOutOfRange();
    }

    public boolean isColliding(IPlayableCharacter playableCharacter) {
        return obstacleModel.isColliding(playableCharacter, gameView.getGameActivity().getResources().getDisplayMetrics().heightPixels);
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
