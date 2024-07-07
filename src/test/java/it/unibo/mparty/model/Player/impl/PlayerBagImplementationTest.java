package it.unibo.mparty.model.Player.impl;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBag;
import it.unibo.mparty.model.player.impl.PlayerBagImplementation;
import it.unibo.mparty.model.player.impl.PlayerBuilderImplementation;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for a {@link it.unibo.mparty.model.player.impl.PlayerBagImplementation}
 */
class PlayerBagImplementationTest {

    private PlayerBag bag;

    /**
     * Configuration step: this is performed before each test
     */
    @BeforeEach
    public void init(){
        final Player pl1 = new PlayerBuilderImplementation()
                .username("user")
                .character("Mario")
                .buildPlayer();
        final int num = 3;
        this.bag = new PlayerBagImplementation(num,pl1);
    }

}