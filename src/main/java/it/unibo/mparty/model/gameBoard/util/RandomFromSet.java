package it.unibo.mparty.model.gameBoard.util;

import java.util.Set;
import java.util.Random;

public class RandomFromSet {

    public static <E> E get(Set<E> inputSet) {
        return inputSet.stream().skip(new Random().nextInt(inputSet.size())).findFirst().get();
    }
}
