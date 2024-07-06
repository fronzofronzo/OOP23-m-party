package it.unibo.mparty.model;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.Character;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link GameModelBuilderImpl} class
 */
class GameModelBuilderImplTest {

    private GameModelBuilder builder;

    @BeforeEach
    public void init(){
        this.builder = new GameModelBuilderImpl();
    }

    @Test
    public void testNumberOfPlayers(){
        final String user1 = "user1";
        final String charact1 = Character.CHAR_MARIO.getName();
        this.builder = this.builder.addPlayer(user1,charact1);
        assertFalse(this.builder.enoughPlayers());
        final String user2 = "user2";
        final String charact2 = Character.CHAR_DK.getName();
        this.builder = this.builder.addPlayer(user2,charact2);
        assertTrue(this.builder.enoughPlayers());
    }

}