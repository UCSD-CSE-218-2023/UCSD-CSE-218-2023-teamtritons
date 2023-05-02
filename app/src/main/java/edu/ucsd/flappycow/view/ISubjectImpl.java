package edu.ucsd.flappycow;

public interface ISubjectImpl<T> extends ISubject<T>{
    void notify(T data);
}
