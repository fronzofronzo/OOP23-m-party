package it.unibo.mparty.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.api.ItemFactory;
import it.unibo.mparty.model.item.impl.ItemFactoryImpl;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImplementation;

/**
 * Test class for the Items.
 */
public class TestItemImplementation {

    private final ItemFactory factory = new ItemFactoryImpl();
    private Player testPlayer;
    private Item cursedDice;
    private Item luckyDice;
    private Item doubleDice;
    private Item booBell;
    private Item goldenPipe;
    private static final int EXP_GOLDENPIPE = 10;
    private static final int EXP_DOUBLEDICE = 7;
    /**
     * Initialize the items and the player used in all tests.
     */
    @BeforeEach
    public void init() {
        cursedDice = factory.createItem(ItemName.DADO_MALEDETTO);
        luckyDice = factory.createItem(ItemName.DADO_FORTUNATO);
        doubleDice = factory.createItem(ItemName.DOPPIO_DADO);
        booBell = factory.createItem(ItemName.CAMPANA_BOO);
        goldenPipe = factory.createItem(ItemName.TUBO_DORATO);
        testPlayer = new PlayerImplementation("test", "Mario");
    }

    /**
     * Test the getDescription method of the items.
     */
    @Test
    public void testGetDescription() {
        assertEquals("Il dado ha solo numeri da 1 a 3. Può essere attivato su un giocatore a tua scelta",
        cursedDice.getDescription());
        assertEquals("Boo ruba delle monete ad un avversario.", booBell.getDescription());
    }

    /**
     * Test the getCost method of the items.
     */
    @Test
    public void testGetCost() {
        assertEquals(EXP_GOLDENPIPE, goldenPipe.getCost());
        assertEquals(EXP_DOUBLEDICE, doubleDice.getCost());
    }

    /**
     * Test the getName method of the item.
     */
    @Test
    public void testGetName() {
        assertEquals(ItemName.DADO_FORTUNATO, luckyDice.getName());
        assertEquals(ItemName.CAMPANA_BOO, booBell.getName());
    }

    /**
     * Test the activation of the items and the effect on the players.
     */
    @Test
    public void testActivation () {
        final Player testPlayer2 = new PlayerImplementation("test2", "Luigi");
        testPlayer2.addCoins(10);
        testPlayer.getPlayerBag().addItem(doubleDice);
        testPlayer.getPlayerBag().addItem(booBell);
        testPlayer.getPlayerBag().useItem(testPlayer.getPlayerBag().getItems().get(0));
        assertEquals(2, testPlayer.getDice().getNumOfAttempts());
        testPlayer.getPlayerBag().useItem(testPlayer.getPlayerBag().getItems().get(1));
        assertEquals(5,testPlayer2.getNumCoins());
    }

}
