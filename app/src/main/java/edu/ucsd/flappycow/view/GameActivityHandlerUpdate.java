package edu.ucsd.flappycow.view;

import android.os.Message;

public class GameActivityHandlerUpdate {
    GameActivity gameActivity;
    Message msg;
    int msgType;

    public GameActivityHandlerUpdate(GameActivity gameActivity, Message msg, int msgType) {
        this.gameActivity = gameActivity;
        this.msg = msg;
        this.msgType = msgType;
    }
}
