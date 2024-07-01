package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.PlayerTiles;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.model.minigames.domino.api.TileFactory;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerTilesImplTest {

    private final static int DISTRIBUTION_TILES = 7;
    private final static int sideA = 1;
    private final static int sideB = 0;
    private PlayerTiles playerTiles;
    private Tile tile;
    private TileFactory tileFactory;
    private Player p1;
    private Player p2;

    @BeforeEach
    void setUp() {
        this.playerTiles = new PlayerTilesImpl();
        this.tile = new TileImpl(sideA, sideB);
        this.tileFactory = new TileFactoryImpl();

        p1 = new PlayerImplementation("player1", "Mario");
        p2 = new PlayerImplementation("player2", "Luigi");
    }

    @Test
    void testInitializePlayerTiles() {
        var fullSet = this.tileFactory.createDoubleSixSet();
        var toDistributeP1 = fullSet.stream().limit(DISTRIBUTION_TILES).collect(Collectors.toSet());

        this.playerTiles.initializePlayerTiles(this.p1, toDistributeP1);
        fullSet.removeAll(toDistributeP1);
        assertFalse(this.playerTiles.getPlayerTiles(this.p1).isEmpty());
        assertEquals(DISTRIBUTION_TILES, this.playerTiles.getPlayerTiles(this.p1).size());

        var toDistributeP2 = fullSet.stream().limit(DISTRIBUTION_TILES).collect(Collectors.toSet());

        this.playerTiles.initializePlayerTiles(this.p2, toDistributeP2);
        fullSet.removeAll(toDistributeP2);
        assertFalse(this.playerTiles.getPlayerTiles(this.p2).isEmpty());
        assertEquals(DISTRIBUTION_TILES, this.playerTiles.getPlayerTiles(this.p2).size());
    }

    @Test
    void testRemoveTilesFromPlayer() {
        this.testInitializePlayerTiles();

        this.playerTiles.removeTilesFromPlayer(this.p1, this.tile);
        assertFalse(playerTiles.getPlayerTiles(this.p1).contains(this.tile));

        this.playerTiles.removeTilesFromPlayer(this.p2, this.tile);
        assertFalse(playerTiles.getPlayerTiles(this.p2).contains(this.tile));
    }
}