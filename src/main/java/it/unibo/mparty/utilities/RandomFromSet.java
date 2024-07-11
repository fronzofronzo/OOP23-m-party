package it.unibo.mparty.utilities;

import java.util.Set;
import java.util.Random;

/**
 * The class RandomFromSet return a random value from a set.
 */
public final class RandomFromSet {

    private static final String ERROR_MESSAGE = "Il set in input non deve essere vuoto";
    private static final Random RANDOM = new Random();

    private RandomFromSet() {
    }

    /**
     * Return a random value form the input set.
     * 
     * @param <E>      the type of the elements in the input set.
     * @param inputSet the input set.
     * @return a random value form the input set.
     */
    public static <E> E get(final Set<E> inputSet) {
        if (inputSet.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        return inputSet.stream().skip(RANDOM.nextInt(inputSet.size())).findFirst().get();
    }
}
