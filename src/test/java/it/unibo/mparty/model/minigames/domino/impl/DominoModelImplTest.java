package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.DominoModel;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DominoModelImplTest {

    private final int DISTRIBUTION_NUMBER = 7;  // Number of tiles to distribute to each player
    private final int DOMINO_SET_SIZE = 28;     // Total size of the domino set
    private DominoModel model;
    private Player p1;
    private Player p2;

    @BeforeEach
    void setUp() {
        // Initialize the model and players before each test
        model = new DominoModelImpl();
        p1 = new PlayerImplementation("player1", "Mario");
        p2 = new PlayerImplementation("player2", "Luigi");
    }

    @Test
    void testDistributionTiles() {
        // Initialize the model and distribute dominoes to players
        model.initDomino(p1, p2);

        // Verify players' domino sets are not empty after initialization
        assertFalse(model.getPlayerDomino().getPlayerTiles(p1).isEmpty());
        assertFalse(model.getPlayerDomino().getPlayerTiles(p2).isEmpty());

        // Verify the model's domino set has correct size after distribution
        assertEquals(DOMINO_SET_SIZE - DISTRIBUTION_NUMBER * 2, model.getDominoSet().size());

        // Verify both players have the correct number of tiles
        assertEquals(DISTRIBUTION_NUMBER, model.getPlayerDomino().getPlayerTiles(p1).size());
        assertEquals(DISTRIBUTION_NUMBER, model.getPlayerDomino().getPlayerTiles(p2).size());

        // Verify tiles assigned to players are removed from the main set
        assertFalse(model.getDominoSet().containsAll(model.getPlayerDomino().getPlayerTiles(p1)));
        assertFalse(model.getDominoSet().containsAll(model.getPlayerDomino().getPlayerTiles(p2)));

        // Verify players' domino sets are distinct from each other
        assertFalse(model.getPlayerDomino().getPlayerTiles(p1).containsAll(model.getPlayerDomino().getPlayerTiles(p2)));
    }

}
