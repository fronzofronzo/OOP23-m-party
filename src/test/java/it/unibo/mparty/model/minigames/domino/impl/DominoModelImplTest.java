package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.DominoModel;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DominoModelImplTest {

    private static final int DISTRIBUTION_TILES = 7;
    private static final int DOMINO_SET_SIZE = 28;
    private DominoModel model;
    private Player p1;
    private Player p2;

    @BeforeEach
    void setUp() {
        model = new DominoModelImpl();

        p1 = new PlayerImplementation("player1", "Mario");
        p2 = new PlayerImplementation("player2", "Luigi");
    }

    @Test
    void testDistributionTiles() {
        model.initDomino(p1, p2);

        // Verify players' domino sets are not empty after initialization
        assertFalse(model.getPlayerTiles().getPlayerTiles(p1).isEmpty());
        assertFalse(model.getPlayerTiles().getPlayerTiles(p2).isEmpty());

        // Verify both players have the correct number of tiles
        assertEquals(DISTRIBUTION_TILES, model.getPlayerTiles().getPlayerTiles(p1).size());
        assertEquals(DISTRIBUTION_TILES, model.getPlayerTiles().getPlayerTiles(p2).size());

        // Verify the model's domino set has correct size after distribution
        assertEquals(DOMINO_SET_SIZE - (DISTRIBUTION_TILES * 2), model.getDominoSet().size());

        // Verify tiles assigned to players are removed from the main set
        assertFalse(model.getDominoSet().containsAll(model.getPlayerTiles().getPlayerTiles(p1)));
        assertFalse(model.getDominoSet().containsAll(model.getPlayerTiles().getPlayerTiles(p2)));

        // Verify players' domino sets are distinct from each other
        assertFalse(model.getPlayerTiles().getPlayerTiles(p1).containsAll(model.getPlayerTiles().getPlayerTiles(p2)));
    }

    @Test
    void testTurn() {

    }

    @Test
    void testMove() {
    }

    @Test
    void testWinner() {
    }
}
