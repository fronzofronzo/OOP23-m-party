package it.unibo.mparty.model.gameBoard.util;
import java.util.Set;

import it.unibo.mparty.utilities.Pair;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

//import org.apache.commons.math3.distribution.EnumeratedDistribution 
public class RandomListGenerator {
    public static <E> List<E> generate(Set<Pair<E,Double>> inputSet) {
        List<E> outputList = new ArrayList<>();
        for (Pair<E,Double> p : inputSet) {
            E element = p.getX();
            Double perc = p.getY();
            for (int i = 0; i < perc * 100; i++) {
                outputList.add(element);
            }            
        }
        Collections.shuffle(outputList);
        return outputList;
    }
}
