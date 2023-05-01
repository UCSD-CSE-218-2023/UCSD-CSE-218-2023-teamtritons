package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;
import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.sprites.Tutorial;

public class TutorialPresenter {

//    private Tutorial tutorial;  // MODEL
//    private GameView gameView;  // VIEW
//
//    public TutorialPresenter(GameView gameView){
//        tutorial= new Tutorial();
//        tutorial.setBitmap(Util.getScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.tutorial));
//        tutorial.onInitBitmap();
//        this.gameView = gameView;
//    }
//
//    public boolean isTutorialIsShown(){
//        return tutorial.isTutorialIsShown();
//    }
//
//    public void setTutorialIsShown(Boolean tutorialIsShown) {
//        tutorial.setTutorialIsShown(tutorialIsShown);
//    }
//
//    public void showTutorial() {
//        gameView.getPlayableCharacterPresenter().move();
//        gameView.getPauseButton().move(gameView.getHeight(), gameView.getWidth());
//        while (!gameView.getHolder().getSurface().isValid()) {
//            /*wait*/
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        Canvas canvas = gameView.getCanvas();
//        gameView.drawCanvas(canvas, true);
//
//        tutorial.move(gameView.getWidth(), gameView.getHeight());
//        tutorial.draw(canvas);
//        gameView.getHolder().unlockCanvasAndPost(canvas);
//    }
}
