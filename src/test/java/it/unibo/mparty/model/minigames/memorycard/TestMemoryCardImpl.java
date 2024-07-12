package it.unibo.mparty.model.minigames.memorycard;

import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.minigames.memorycard.api.MemoryCardModel;
import it.unibo.mparty.model.minigames.memorycard.impl.MemoryCardModelImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * Test class for the {@link MemoryCardModelImpl} class.
 */
class TestMemoryCardImpl {

    private static MemoryCardModel model;

    /**
     * Initialization method executed Before all others methods.
     */
    @BeforeAll
    public static void initialise() {
        model = new MemoryCardModelImpl();
    }

    /**
     * Check if flipping correct behavior of model flipping two cards of same type.
     */
    @Test
    void testFlipSameType() {
        final int i = 0;
        final var type = model.getCards().get(i);
        final int j = model.getCards().entrySet().stream()
                .filter(e -> e.getValue() == type && e.getKey() != i)
                .map(e -> e.getKey())
                .reduce(0, (a, b) -> a + b);
        model.flip(i);
        model.flip(j);
        Assertions.assertEquals(1, model.guessedCardsType().size());
        Assertions.assertFalse(model.isOver());
    }

    /**
     * Check if flipping correct behavior of model flipping two cards of different type.
     */
    @Test
    void testFlipDifferentType() {
        final int i = 0;
        final var type = model.getCards().get(i);
        final int j = model.getCards().entrySet().stream()
                .filter(e -> e.getValue() != type && e.getKey() != i)
                .map(Map.Entry::getKey)
                .findAny()
                .get();
        model.flip(i);
        model.flip(j);
        Assertions.assertEquals(1, model.getMistakes());
        Assertions.assertFalse(model.isOver());
    }

    /**
     * Check that the attributes name and type are correct.
     */
    @Test
    void testMinigameAttributes() {
        Assertions.assertEquals("memoryCard", model.getName());
        Assertions.assertEquals(MinigameType.SINGLE_PLAYER, model.getType());
    }
}
