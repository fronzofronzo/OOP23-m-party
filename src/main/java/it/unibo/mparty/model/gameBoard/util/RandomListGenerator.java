package it.unibo.mparty.model.gameBoard.util;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class RandomListGenerator {
    public static <E> List<E> generateRandomList(Set<Pair<E,Integer>> inputSet) {
        List<E> outputList = new ArrayList<>();
        for (Pair<E,Integer> p : inputSet) {
            var element = p.getX();
            int quantity = p.getY();
            for (int i = 0; i < quantity; i++) {
                outputList.add(element);
            }            
        }
        Collections.shuffle(outputList);
        return outputList;
    }
}
