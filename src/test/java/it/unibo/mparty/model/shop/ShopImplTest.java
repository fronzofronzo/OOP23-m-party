package it.unibo.mparty.model.shop;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.impl.BooBell;
import it.unibo.mparty.model.item.impl.CursedDice;
import it.unibo.mparty.model.item.impl.GoldenPipe;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.item.impl.LuckyDice;
import it.unibo.mparty.model.item.impl.TripleDice;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImpl;
import it.unibo.mparty.model.shop.api.Shop;
import it.unibo.mparty.model.shop.impl.ShopImpl;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class test the {@link ShopImpl} class.
 */
class ShopImplTest {
    private Shop testShop;
    private Player testPlayer;
    private static final int EXPECTED_SIZE = 5;
    private static final int TEST_COINS = 12;

    /**
     * This methods initialize the shop for the test.
     */
    @BeforeEach
    public void init() {
        testShop = new ShopImpl();
        testPlayer = new PlayerImpl("testPlayer", "Mario");
    }

    /**
     * Test the getItemList method of {@link ShopImpl} class.
     */
    @Test
    void testGetItemList() {
        final List<Item> items = testShop.getItemList();
        assertNotNull(items);
        assertEquals(EXPECTED_SIZE, items.size());
        assertInstanceOf(BooBell.class, items.get(0));
        assertInstanceOf(CursedDice.class, items.get(1));
        assertInstanceOf(LuckyDice.class, items.get(2));
        assertInstanceOf(TripleDice.class, items.get(3));
        assertInstanceOf(GoldenPipe.class, items.get(4));
    }

    /**
     * Test the method buyItem of the {@link ShopImpl} class.
     */
    @Test
    void testAcquisition() {
        testPlayer.addCoins(TEST_COINS);
        assertTrue(testShop.buyItem(testPlayer, ItemName.TRIPLO_DADO));
        assertEquals(ItemName.TRIPLO_DADO, testPlayer.getPlayerBag().getItems().get(0));
        assertFalse(testShop.buyItem(testPlayer, ItemName.DADO_FORTUNATO));
    }
}
