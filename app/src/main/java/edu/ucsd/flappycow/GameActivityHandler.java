package edu.ucsd.flappycow;

import android.os.Handler;
import android.os.Message;

class GameActivityHandler extends Handler {

    private ISubjectImpl<GameActivityHandlerUpdate> GameActivityHandlerSub = new GameActivityHandlerSubjectImpl<>();

    public static final int SHOW_GAME_OVER_DIALOG = 0;
    public static final int SHOW_TOAST = 1;
    public static final int SHOW_AD = 2;

    public GameActivity getGameActivity() {
        return gameActivity;
    }

    private final GameActivity gameActivity;

    public GameActivityHandler(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
        IObserver ToastObserver = new ToastObserverImpl();
        IObserver GameOverDialogObserver = new GameOverDialogObserverImpl(gameActivity);
        GameActivityHandlerSub.register(ToastObserver);
        GameActivityHandlerSub.register(GameOverDialogObserver);
    }

    @Override
    public void handleMessage(Message msg) {

        switch (msg.what) {
            case SHOW_GAME_OVER_DIALOG:
                showGameOverDialog(msg);
                break;
            case SHOW_TOAST:
                GameActivityHandlerSub.notify(new GameActivityHandlerUpdate(getGameActivity(), msg, SHOW_TOAST));
                break;
            case SHOW_AD:
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
        GameActivityHandlerSub.notify(new GameActivityHandlerUpdate(getGameActivity(), msg, SHOW_GAME_OVER_DIALOG));
    }
}