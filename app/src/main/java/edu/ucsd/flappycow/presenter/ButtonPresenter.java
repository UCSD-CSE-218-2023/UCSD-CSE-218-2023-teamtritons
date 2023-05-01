package edu.ucsd.flappycow.presenter;

import android.graphics.Canvas;

import edu.ucsd.flappycow.sprites.IGameButton;
import edu.ucsd.flappycow.sprites.PauseButton;

public class ButtonPresenter {

    IGameButton gameButton;

    public ButtonPresenter() {
        this.gameButton = new PauseButton();
    }
    // isTouching, move, draw

    public boolean isTouching(int x, int y){
        return gameButton.isTouching(x,y);
    }

    public void move(int height, int width){
        gameButton.move(height, width);
    }

    public void draw(Canvas canvas){
        gameButton.draw(canvas);
    }


}
