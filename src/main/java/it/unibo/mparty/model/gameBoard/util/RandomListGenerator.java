package it.unibo.mparty.model.gameBoard.util;

import java.util.Set;

import it.unibo.mparty.utilities.Pair;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class RandomListGenerator {

    private static final int CORRECT_PERC = 100;
    private static final String ERROR_MESSAGE = "Il valore totale delle percentuali in input non è accettabile (100)";

    public static <E> List<E> generate(Set<Pair<E, Integer>> inputSet) {
        if (!validProbabilities(inputSet)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        List<E> outputList = new ArrayList<>();
        for (Pair<E, Integer> p : inputSet) {
            E element = p.getFirst();
            int perc = p.getSecond();
            for (int i = 0; i < perc; i++) {
                outputList.add(element);
            }
        }
        Collections.shuffle(outputList);
        return outputList;
    }

    private static <E> boolean validProbabilities(Set<Pair<E, Integer>> inputSet) {
        int totPerc = 0;
        for (Pair<E, Integer> p : inputSet) {
            totPerc += p.getSecond();
        }
        return totPerc == CORRECT_PERC;
    }
}
