package it.unibo.samplejavafx.model.minigames.memoryCard;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import it.unibo.mparty.model.minigames.memoryCard.api.MemoryCardModel;
import it.unibo.mparty.model.minigames.memoryCard.impl.MemoryCardModelImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

public class TestMemoryCardImpl {

    private static MemoryCardModel model;

    @BeforeAll
    public static void initialise(){
        model = new MemoryCardModelImpl();
    }

    @Test
    public void testClicks(){
        int i  = 0;
        var type = model.getCards().get(i);
        int j = model.getCards().entrySet().stream().filter(e -> e.getValue() == type && e.getKey() != i).map(e -> e.getKey()).reduce(0, (a,b) -> a+b);
        model.firstClick(i);
        model.secondClick(j);
        asse
    }
}
