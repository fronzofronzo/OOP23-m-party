package it.unibo.samplejavafx.model.Player;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.Character;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import it.unibo.mparty.utilities.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestPlayerImplementation {

    @Test
    public void testPlayerConstructor(){
        final String username = "username";
        final String character = "Luigi";
        final Position position = new Position(0,0);
        final Player testPlayer = new PlayerImplementation(username,character);
        assertEquals(username, testPlayer.getUsername());
        assertEquals(character, testPlayer.getCharacter().getName());
        assertEquals(position, testPlayer.getPosition());
    }

    @Test
    public void testStarAmount(){
        final String username = "username";
        final String character = "Luigi";
        final Position position = new Position(0,0);
        final Player testPlayer = new PlayerImplementation(username,character);
        assertEquals(0, testPlayer.getNumStars());
        testPlayer.addStar();
        assertEquals(1, testPlayer.getNumStars());
        testPlayer.removeStar();
        assertEquals(0, testPlayer.getNumStars());
    }



}