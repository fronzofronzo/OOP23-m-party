package it.unibo.mparty.model.Player.impl;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBuilder;
import it.unibo.mparty.model.player.impl.Character;
import it.unibo.mparty.model.player.impl.PlayerBuilderImplementation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for a {@link PlayerBuilderImplementation} class.
 */
class PlayerBuilderImplementationTest {

    /**
     * Check if the player is created correctly.
     */
    @Test
    public void testPlayerCreation() {
        final String username = "username";
        final String character = Character.CHAR_MARIO.getName();
        final PlayerBuilder builder = new PlayerBuilderImplementation();
        final Player player = builder.username(username)
                .character(character)
                .buildPlayer();
        assertEquals(username, player.getUsername());
        assertEquals(character, player.getCharacter().getName());
    }

    /**
     * Check that building a player with not enough information fails.
     */
    @Test
    public void testNotEnoughInformation() {
        final String username = "username";
        final PlayerBuilder builder = new PlayerBuilderImplementation();
        assertThrows(IllegalStateException.class,
                () -> {
                    builder.username(username).buildPlayer();
                });
    }
}
