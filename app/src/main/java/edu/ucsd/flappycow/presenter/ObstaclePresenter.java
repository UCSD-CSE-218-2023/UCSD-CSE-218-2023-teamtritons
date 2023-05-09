package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.view.GameActivity;
import edu.ucsd.flappycow.view.MainActivity;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.util.Util;
import edu.ucsd.flappycow.model.IPlayableCharacter;
import edu.ucsd.flappycow.model.Obstacle;
import edu.ucsd.flappycow.model.Spider;
import edu.ucsd.flappycow.model.WoodLog;

public class ObstaclePresenter {
    private GameFacade gameFacade;
    private Obstacle obstacleModel;

    public ObstaclePresenter(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
        this.obstacleModel = createInstance();

        if (obstacleModel.getCollideSound() == -1) {
            obstacleModel.setCollideSound(gameFacade.getGameActivity().getSoundPool().load(gameFacade.getGameActivity(), R.raw.crash, 1));
        }

        if (obstacleModel.getPassSound() == -1) {
            obstacleModel.setPassSound(gameFacade.getGameActivity().getSoundPool().load(gameFacade.getGameActivity(), R.raw.pass, 1));
        }
    }


    public Obstacle createInstance() {
        Spider spider = new Spider();
        WoodLog woodLog = new WoodLog();

        spider.onInitBitmap(Util.getScaledBitmapAlpha8(gameFacade.getGameActivity(), R.drawable.spider_full));
        woodLog.onInitBitmap(Util.getScaledBitmapAlpha8(gameFacade.getGameActivity(), R.drawable.log_full));

        Obstacle obstacle = new Obstacle(spider, woodLog, gameFacade.getWidthPixels(), gameFacade.getHeightPixels(), gameFacade.getSpeedX());

        return obstacle;
    }

    public void draw(Canvas canvas) {
        obstacleModel.draw(canvas);
    }

    public boolean isPassed() {
        return obstacleModel.isPassed(gameFacade.getSpeedX());
    }
    public boolean isAlreadyPassed() {
        return obstacleModel.isAlreadyPassed();
    }

    public void onPass() {
        if(!obstacleModel.isAlreadyPassed()) {
            gameFacade.getGameActivity().getSoundPool().play(obstacleModel.getPassSound(), MainActivity.volume / obstacleModel.getSoundVolumeDivider(), MainActivity.volume / obstacleModel.getSoundVolumeDivider(), 0, 0, 1);
            gameFacade.increasePoints();
        }

        obstacleModel.onPass();
    }

    public boolean isOutOfRange() {
        return obstacleModel.isOutOfRange();
    }

    public boolean isColliding(IPlayableCharacter playableCharacter) {
        return obstacleModel.isColliding(playableCharacter, gameFacade.getHeightPixels());
    }

    public void onCollision() {
        obstacleModel.onCollision();
        gameFacade.getGameActivity().getSoundPool().play(obstacleModel.getCollideSound(), MainActivity.volume / obstacleModel.getSoundVolumeDivider(), MainActivity.volume / obstacleModel.getSoundVolumeDivider(), 0, 0, 1);
    }

    public void setSpeedX(float speedX) {
        obstacleModel.setSpeedX(speedX);
    }

    public void move(){
        obstacleModel.move(gameFacade.getWidth(), gameFacade.getHeight());
    }
}
