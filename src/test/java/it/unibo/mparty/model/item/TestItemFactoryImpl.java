package it.unibo.mparty.model.item;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
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
class TestItemFactoryImpl {
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
    void testFactory() {
        assertInstanceOf(GoldenPipe.class, this.testFactory.createItem(ItemName.TUBO_DORATO));
        assertInstanceOf(CursedDice.class, this.testFactory.createItem(ItemName.DADO_MALEDETTO));
        assertInstanceOf(LuckyDice.class, this.testFactory.createItem(ItemName.DADO_FORTUNATO));
        assertInstanceOf(BooBell.class, this.testFactory.createItem(ItemName.CAMPANA_BOO));
        assertInstanceOf(TripleDice.class, this.testFactory.createItem(ItemName.TRIPLO_DADO));
    }
}
