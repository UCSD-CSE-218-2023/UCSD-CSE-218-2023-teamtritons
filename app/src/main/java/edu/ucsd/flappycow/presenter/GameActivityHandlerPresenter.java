package edu.ucsd.flappycow.presenter;

//import edu.ucsd.flappycow.GameActivityHandler;
//import edu.ucsd.flappycow.SubjectImpl;
import edu.ucsd.flappycow.view.GameActivityHandlerUpdate;
        import edu.ucsd.flappycow.util.IObserver;

public class GameActivityHandlerPresenter implements IObserver<GameActivityHandlerUpdate> {
//    ISubjectImpl gameActivityHandlerSub; //MODEL
//    Toast toast;
//    GameOverDialog GameOverDialog;
//    GameActivity gameActivity;
//
//    public GameActivityHandlerPresenter(ISubjectImpl gameActivityHandlerSub){
//        this.gameActivityHandlerSub  = gameActivityHandlerSub;
//        this.gameActivityHandlerSub.register(this);
//
//    }

    @Override
    public void onUpdate(GameActivityHandlerUpdate gameActivityHandlerUpdate) {
//        if( gameActivityHandlerUpdate.getMsgType() == ApplicationConstants.SHOW_TOAST)
//            Toast.makeText(gameActivityHandlerUpdate.getGameActivity(), gameActivityHandlerUpdate.getMsg().arg1, Toast.LENGTH_SHORT).show();
//        if (gameActivityHandlerUpdate.getMsgType() == ApplicationConstants.SHOW_GAME_OVER_DIALOG) {
//            gameActivityHandlerUpdate.getGameActivity().setGameOverCounter(gameActivityHandlerUpdate.getGameActivity().getGameOverCounter() + 1);
//            gameActivityHandlerUpdate.getGameActivity().getGameOverDialog().init();
//            gameActivityHandlerUpdate.getGameActivity().getGameOverDialog().show();
//        }
    }
}
