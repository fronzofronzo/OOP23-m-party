package it.unibo.mparty.model.Player.impl;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBuilder;
import it.unibo.mparty.model.player.impl.Character;
import it.unibo.mparty.model.player.impl.PlayerBuilderImplementation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PlayerBuilderImplementationTest {

    @Test
    public void testPlayerCreation(){
        final String username = "username";
        final String character = Character.CHAR_MARIO.getName();
        final PlayerBuilder builder = new PlayerBuilderImplementation();
        final Player player = builder.username(username)
                .character(character)
                .buildPlayer();
        assertEquals(username, player.getUsername());
        assertEquals(character, player.getCharacter().getName());
    }
}