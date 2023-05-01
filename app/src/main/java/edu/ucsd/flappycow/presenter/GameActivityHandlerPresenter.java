package edu.ucsd.flappycow.presenter;

import edu.ucsd.flappycow.GameActivityHandler;
import edu.ucsd.flappycow.SubjectImpl;
import edu.ucsd.flappycow.GameActivityHandlerUpdate;
import edu.ucsd.flappycow.GameOverDialogObserverImpl;
import edu.ucsd.flappycow.IObserver;
import edu.ucsd.flappycow.ISubjectImpl;
import edu.ucsd.flappycow.ToastObserverImpl;
import edu.ucsd.flappycow.consts.ApplicationConstants;

import android.widget.Toast;

import java.util.ArrayList;

public class GameActivityHandlerPresenter implements IObserver<GameActivityHandlerUpdate> {
    ISubjectImpl gameActivityHandlerSub; //MODEL

    Toast toast;
    public GameActivityHandlerPresenter(ISubjectImpl gameActivityHandlerSub){
        this.gameActivityHandlerSub  = gameActivityHandlerSub;
        this.gameActivityHandlerSub.register(this);
    }

    @Override
    public void onUpdate(GameActivityHandlerUpdate gameActivityHandlerUpdate) {
        if(gameActivityHandlerUpdate.getMsgType() == ApplicationConstants.SHOW_TOAST)
            Toast.makeText(gameActivityHandlerUpdate.getGameActivity(), gameActivityHandlerUpdate.getMsg().arg1, Toast.LENGTH_SHORT).show();
    }
}
