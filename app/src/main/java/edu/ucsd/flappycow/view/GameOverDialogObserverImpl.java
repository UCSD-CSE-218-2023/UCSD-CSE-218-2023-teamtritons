package edu.ucsd.flappycow.view;

import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.util.IObserver;

public class GameOverDialogObserverImpl<T> extends GameOverDialog implements IObserver<GameActivityHandlerUpdate> {
    public GameOverDialogObserverImpl(GameActivity gameActivity) {
        super(gameActivity);
    }


    @Override
    public void onUpdate(GameActivityHandlerUpdate gameActivityHandlerUpdate) {
        if (gameActivityHandlerUpdate.msgType == ApplicationConstants.SHOW_GAME_OVER_DIALOG) {
            gameActivityHandlerUpdate.gameActivity.setGameOverCounter(gameActivityHandlerUpdate.gameActivity.getGameOverCounter() + 1);
            gameActivityHandlerUpdate.gameActivity.gameFacade.getGameOverDialog().init();
            gameActivityHandlerUpdate.gameActivity.gameFacade.getGameOverDialog().show();
        }
    }
}
