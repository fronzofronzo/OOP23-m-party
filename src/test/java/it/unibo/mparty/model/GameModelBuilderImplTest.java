package it.unibo.mparty.model;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.Character;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import it.unibo.mparty.utilities.BoardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link GameModelBuilderImpl} class
 */
class GameModelBuilderImplTest {

    private GameModelBuilder builder;

    /**
     * Tests initialisation that is performed before each test
     */
    @BeforeEach
    public void init(){
        this.builder = new GameModelBuilderImpl();
    }

    /**
     * Check if the number of minimum and maximum players is handled correctly
     * by the Game Model builder.
     */
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
        final String user3 = "user3";
        final String charact3 = Character.CHAR_PEACH.getName();
        this.builder = this.builder.addPlayer(user3,charact3);
        final String user4 =  "user4";
        final String charact4 = Character.CHAR_YOSHI.getName();
        this.builder = this.builder.addPlayer(user4,charact4);
        assertTrue(this.builder.isFull());
    }

    /**
     * Check that adding two players with same username and/or same character
     * throws {@link IllegalArgumentException}
     */
    @Test
    public void testInsertSamePlayer(){
        final String user1 = "user1";
        final String charact1 = Character.CHAR_MARIO.getName();
        this.builder = this.builder.addPlayer(user1,charact1);
        final String user2 = "user1";
        final String charact2 = Character.CHAR_MARIO.getName();
        assertThrows(IllegalArgumentException.class,
                () -> {this.builder.addPlayer(user2,charact2);});
    }

    /**
     * Check if the building of a new GameModel instance is created properly
     */
    @Test
    public void testBuildingModel(){
        final String user1 = "user1";
        final String charact1 = Character.CHAR_MARIO.getName();
        final String user2 = "user2";
        final String charact2 = Character.CHAR_DK.getName();
        final GameModel model = this.builder.addPlayer(user1,charact1)
                .addPlayer(user2,charact2)
                .difficulty(BoardType.EASY.toString()).build();
        assertTrue(model.getPlayers().stream()
                .map(Player::getUsername)
                .toList().contains(user1));
        assertTrue(model.getPlayers().stream()
                .map(Player::getUsername)
                .toList().contains(user2));
    }

}