package it.unibo.mparty.model.minigames.domino.api;

import java.util.List;

public interface ESource<T> {
    void addObserver(EObserver<? super T> obs);

    void notifyObservers(T t);
}
