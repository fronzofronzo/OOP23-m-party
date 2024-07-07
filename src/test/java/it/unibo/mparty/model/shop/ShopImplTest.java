package it.unibo.mparty.model.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.item.impl.DoubleDice;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import it.unibo.mparty.model.shop.api.Shop;
import it.unibo.mparty.model.shop.impl.ShopImpl;

/**
 * This class test the {@link ShopImpl} class
 */
public class ShopImplTest {
    private Shop testShop;
    private Player testPlayer;
    private DoubleDice doubleDice = new DoubleDice();

    /**
     * This methods initialize the shop for the test
     */
    @BeforeEach
    public void init() {
        testShop = new ShopImpl();
        testPlayer = new PlayerImplementation("testPlayer", "Mario");
    }

    @Test
    public void testGetItemList() {
        assertNotNull(testShop.getItemList());
    }

    @Test
    public void testAcquisition() {
        /*testShop.buyItem(testPlayer, ItemName.DOPPIO_DADO);
        assertEquals(doubleDice.getName(), testPlayer.getPlayerBag().getItems().get(0));
        */
    }
}
