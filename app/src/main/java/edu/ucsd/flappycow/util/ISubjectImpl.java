package edu.ucsd.flappycow.view;

public interface ISubjectImpl<T> extends ISubject<T>{
    void notify(T data);
}
