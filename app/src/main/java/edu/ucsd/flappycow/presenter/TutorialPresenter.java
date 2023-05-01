package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.sprites.Tutorial;

public class TutorialPresenter {

    private Tutorial tutorial;
    private GameView gameView;

    public TutorialPresenter(GameView gameView){
        tutorial= new Tutorial();
        this.gameView = gameView;
    }

    public boolean isTutorialIsShown(){
        return tutorial.isTutorialIsShown();
    }

    public void setTutorialIsShown(Boolean tutorialIsShown) {
        tutorial.setTutorialIsShown(tutorialIsShown);
    }

    public void showTutorial() {
        gameView.getPlayableCharacterPresenter().move();
        gameView.getPauseButton().move(gameView.getHeight(), gameView.getWidth());
        while (!gameView.getHolder().getSurface().isValid()) {
            /*wait*/
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Canvas canvas = gameView.getCanvas();
        gameView.drawCanvas(canvas, true);

        tutorial.move(gameView.getWidth(), gameView.getHeight());
        tutorial.draw(canvas);
        gameView.getHolder().unlockCanvasAndPost(canvas);
    }
}
