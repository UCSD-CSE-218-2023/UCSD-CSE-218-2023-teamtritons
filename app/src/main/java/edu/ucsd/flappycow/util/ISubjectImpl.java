package edu.ucsd.flappycow.util;

import edu.ucsd.flappycow.util.ISubject;

public interface ISubjectImpl<T> extends ISubject<T> {
    void notify(T data);
}
