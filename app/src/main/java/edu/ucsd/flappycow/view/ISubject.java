package edu.ucsd.flappycow;

public interface ISubject<T> {
    void register(IObserver<T> observer);
    void remove(IObserver<T> observer);
}
