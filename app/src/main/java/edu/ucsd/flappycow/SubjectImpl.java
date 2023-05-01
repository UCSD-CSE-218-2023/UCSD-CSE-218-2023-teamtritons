package edu.ucsd.flappycow;

import java.util.ArrayList;

public class SubjectImpl <T> implements ISubjectImpl<T> {
    private ArrayList<IObserver> observers;

    @Override public void register(IObserver<T> observer) {
        if(observers == null){
            observers = new ArrayList<IObserver>();
        }
        observers.add(observer);
    }

    @Override public void remove(IObserver<T> observer) {
        int i = observers.indexOf(observer);
        if(i >= 0)
            observers.remove(i);
    }

    @Override public void notify(T data) {
        for(int i=0; i<observers.size(); i++)
            observers.get(i).onUpdate(data);
    }
}