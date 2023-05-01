package edu.ucsd.flappycow;

public interface IObserver<T> {
    void onUpdate(T data);
}
