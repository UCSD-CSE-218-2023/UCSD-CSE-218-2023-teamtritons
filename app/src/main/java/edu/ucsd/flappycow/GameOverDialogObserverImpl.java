package edu.ucsd.flappycow;

import edu.ucsd.flappycow.consts.ApplicationConstants;

public class GameOverDialogObserverImpl<T> extends GameOverDialog implements IObserver<GameActivityHandlerUpdate> {
    public GameOverDialogObserverImpl(GameActivity gameActivity) {
        super(gameActivity);
    }


    @Override
    public void onUpdate(GameActivityHandlerUpdate gameActivityHandlerUpdate) {
        if (gameActivityHandlerUpdate.msgType == ApplicationConstants.SHOW_GAME_OVER_DIALOG) {
            gameActivityHandlerUpdate.gameActivity.setGameOverCounter(gameActivityHandlerUpdate.gameActivity.getGameOverCounter() + 1);
            gameActivityHandlerUpdate.gameActivity.gameOverDialog.init();
            gameActivityHandlerUpdate.gameActivity.gameOverDialog.show();
        }
    }
}
