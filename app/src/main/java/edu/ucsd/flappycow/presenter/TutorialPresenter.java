package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.view.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.model.Tutorial;

public class TutorialPresenter {

    private Tutorial tutorial;  // MODEL
    private GameFacade gameFacade;  // VIEW

    public TutorialPresenter(GameFacade gameFacade){
        tutorial= new Tutorial();
        tutorial.onInitBitmap(Util.getScaledBitmapAlpha8(gameFacade.getGameActivity(), R.drawable.tutorial));
        this.gameFacade = gameFacade;
    }

    public boolean isTutorialIsShown(){
        return tutorial.isTutorialIsShown();
    }

    public void setTutorialIsShown(Boolean tutorialIsShown) {
        tutorial.setTutorialIsShown(tutorialIsShown);
    }

    public void move() {
        tutorial.move(gameFacade.getWidth(), gameFacade.getHeight());
    }

    public void draw(Canvas canvas) {
        tutorial.draw(canvas);
    }

//    public void showTutorial() {
////        gameView.getPlayableCharacterPresenter().move();
//        gameFacade.getPlayer().move(gameFacade.getWidth(), gameFacade.getHeight());
//        gameFacade.getPauseButton().move();
//        while (!gameFacade.getHolder().getSurface().isValid()) {
//            /*wait*/
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        Canvas canvas = gameFacade.getCanvas();
//        gameFacade.drawCanvas(canvas, true);
//
////        tutorial.move(gameView.getWidth(), gameView.getHeight());
//        tutorial.move(gameFacade.getWidth(), gameFacade.getHeight());
//        tutorial.draw(canvas);
//        gameFacade.gameView.getHolder().unlockCanvasAndPost(canvas);
//    }
}
