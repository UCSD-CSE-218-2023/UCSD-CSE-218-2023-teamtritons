package edu.ucsd.flappycow.view;

public interface Subject<T> {
    void register(IObserver<T> o);
    void unregister(IObserver<T> o);

    void notify(T data);

}
