package edu.ucsd.flappycow.factory;

import edu.ucsd.flappycow.enums.GameButton;
import edu.ucsd.flappycow.enums.GameLevel;
import edu.ucsd.flappycow.model.IGameButton;
import edu.ucsd.flappycow.model.PauseButton;

public class ButtonFactory {

    public static IGameButton getInstance(GameButton type) {
        IGameButton gameButton = null;
        if (type.equals(GameButton.PAUSEBUTTON)) {
            gameButton = new PauseButton();
        }
        return gameButton;
    }
}
