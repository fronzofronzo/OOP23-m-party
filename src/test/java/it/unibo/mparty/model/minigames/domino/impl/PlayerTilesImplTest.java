package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.player.api.PlayerTiles;
import it.unibo.mparty.model.minigames.domino.player.impl.PlayerTilesImpl;
import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.model.minigames.domino.tile.api.TileFactory;
import it.unibo.mparty.model.minigames.domino.tile.impl.TileFactoryImpl;
import it.unibo.mparty.model.minigames.domino.tile.impl.TileImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for {@link PlayerTilesImpl}.
 */
class PlayerTilesImplTest {

    private static final int DISTRIBUTION_TILES = 7;
    private static final int SIDEA = 1;
    private static final int SIDEB = 0;
    private PlayerTiles playerTiles;
    private Tile tile;
    private TileFactory tileFactory;
    private String player1;
    private String player2;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        this.playerTiles = new PlayerTilesImpl();
        this.tile = new TileImpl(SIDEA, SIDEB);
        this.tileFactory = new TileFactoryImpl();

        player1 = "player1";
        player2 = "player2";
    }

    /**
     * Tests the {@code initializePlayerTiles} method.
     * Ensures that player tiles are initialized and distributed correctly.
     */
    @Test
    void testInitializePlayerTiles() {
        var fullSet = this.tileFactory.createDoubleSixSet();
        var toDistributeP1 = fullSet.stream().limit(DISTRIBUTION_TILES).collect(Collectors.toSet());

        this.playerTiles.initializePlayerTiles(this.player1, toDistributeP1);
        fullSet.removeAll(toDistributeP1);
        assertFalse(this.playerTiles.getPlayerTiles(this.player1).isEmpty());
        assertEquals(DISTRIBUTION_TILES, this.playerTiles.getPlayerTiles(this.player1).size());

        var toDistributeP2 = fullSet.stream().limit(DISTRIBUTION_TILES).collect(Collectors.toSet());

        this.playerTiles.initializePlayerTiles(this.player2, toDistributeP2);
        fullSet.removeAll(toDistributeP2);
        assertFalse(this.playerTiles.getPlayerTiles(this.player2).isEmpty());
        assertEquals(DISTRIBUTION_TILES, this.playerTiles.getPlayerTiles(this.player2).size());
    }

    /**
     * Tests the {@code removeTilesFromPlayer} method.
     * Ensures that tiles are correctly removed from a player's tile set.
     */
    @Test
    void testRemoveTilesFromPlayer() {
        this.testInitializePlayerTiles();

        var pickTile = this.playerTiles.getPlayerTiles(player1).iterator().next();
        assertTrue(this.playerTiles.getPlayerTiles(this.player1).contains(pickTile));
        this.playerTiles.removeTilesFromPlayer(this.player1, pickTile);
        assertFalse(this.playerTiles.getPlayerTiles(this.player1).contains(pickTile));

        this.playerTiles.removeTilesFromPlayer(this.player2, this.tile);
        assertFalse(this.playerTiles.getPlayerTiles(this.player2).contains(this.tile));
    }
}
