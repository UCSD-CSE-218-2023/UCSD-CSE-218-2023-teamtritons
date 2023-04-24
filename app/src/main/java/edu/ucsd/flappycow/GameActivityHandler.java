package edu.ucsd.flappycow;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import edu.ucsd.flappycow.GameActivity;

class GameActivityHandler extends Handler {
    public static final int SHOW_GAME_OVER_DIALOG = 0;
    public static final int SHOW_TOAST = 1;
    public static final int SHOW_AD = 2;

    private final GameActivity gameActivity;

    public GameActivityHandler(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_GAME_OVER_DIALOG:
                showGameOverDialog();
                break;
            case SHOW_TOAST:
                Toast.makeText(gameActivity, msg.arg1, Toast.LENGTH_SHORT).show();
                break;
            case SHOW_AD:
                showAdIfAvailable();
                break;
        }
    }

    private void showAdIfAvailable() {
        if (gameActivity.getInterstitial() == null) {
            showGameOverDialog();
        } else {
            gameActivity.getInterstitial().show(gameActivity);
        }
    }

    private void showGameOverDialog() {
        gameActivity.setGameOverCounter(gameActivity.getGameOverCounter() + 1);
        gameActivity.gameOverDialog.init();
        gameActivity.gameOverDialog.show();
    }
}