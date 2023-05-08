package edu.ucsd.flappycow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.ucsd.flappycow.presenter.ButtonPresenter;
import edu.ucsd.flappycow.presenter.GroundPresenter;
import edu.ucsd.flappycow.presenter.ObstaclePresenter;
import edu.ucsd.flappycow.presenter.PlayableCharacterPresenter;
import edu.ucsd.flappycow.presenter.PowerUpPresenter;
import edu.ucsd.flappycow.presenter.TutorialPresenter;
import edu.ucsd.flappycow.view.GameActivity;
import edu.ucsd.flappycow.view.GameView;

public
class GameFacade {

    private GameView gameView;
    private List<ObstaclePresenter> obstaclePresenters;
    private TutorialPresenter tutorialPresenter;
    private ButtonPresenter pauseButton;
    private Map<String, GroundPresenter> groundPresenterMap;
    private PlayableCharacterPresenter playableCharacterPresenter;
    private List<PowerUpPresenter> powerUpPresenters = new ArrayList<>();
    public GameFacade(GameView gameView) {
        this.gameView = gameView;
        obstaclePresenters = new ArrayList<>();
        tutorialPresenter = new TutorialPresenter(this)
    }

    public void createObstacle() {
        if (obstaclePresenters.size() < 1) {
            obstaclePresenters.add(new ObstaclePresenter(this));
        }
    }

    public int getHeightPixels() {
        return gameView.getHeightPixels();
    }

    public int getWidthPixels() {
        return gameView.getWidthPixels();
    }

    public int getSpeedX() {
        return gameView.getSpeedX();
    }

    public GameActivity getGameActivity() {
        return gameView.getGameActivity();
    }

}
