package it.unibo.mparty.model.minigames.memoryCard;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import it.unibo.mparty.model.minigames.memoryCard.api.MemoryCardModel;
import it.unibo.mparty.model.minigames.memoryCard.impl.MemoryCardModelImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Test class for the {@link MemoryCardModelImpl} class
 */
public class TestMemoryCardImpl {

    private static MemoryCardModel model;

    /**
     * Initialization method executed Before all others methods
     */
    @BeforeAll
    public static void initialise(){
        model = new MemoryCardModelImpl();
    }

    /**
     * Check if flipping correct behavior of model flipping two cards of same type
     */
    @Test
    public void testFlipSameType(){
        final int i  = 0;
        var type = model.getCards().get(i);
        final int j = model.getCards().entrySet().stream().filter(e -> e.getValue() == type && e.getKey() != i).map(e -> e.getKey()).reduce(0, (a,b) -> a+b);
        model.flip(i);
        model.flip(j);
        assertEquals(1,model.guessedCardsType().size());
    }

    /**
     * Check if flipping correct behavior of model flipping two cards of different type
     */
    @Test
    public void testFlipDifferentType(){
        final int i  = 0;
        var type = model.getCards().get(i);
        final int j = model.getCards().entrySet().stream().filter(e -> e.getValue() != type && e.getKey() != i).map(Map.Entry::getKey).findAny().get();
        model.flip(i);
        model.flip(j);
        assertEquals(1,model.getMistakes());
    }
}
