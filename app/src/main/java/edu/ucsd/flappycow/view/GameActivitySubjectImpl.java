package edu.ucsd.flappycow.view;

import java.util.ArrayList;

import edu.ucsd.flappycow.util.IObserver;
import edu.ucsd.flappycow.util.ISubjectImpl;

public class GameActivitySubjectImpl <T> implements ISubjectImpl<T> {
    private ArrayList<IObserver> observers;

    public GameActivitySubjectImpl() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void register(IObserver<T> observer) {
        observers.add(observer);
    }

    @Override
    public void remove(IObserver<T> observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(T data) {
        for(IObserver o: observers)
            o.onUpdate(data);
    }
}