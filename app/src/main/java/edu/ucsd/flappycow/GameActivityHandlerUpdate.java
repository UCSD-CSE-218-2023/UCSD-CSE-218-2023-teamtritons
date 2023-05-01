package edu.ucsd.flappycow;

import android.os.Message;

public class GameActivityHandlerUpdate {
//    GameActivity gameActivity;
    Message msg;
    int msgType;

    public GameActivityHandlerUpdate(/**GameActivity gameActivity,**/ Message msg, int msgType) {
//        this.gameActivity = gameActivity;
        this.msg = msg;
        this.msgType = msgType;
    }

//    public GameActivity getGameActivity() {
//        return gameActivity;
//    }
//
//    public void setGameActivity(GameActivity gameActivity) {
//        this.gameActivity = gameActivity;
//    }

    public Message getMsg() {
        return msg;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }
}
