package edu.ucsd.flappycow.util;

import edu.ucsd.flappycow.util.IObserver;

public interface Subject<T> {
    void register(IObserver<T> o);
    void unregister(IObserver<T> o);

    void notify(T data);

}
