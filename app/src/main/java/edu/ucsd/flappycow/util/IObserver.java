package edu.ucsd.flappycow.view;

public interface IObserver<T> {
    void onUpdate(T data);
}
