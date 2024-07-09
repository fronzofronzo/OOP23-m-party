package it.unibo.mparty.model.gameBoard.util;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class RandomListGenerator {

    private static final int CORRECT_PERC = 100;
    private static final String ERROR_MESSAGE = "Il valore totale delle percentuali in input non è accettabile (100)";

    public static <E> List<E> generate(Map<E, Integer> input) {
        if (!validProbabilities(input)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        List<E> outputList = new ArrayList<>();
        for (Map.Entry<E, Integer> entry : input.entrySet()) {
            E element = entry.getKey();
            int perc = entry.getValue();
            for (int i = 0; i < perc; i++) {
                outputList.add(element);
            }
        }
        Collections.shuffle(outputList);
        return outputList;
    }

    private static <E> boolean validProbabilities(Map<E, Integer> inputSet) {
        int totPerc = 0;
        for (Map.Entry<E, Integer> entry : inputSet.entrySet()) {
            totPerc += entry.getValue();
        }
        return totPerc == CORRECT_PERC;
    }
}
