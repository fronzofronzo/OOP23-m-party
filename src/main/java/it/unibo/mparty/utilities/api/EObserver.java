package it.unibo.mparty.utilities.api;

/**
 * Represents an observer in the observer pattern.
 *
 * @param <T> the type of data being observed
 */
public interface EObserver<T> {

    /**
     * Called when the source has new data.
     *
     * @param arg the new data
     */
    void update(T arg);
}
