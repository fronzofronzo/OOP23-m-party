package it.unibo.mparty.player.impl;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBuilder;
import it.unibo.mparty.model.player.impl.Character;
import it.unibo.mparty.model.player.impl.PlayerBuilderImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for a {@link PlayerBuilderImpl} class.
 */
class PlayerBuilderImplTest {

    /**
     * Check if the player is created correctly.
     */
    @Test
    void testPlayerCreation() {
        final String username = "username";
        final String character = Character.CHAR_MARIO.getName();
        final PlayerBuilder builder = new PlayerBuilderImpl();
        final Player player = builder.username(username)
                .character(character)
                .buildPlayer();
        assertNotNull(player);
        assertEquals(username, player.getUsername());
        assertEquals(character, player.getCharacter().getName());
    }

    /**
     * Check that building a player with not enough information fails.
     */
    @Test
    void testNotEnoughInformation() {
        final String username = "username";
        final PlayerBuilder builder = new PlayerBuilderImpl();
        assertThrows(IllegalStateException.class,
                () -> {
                    builder.username(username)
                            .buildPlayer();
                });
    }
}
