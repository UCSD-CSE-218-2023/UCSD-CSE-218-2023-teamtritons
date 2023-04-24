package edu.ucsd.flappycow;

public class GameOverDialogObserverImpl<T> extends GameOverDialog implements IObserver<GameActivityHandlerUpdate> {
    public GameOverDialogObserverImpl(GameActivity gameActivity) {
        super(gameActivity);
    }


    @Override
    public void onUpdate(GameActivityHandlerUpdate gameActivityHandlerUpdate) {
        gameActivityHandlerUpdate.gameActivity.setGameOverCounter(gameActivityHandlerUpdate.gameActivity.getGameOverCounter() + 1);
        gameActivityHandlerUpdate.gameActivity.gameOverDialog.init();
        gameActivityHandlerUpdate.gameActivity.gameOverDialog.show();
    }
}
