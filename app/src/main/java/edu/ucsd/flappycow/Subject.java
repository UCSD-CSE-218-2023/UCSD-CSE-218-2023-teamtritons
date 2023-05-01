package edu.ucsd.flappycow;

public interface Subject<T> {
    public void register(IObserver<T> o);
    public void unregister(IObserver<T> o);
    public void notifyObservers(T data);

}
