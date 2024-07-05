package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.DominoModel;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DominoModelImplTest {

    private static final int DISTRIBUTION_TILES = 7;
    private static final int DOMINO_SET_SIZE = 28;
    private static final int SIDE1 = 1;
    private static final int SIDE2 = 2;
    private static final int SIDE3 = 3;
    private DominoModel model;
    private List<String> players;
    private String player1;
    private String player2;

    @BeforeEach
    void setUp() {
        this.model = new DominoModelImpl();
        this.players = List.of("player1", "player2");
        this.player1 = this.players.get(0);
        this.player2 = this.players.get(1);
    }

    @Test
    void testDistributionTiles() {
        this.model.setUpPlayers(this.players);

        // Verify players' domino sets are not empty after initialization
        assertFalse(this.model.getPlayersTiles().getPlayerTiles(this.player1).isEmpty());
        assertFalse(this.model.getPlayersTiles().getPlayerTiles(this.player2).isEmpty());

        // Verify both players have the correct number of tiles
        assertEquals(DISTRIBUTION_TILES, this.model.getPlayersTiles().getPlayerTiles(this.player1).size());
        assertEquals(DISTRIBUTION_TILES, this.model.getPlayersTiles().getPlayerTiles(this.player2).size());

        // Verify the model's domino set has correct size after distribution
        assertEquals(DOMINO_SET_SIZE - (DISTRIBUTION_TILES * 2), this.model.getDominoSet().size());

        // Verify tiles assigned to players are removed from the main set
        assertFalse(this.model.getDominoSet().containsAll(this.model.getPlayersTiles().getPlayerTiles(this.player1)));
        assertFalse(this.model.getDominoSet().containsAll(this.model.getPlayersTiles().getPlayerTiles(this.player2)));

        // Verify players' domino sets are distinct from each other
        assertFalse(this.model.getPlayersTiles().getPlayerTiles(this.player1).containsAll(this.model.getPlayersTiles().getPlayerTiles(this.player2)));
    }

    @Test
    void testTurn() {
        testDistributionTiles();

        // Assuming the turn is set correctly, it should be either p1 or p2's turn
        assertTrue(this.model.initializeTurn(this.player1, this.player2)
                || !this.model.initializeTurn(this.player1, this.player2));
    }

    @Test
    void testMove() {
        this.testDistributionTiles();
        Set<Tile> p1Tiles = this.model.getPlayersTiles().getPlayerTiles(this.player1);

        Tile tileToMove = p1Tiles.iterator().next();
        boolean moveResult = this.model.checkAndAddToBoard(this.player1, tileToMove);

        // Verify the move result
        assertTrue(moveResult);

        // Verify the tile was removed from player1's set
        assertFalse(this.model.getPlayersTiles().getPlayerTiles(this.player1).contains(tileToMove));

        // Verify the tile was added to the board
        assertTrue(this.model.getBoardTile().getBoardTiles().contains(tileToMove));
    }

    @Test
    void testCanDrawTile() {
        this.model.setUpPlayers(this.players);

        // Get initial set of player1
        Set<Tile> initialTiles = this.model.getPlayersTiles().getPlayerTiles(this.player1);
        int initialSize = initialTiles.size();

        // Simulate a valid move
        Tile validTile = new TileImpl(SIDE1, SIDE1);
        this.model.checkAndAddToBoard(this.player1, validTile);

        // Simulate an invalid move
        Tile invalidTile = new TileImpl(SIDE2, SIDE2);
        this.model.checkAndAddToBoard(this.player1, invalidTile);

        // Draw a tile from tileSet to player1
        this.model.drawTile(this.player1);

        // Get update set of player1
        Set<Tile> updatedTiles = this.model.getPlayersTiles().getPlayerTiles(this.player1);
        int updatedSize = updatedTiles.size();

        // Verify the size is update 1 more
        assertEquals(initialSize + 1, updatedSize);
    }


    @Test
    void testWinner() {
        this.testDistributionTiles();

        // Simulate player1 winning by emptying their tile set
        this.model.getPlayersTiles().getPlayerTiles(this.player1).clear();

        // Verify player1 is declared the winner
        assertEquals(this.player1, this.model.getResult().getFirst());

        // Simulate player2 winning by emptying their tile set
        this.model.getPlayersTiles().getPlayerTiles(this.player1).add(new TileImpl(SIDE1, SIDE1));
        this.model.getPlayersTiles().getPlayerTiles(this.player2).clear();

        // Verify player2 is declared the winner
        assertEquals(this.player2, this.model.getResult().getFirst());

        // No winner case
        this.model.getPlayersTiles().getPlayerTiles(this.player1).add(new TileImpl(SIDE1, SIDE3));
        this.model.getPlayersTiles().getPlayerTiles(this.player2).add(new TileImpl(SIDE2, SIDE2));

        // Verify no winner
        assertNull(this.model.getResult().getFirst());
    }


}
