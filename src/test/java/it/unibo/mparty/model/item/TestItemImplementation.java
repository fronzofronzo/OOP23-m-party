package it.unibo.mparty.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.api.ItemFactory;
import it.unibo.mparty.model.item.impl.ItemFactoryImpl;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImpl;

/**
 * Test class for the Items.
 */
class TestItemImplementation {

    private final ItemFactory factory = new ItemFactoryImpl();
    private Player testPlayer;
    private Item cursedDice;
    private Item luckyDice;
    private Item tripleDice;
    private Item booBell;
    private Item goldenPipe;
    private static final int EXP_GOLDENPIPE = 12;
    private static final int EXP_TRIPLEDICE = 10;
    private static final int EXP_PL2_COINS = 7;
    private static final int EXP_PL1_COINS = 3;


    /**
     * Initialize the items and the player used in all tests.
     */
    @BeforeEach
    public void init() {
        cursedDice = factory.createItem(ItemName.DADO_MALEDETTO);
        luckyDice = factory.createItem(ItemName.DADO_FORTUNATO);
        tripleDice = factory.createItem(ItemName.TRIPLO_DADO);
        booBell = factory.createItem(ItemName.CAMPANA_BOO);
        goldenPipe = factory.createItem(ItemName.TUBO_DORATO);
        testPlayer = new PlayerImpl("test", "Mario");
    }

    /**
     * Test the getDescription method of the items.
     */
    @Test
    void testGetDescription() {
        assertEquals("Il dado ha solo numeri da 1 a 3. Puo' essere attivato su un giocatore a tua scelta",
        cursedDice.getDescription());
        assertEquals("Boo ruba delle monete ad un avversario.", booBell.getDescription());
    }

    /**
     * Test the getCost method of the items.
     */
    @Test
    void testGetCost() {
        assertEquals(EXP_GOLDENPIPE, goldenPipe.getCost());
        assertEquals(EXP_TRIPLEDICE, tripleDice.getCost());
    }

    /**
     * Test the getName method of the item.
     */
    @Test
    void testGetName() {
        assertEquals(ItemName.DADO_FORTUNATO, luckyDice.getName());
        assertEquals(ItemName.CAMPANA_BOO, booBell.getName());
    }

    /**
     * Test the activation of the items and the effect on the players.
     */
    @Test
    void testActivation() {
        final Player testPlayer2 = new PlayerImpl("test2", "Luigi");
        testPlayer2.addCoins(10);
        testPlayer.getPlayerBag().addItem(tripleDice);
        testPlayer.getPlayerBag().addItem(booBell);
        assertEquals(2, testPlayer.getPlayerBag().getItems().size());
        final Item item = testPlayer.getPlayerBag().useItem(testPlayer.getPlayerBag().getItems().get(0));
        item.activate(testPlayer, null, null);
        assertEquals(3, testPlayer.getDice().getNumOfAttempts());
        final Item item2 = testPlayer.getPlayerBag().useItem(testPlayer.getPlayerBag().getItems().get(0));
        item2.activate(testPlayer, Optional.of(testPlayer2), null);
        assertEquals(EXP_PL2_COINS, testPlayer2.getNumCoins());
        assertEquals(EXP_PL1_COINS, testPlayer.getNumCoins());
    }
}
