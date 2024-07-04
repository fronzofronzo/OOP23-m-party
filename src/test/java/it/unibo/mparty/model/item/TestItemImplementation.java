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

public class TestItemImplementation {

    private final ItemFactory factory = new ItemFactoryImpl();
    private Player testPlayer;
    private Item cursedDice;
    private Item luckyDice;
    private Item doubleDice;
    private Item booBell;
    private Item goldenPipe;
    
    @BeforeEach
    public void init() {
        cursedDice = factory.createItem(ItemName.DADO_MALEDETTO);
        luckyDice = factory.createItem(ItemName.DADO_FORTUNATO);
        doubleDice = factory.createItem(ItemName.DOPPIO_DADO);
        booBell = factory.createItem(ItemName.CAMPANA_BOO);
        goldenPipe = factory.createItem(ItemName.TUBO_DORATO);
        testPlayer= new PlayerImplementation("test", "Mario");
    }

    @Test
    public void testGetDescription() {
        assertEquals("Il dado ha solo numeri da 1 a 3. Può essere attivato su un giocatore a tua scelta",
        cursedDice.getDescription());
        assertEquals("Boo ruba delle monete ad un avversario.", booBell.getDescription());
    }

    @Test
    public void testGetCost() {
        assertEquals(15, goldenPipe.getCost());
        assertEquals(5, doubleDice.getCost());
    }

    @Test
    public void testGetName() {
        assertEquals(ItemName.DADO_FORTUNATO, luckyDice.getName());
        assertEquals(ItemName.CAMPANA_BOO, booBell.getName());
    }

    @Test
    public void testActivation () {
        final Player testPlayer2 = new PlayerImplementation("test2", "Luigi");
        testPlayer2.addCoins(10);
        testPlayer.getPlayerBag().addItem(doubleDice);
        testPlayer.getPlayerBag().addItem(booBell);
        testPlayer.getPlayerBag().getItem(0).activate(testPlayer);
        //assertEquals(2, testPlayer.getDice().);
        testPlayer.getPlayerBag().getItem(1).activate(testPlayer2);
        assertEquals(5,testPlayer2.getNumCoins());
    }

}
