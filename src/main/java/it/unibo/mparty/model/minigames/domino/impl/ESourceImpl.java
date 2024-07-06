package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.EObserver;
import it.unibo.mparty.model.minigames.domino.api.ESource;

import java.util.HashSet;
import java.util.Set;

public class ESourceImpl<T> implements ESource<T> {

    private final Set<EObserver<? super T>> set = new HashSet<>();

    @Override
    public void addObserver(EObserver<? super T> obs) {
        this.set.add(obs);
    }

    @Override
    public void notifyObservers(T t) {
        for (EObserver<? super T> observer : this.set) {
            observer.update(t);
        }
    }
}
