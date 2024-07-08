package it.unibo.mparty.model.item;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.item.api.ItemFactory;
import it.unibo.mparty.model.item.impl.ItemFactoryImpl;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.item.impl.LuckyDice;
import it.unibo.mparty.model.item.impl.TripleDice;
import it.unibo.mparty.model.item.impl.BooBell;
import it.unibo.mparty.model.item.impl.CursedDice;
import it.unibo.mparty.model.item.impl.GoldenPipe;

/**
 * This class test the {@link ItemFactoryImpl} class.
 */
public class TestItemFactoryImpl {
    private ItemFactory testFactory;

    /**
     * Initialize the factory before each test.
     */
    @BeforeEach
    public void init() {
        testFactory = new ItemFactoryImpl();
    }

    /**
     * Test the correct functioning of the createItem of the factory.
     */
    @Test
    public void testFactory() {
        assertTrue(this.testFactory.createItem(ItemName.TUBO_DORATO) instanceof GoldenPipe);
        assertTrue(this.testFactory.createItem(ItemName.DADO_MALEDETTO) instanceof CursedDice);
        assertTrue(this.testFactory.createItem(ItemName.DADO_FORTUNATO) instanceof LuckyDice);
        assertTrue(this.testFactory.createItem(ItemName.CAMPANA_BOO) instanceof BooBell);
        assertTrue(this.testFactory.createItem(ItemName.TRIPLO_DADO) instanceof TripleDice);
    }
}
