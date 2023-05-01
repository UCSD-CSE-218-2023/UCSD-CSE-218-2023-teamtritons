package edu.ucsd.flappycow;

import android.widget.Toast;

import edu.ucsd.flappycow.consts.ApplicationConstants;

public class ToastObserverImpl<T> implements IObserver<GameActivityHandlerUpdate>{
    @Override
    public void onUpdate(GameActivityHandlerUpdate gameActivityHandlerUpdate) {
        if(gameActivityHandlerUpdate.msgType == ApplicationConstants.SHOW_TOAST)
            Toast.makeText(gameActivity, gameActivityHandlerUpdate.msg.arg1, Toast.LENGTH_SHORT).show();
    }
}
