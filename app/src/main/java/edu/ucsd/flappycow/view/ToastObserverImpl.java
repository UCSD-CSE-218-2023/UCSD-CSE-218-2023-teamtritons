package edu.ucsd.flappycow.view;

import android.widget.Toast;

import edu.ucsd.flappycow.consts.ApplicationConstants;
import edu.ucsd.flappycow.util.IObserver;

public class ToastObserverImpl<T> implements IObserver<GameActivityHandlerUpdate> {
    @Override
    public void onUpdate(GameActivityHandlerUpdate gameActivityHandlerUpdate) {
        if(gameActivityHandlerUpdate.msgType == ApplicationConstants.SHOW_TOAST)
            Toast.makeText(gameActivityHandlerUpdate.gameActivity, gameActivityHandlerUpdate.msg.arg1, Toast.LENGTH_SHORT).show();
    }
}
