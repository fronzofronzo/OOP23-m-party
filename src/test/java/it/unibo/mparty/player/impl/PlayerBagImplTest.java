package it.unibo.mparty.player.impl;

import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.impl.ItemFactoryImpl;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.PlayerBag;
import it.unibo.mparty.model.player.impl.PlayerBagImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


/**
 * Test class for a {@link PlayerBagImpl}.
 */
class PlayerBagImplTest {

    private PlayerBag bag;
    private int capacity;

    /**
     * Configuration step: this is performed before each test.
     */
    @BeforeEach
    void init() {
        capacity =  3;
        this.bag = new PlayerBagImpl(capacity);
    }

    /**
     * Check if an item is correctly added to the bag.
     */
    @Test
    void testAddItem() {
        final Item item = new ItemFactoryImpl().createItem(ItemName.DADO_FORTUNATO);
        this.bag.addItem(item);
        assertTrue(this.bag.getItems().contains(item.getName()));
    }

    /**
     * Check if an item is correctly removed from the bag.
     */
    @Test
    void testRemoveItem() {
        final Item item = new ItemFactoryImpl().createItem(ItemName.DADO_FORTUNATO);
        this.bag.addItem(item);
        assertEquals(item, this.bag.useItem(item.getName()));
        assertFalse(this.bag.getItems().contains(item.getName()));
    }

    /**
     * Check if the {@code isFull} function works properly.
     */
    @Test
    public void testFull() {
        final Item item = new ItemFactoryImpl().createItem(ItemName.DADO_FORTUNATO);
        for (int i = 0; i < capacity; i++) {
            this.bag.addItem(item);
        }
        assertTrue(this.bag.isFull());
    }

}
