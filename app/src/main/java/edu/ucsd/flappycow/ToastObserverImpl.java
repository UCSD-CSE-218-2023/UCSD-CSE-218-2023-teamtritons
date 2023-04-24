package edu.ucsd.flappycow;

import android.widget.Toast;

public class ToastObserverImpl<T> implements IObserver<GameActivityHandlerUpdate>{
    @Override
    public void onUpdate(GameActivityHandlerUpdate gameActivityHandlerUpdate) {
        if(gameActivityHandlerUpdate.msgType == 1)
            Toast.makeText(gameActivityHandlerUpdate.gameActivity, gameActivityHandlerUpdate.msg.arg1, Toast.LENGTH_SHORT).show();
    }
}
