package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.factory.TutorialFactory;
import edu.ucsd.flappycow.util.Util;
import edu.ucsd.flappycow.model.Tutorial;

public class TutorialPresenter {

    private Tutorial tutorial;  // MODEL
    private GameFacade gameFacade;  // VIEW

    public TutorialPresenter(GameFacade gameFacade){
        tutorial= TutorialFactory.getInstance(edu.ucsd.flappycow.enums.Tutorial.TUTORIAL);
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
}
