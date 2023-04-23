public class GameActivitySubjectImpl <T> implements ISubjectImpl<T> {
    private ArrayList observers;

    @Override public void register(IObserver<T> observer) { 
        observers.add(observer);
    }

    @Override public void remove(IObserver<T> observer) { 
        int i = observers.indexOf(o);
        if(i >= 0)
            observers.remove(i);
    }

    @Override public void notify(T data) { 
        
    }
}