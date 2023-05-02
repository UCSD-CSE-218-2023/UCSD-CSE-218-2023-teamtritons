package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.GameView;
import edu.ucsd.flappycow.R;
import edu.ucsd.flappycow.Util;
import edu.ucsd.flappycow.sprites.IGameButton;
import edu.ucsd.flappycow.sprites.PauseButton;

public class ButtonPresenter {

    IGameButton gameButton;
    GameView gameView;

    public ButtonPresenter(GameView gameView) {
        PauseButton pauseButton = new PauseButton(gameView, gameView.getGameActivity());
        this.gameButton = pauseButton;
        this.gameView = gameView;
        pauseButton.onInitBitmap(Util.getDownScaledBitmapAlpha8(gameView.getGameActivity(), R.drawable.pause_button));
    }
    // isTouching, move, draw

    public boolean isTouching(int x, int y){
        return gameButton.isTouching(x,y);
    }

    public void move(){
        gameButton.move(gameView.getWidth(), gameView.getHeight());
    }

    public void draw(Canvas canvas){
        gameButton.draw(canvas);
    }


}
