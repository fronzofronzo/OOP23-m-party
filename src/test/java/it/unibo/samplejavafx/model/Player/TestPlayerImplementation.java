package it.unibo.samplejavafx.model.Player;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.api.ItemFactory;
import it.unibo.mparty.model.item.impl.GoldenPipe;
import it.unibo.mparty.model.item.impl.ItemFactoryImpl;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.Character;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import it.unibo.mparty.utilities.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayerImplementation {

    private Player testPlayer;

    @BeforeEach
    public void init(){
        final String username = "username";
        final String character = "Luigi";
        final Position position = new Position(0,0);
        testPlayer = new PlayerImplementation(username,character);
    }

    @Test
    public void testPlayerConstructor(){
        final String username = "username";
        final String character = "Luigi";
        final Position position = new Position(0,0);
        assertEquals(username, testPlayer.getUsername());
        assertEquals(character, testPlayer.getCharacter().getName());
        assertEquals(position, testPlayer.getPosition());
    }

    @Test
    public void testStarAmount(){
        assertEquals(0, testPlayer.getNumStars());
        testPlayer.addStar();
        assertEquals(1, testPlayer.getNumStars());
        testPlayer.removeStar();
        assertEquals(0, testPlayer.getNumStars());
    }

    @Test
    public void testPlayerBag() {
        final Item itemTest = new ItemFactoryImpl().createItem(ItemName.TUBO_DORATO);
        testPlayer.getPlayerBag().addItem(itemTest);
        assertEquals(itemTest, testPlayer.getPlayerBag().getItem(0));
        assertFalse(testPlayer.getPlayerBag().isFull());

        assertDoesNotThrow(testPlayer.getPlayerBag().removeItem(0));
    }

}