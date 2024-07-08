package it.unibo.mparty.model.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.item.impl.BooBell;
import it.unibo.mparty.model.item.impl.CursedDice;
import it.unibo.mparty.model.item.impl.GoldenPipe;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.item.impl.LuckyDice;
import it.unibo.mparty.model.item.impl.TripleDice;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import it.unibo.mparty.model.shop.api.Shop;
import it.unibo.mparty.model.shop.impl.ShopImpl;

/**
 * This class test the {@link ShopImpl} class.
 */
public class ShopImplTest {
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
        testPlayer = new PlayerImplementation("testPlayer", "Mario");
    }

    /**
     * Test the getItemList method of {@link ShopImpl} class.
     */
    @Test
    public void testGetItemList() {
        var items = testShop.getItemList();
        assertNotNull(items);
        assertEquals(EXPECTED_SIZE, items.size());
        assertTrue(items.get(0) instanceof BooBell);
        assertTrue(items.get(1) instanceof CursedDice);
        assertTrue(items.get(2) instanceof LuckyDice);
        assertTrue(items.get(3) instanceof TripleDice);
        assertTrue(items.get(4) instanceof GoldenPipe);
    }

    /**
     * Test the method buyItem of the {@link ShopImpl} class.
     */
    @Test
    public void testAcquisition() {
        testPlayer.addCoins(TEST_COINS);
        assertTrue(testShop.buyItem(testPlayer, ItemName.TRIPLO_DADO));
        assertEquals(ItemName.TRIPLO_DADO, testPlayer.getPlayerBag().getItems().get(0));
        assertFalse(testShop.buyItem(testPlayer, ItemName.DADO_FORTUNATO));
    }
}
