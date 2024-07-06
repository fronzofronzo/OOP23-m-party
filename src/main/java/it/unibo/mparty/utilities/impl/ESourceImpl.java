package it.unibo.mparty.utilities.impl;

import it.unibo.mparty.utilities.api.EObserver;
import it.unibo.mparty.utilities.api.ESource;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of the {@link ESource} interface.
 *
 * @param <T> the type of data being observed
 */
public class ESourceImpl<T> implements ESource<T> {

    private final Set<EObserver<? super T>> set = new HashSet<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(final EObserver<? super T> obs) {
        this.set.add(obs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers(final T t) {
        for (final EObserver<? super T> observer : this.set) {
            observer.update(t);
        }
    }
}
