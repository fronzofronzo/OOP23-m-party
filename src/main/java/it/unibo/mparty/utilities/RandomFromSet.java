package it.unibo.mparty.utilities;

import java.util.Set;
import java.util.Random;

/**
 * The class RandomFromSet return a random value from a set.
 */
public class RandomFromSet {

    private static final String ERROR_MESSAGE = "Il set in input non deve essere vuoto";

    /**
     * Return a random value form the input set.
     * 
     * @param inputSet the input set.
     * @return a random value form the input set.
     * @throws {@link IllegalArgumentException} if the input set is empty.
     */
    public static <E> E get(Set<E> inputSet) {
        if (inputSet.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        return inputSet.stream().skip(new Random().nextInt(inputSet.size())).findFirst().get();
    }
}
