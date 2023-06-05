package edu.ucsd.flappycow.factory;

import static edu.ucsd.flappycow.util.Contract.ensure;
import static edu.ucsd.flappycow.util.Contract.require;

import edu.ucsd.flappycow.enums.GameButton;
import edu.ucsd.flappycow.enums.GameLevel;
import edu.ucsd.flappycow.model.IGameButton;
import edu.ucsd.flappycow.model.PauseButton;

public class ButtonFactory {
    public static IGameButton getInstance(GameButton type) {
        require(type != null, "Type is not null");
        require(type instanceof edu.ucsd.flappycow.enums.GameButton, "Type should be of type GameButton");
        IGameButton gameButton = null;
        if (type.equals(GameButton.PAUSEBUTTON)) {
            gameButton = new PauseButton();
        }
        ensure(gameButton != null, "gameButton should not be NULL");
        return gameButton;
    }
}
