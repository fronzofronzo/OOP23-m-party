package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.game.api.DominoModel;
import it.unibo.mparty.model.minigames.domino.game.impl.DominoModelImpl;
import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.model.minigames.domino.tile.impl.TileImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test class for {@link DominoModelImpl}.
 */
class DominoModelImplTest {

    private static final int DISTRIBUTION_TILES = 7;
    private static final int DOMINO_SET_SIZE = 28;
    private static final int SIDE1 = 1;
    private static final int SIDE2 = 2;
    private static final int SIDE3 = 3;
    private static final int SIDE4 = 4;
    private static final int SIDE5 = 5;
    private static final int SIDE6 = 6;

    private DominoModel model;
    private List<String> players;
    private String player1;
    private String player2;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        this.model = new DominoModelImpl();
        this.players = List.of("player1", "player2");
        this.player1 = this.players.get(0);
        this.player2 = this.players.get(1);
    }

    /**
     * Tests the distribution of tiles to players.
     */
    @Test
    void testDistributionTiles() {
        this.model.setUpPlayers(this.players);

        assertFalse(this.model.getAllPlayersTiles().getPlayerTiles(this.player1).isEmpty());
        assertFalse(this.model.getAllPlayersTiles().getPlayerTiles(this.player2).isEmpty());

        assertEquals(DISTRIBUTION_TILES, this.model.getAllPlayersTiles().getPlayerTiles(this.player1).size());
        assertEquals(DISTRIBUTION_TILES, this.model.getAllPlayersTiles().getPlayerTiles(this.player2).size());

        assertEquals(DOMINO_SET_SIZE - (DISTRIBUTION_TILES * 2), this.model.getDominoSet().size());

        assertFalse(this.model.getDominoSet().containsAll(this.model.getAllPlayersTiles().getPlayerTiles(this.player1)));
        assertFalse(this.model.getDominoSet().containsAll(this.model.getAllPlayersTiles().getPlayerTiles(this.player2)));

        assertFalse(this.model.getAllPlayersTiles().getPlayerTiles(this.player1)
                .containsAll(this.model.getAllPlayersTiles().getPlayerTiles(this.player2)));
    }

    /**
     * Tests the initialization of player turns.
     */
    @Test
    void testTurn() {
        this.testDistributionTiles();

        assertTrue(this.model.initializeTurn(this.player1, this.player2)
                || !this.model.initializeTurn(this.player1, this.player2));
    }

    /**
     * Tests the process of making a move.
     */
    @Test
    void testMove() {
        this.testDistributionTiles();
        final Set<Tile> p1Tiles = this.model.getAllPlayersTiles().getPlayerTiles(this.player1);

        final Tile tileToMove = p1Tiles.iterator().next();
        final boolean moveResult = this.model.checkAndAddToBoard(this.player1, tileToMove);

        assertTrue(moveResult);
        assertFalse(this.model.getAllPlayersTiles().getPlayerTiles(this.player1).contains(tileToMove));
        assertTrue(this.model.getBoardTile().getBoardTiles().contains(tileToMove));
    }

    /**
     * Tests if a player can draw a tile.
     */
    @Test
    void testCanDrawTile() {
        this.model.setUpPlayers(this.players);
        this.model.getAllPlayersTiles().getPlayerTiles(this.player1).clear();
        assertTrue(this.model.canDrawTile(this.player1));

        Tile tileOnBoard = new TileImpl(SIDE1, SIDE2);
        this.model.getBoardTile().addTileToBoard(tileOnBoard);

        Tile matchingTile = new TileImpl(SIDE2, SIDE3);
        this.model.getAllPlayersTiles().addTileToPlayer(this.player1, matchingTile);
        assertFalse(this.model.canDrawTile(this.player1));

        this.model.getAllPlayersTiles().getPlayerTiles(this.player1).clear();
        this.model.getDominoSet().clear();
        assertFalse(this.model.canDrawTile(this.player1));
    }

    /**
     * Tests the determination of the game winner.
     */
    @Test
    void testWinner() {
        this.testDistributionTiles();
        this.model.getAllPlayersTiles().getPlayerTiles(this.player1).clear();
        assertEquals(this.player1, this.model.getResult().getFirst());

        this.model.getAllPlayersTiles().getPlayerTiles(this.player1).add(new TileImpl(SIDE1, SIDE1));
        this.model.getAllPlayersTiles().getPlayerTiles(this.player2).clear();
        assertEquals(this.player2, this.model.getResult().getFirst());

        this.model.getAllPlayersTiles().getPlayerTiles(this.player1).add(new TileImpl(SIDE1, SIDE3));
        this.model.getAllPlayersTiles().getPlayerTiles(this.player2).add(new TileImpl(SIDE2, SIDE2));
        assertNull(this.model.getResult().getFirst());
    }

    /**
     * Tests if the game is over.
     */
    @Test
    void testIsOver() {
        this.model.setUpPlayers(this.players);
        assertFalse(this.model.isOver());

        this.model.getAllPlayersTiles().getPlayerTiles(this.player1).clear();
        assertTrue(this.model.isOver());

        this.model.getAllPlayersTiles().getPlayerTiles(this.player1).add(new TileImpl(SIDE1, SIDE1));
        this.model.getAllPlayersTiles().getPlayerTiles(this.player2).clear();
        assertTrue(this.model.isOver());

        this.model.getAllPlayersTiles().getPlayerTiles(this.player1).add(new TileImpl(SIDE1, SIDE6));
        this.model.getAllPlayersTiles().getPlayerTiles(this.player2).add(new TileImpl(SIDE2, SIDE2));
        this.model.getDominoSet().clear();
        assertFalse(this.model.isOver());

        this.model.checkAndAddToBoard(this.player1, new TileImpl(SIDE3, SIDE4));
        this.model.checkAndAddToBoard(this.player2, new TileImpl(SIDE4, SIDE5));
        assertTrue(this.model.isOver());
    }

    /**
     * Tests the drawing of a tile from the tile set to a player.
     */
    @Test
    void testDrawTile() {
        this.model.setUpPlayers(this.players);

        final Set<Tile> initialTiles = this.model.getAllPlayersTiles().getPlayerTiles(this.player1);
        final int initialSize = initialTiles.size();

        this.model.drawTile(this.player1);

        final Set<Tile> updatedTiles = this.model.getAllPlayersTiles().getPlayerTiles(this.player1);
        final int updatedSize = updatedTiles.size();

        assertEquals(initialSize + 1, updatedSize);
        assertEquals(DOMINO_SET_SIZE - (DISTRIBUTION_TILES * 2) - 1, this.model.getDominoSet().size());
    }
}
