package it.unibo.mparty.utilities.api;

/**
 * Represents a source in the observer pattern. Manages observers and notifies them of changes.
 *
 * @param <T> the type of data being observed
 */
public interface ESource<T> {

    /**
     * Adds an observer to the list of observers.
     *
     * @param obs the observer to add
     */
    void addObserver(EObserver<? super T> obs);

    /**
     * Notifies all observers of a change.
     *
     * @param t the data to pass to the observers
     */
    void notifyObservers(T t);
}
