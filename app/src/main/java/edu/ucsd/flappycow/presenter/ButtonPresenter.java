package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.GameFacade;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.enums.GameButton;
import edu.ucsd.flappycow.enums.GameLevel;
import edu.ucsd.flappycow.factory.AbstractFactory;
import edu.ucsd.flappycow.factory.GameLevelFactoryProvider;
import edu.ucsd.flappycow.util.Util;
import edu.ucsd.flappycow.model.IGameButton;
import edu.ucsd.flappycow.model.PauseButton;

public class ButtonPresenter {
    IGameButton gameButton;
    GameFacade gameFacade;
    AbstractFactory abstractFactory;

    public ButtonPresenter(GameFacade gameFacade) {
        abstractFactory = GameLevelFactoryProvider.getFactory(GameLevel.LEVEL_1);
        PauseButton pauseButton = (PauseButton) abstractFactory.createGameButton(GameButton.PAUSEBUTTON);
        this.gameButton = pauseButton;
        this.gameFacade = gameFacade;
        pauseButton.onInitBitmap(Util.getDownScaledBitmapAlpha8(gameFacade.getGameActivity(), R.drawable.pause_button));
    }
    // isTouching, move, draw

    public boolean isTouching(int x, int y){
        return gameButton.isTouching(x,y);
    }

    public void move(){
        gameButton.move(gameFacade.getWidth(), gameFacade.getHeight());
    }

    public void draw(Canvas canvas){
        gameButton.draw(canvas);
    }


}
