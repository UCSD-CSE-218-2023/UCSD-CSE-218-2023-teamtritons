package edu.ucsd.flappycow;

import android.os.Handler;
import android.os.Message;
import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.presenter.GameActivityHandlerPresenter;

public class GameActivityHandler extends Handler {

    private ISubjectImpl<GameActivityHandlerUpdate> GameActivityHandlerSub = new SubjectImpl<>();

    public GameActivity getGameActivity() {
        return gameActivity;
    }

    private GameActivityHandlerPresenter gameActivityHandlerPresenter;
    private final GameActivity gameActivity;

    public GameActivityHandler(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
        IObserver ToastObserver = new ToastObserverImpl();
        IObserver GameOverDialogObserver = new GameOverDialogObserverImpl(gameActivity);

//        GameActivityHandlerSub.register(GameOverDialogObserver);
        gameActivityHandlerPresenter = new GameActivityHandlerPresenter(GameActivityHandlerSub);
        GameActivityHandlerSub.register(gameActivityHandlerPresenter);
    }

    @Override
    public void handleMessage(Message msg) {

        switch (msg.what) {
            case ApplicationConstants.SHOW_GAME_OVER_DIALOG:
                showGameOverDialog(msg);
                break;
            case ApplicationConstants.SHOW_TOAST:
                GameActivityHandlerSub.notify(new GameActivityHandlerUpdate(/**getGameActivity(),**/ msg, ApplicationConstants.SHOW_TOAST));
                break;
            case ApplicationConstants.SHOW_AD:
                showAdIfAvailable(msg);
                break;
        }
    }

    private void showAdIfAvailable(Message msg) {
        if (gameActivity.getInterstitial() == null) {
            showGameOverDialog(msg);
        } else {
            gameActivity.getInterstitial().show(gameActivity);
        }
    }

    private void showGameOverDialog(Message msg) {
        GameActivityHandlerSub.notify(new GameActivityHandlerUpdate(/**getGameActivity(),**/ msg, ApplicationConstants.SHOW_GAME_OVER_DIALOG));
    }
}