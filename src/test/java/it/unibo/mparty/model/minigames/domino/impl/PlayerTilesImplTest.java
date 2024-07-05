package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.PlayerTiles;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.model.minigames.domino.api.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTilesImplTest {

    private final static int DISTRIBUTION_TILES = 7;
    private final static int sideA = 1;
    private final static int sideB = 0;
    private PlayerTiles playerTiles;
    private Tile tile;
    private TileFactory tileFactory;
    private String player1;
    private String player2;

    @BeforeEach
    void setUp() {
        this.playerTiles = new PlayerTilesImpl();
        this.tile = new TileImpl(sideA, sideB);
        this.tileFactory = new TileFactoryImpl();

        player1 = "player1";
        player2 = "player2";
    }

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
