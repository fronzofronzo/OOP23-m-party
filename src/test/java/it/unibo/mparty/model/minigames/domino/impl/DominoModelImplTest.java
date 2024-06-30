package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.DominoModel;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DominoModelImplTest {

    private static final int DISTRIBUTION_TILES = 7;
    private static final int DOMINO_SET_SIZE = 28;
    private static final int SIDE1 = 1;
    private static final int SIDE2 = 2;
    private static final int SIDE3 = 3;
    private DominoModel model;
    private Player p1;
    private Player p2;

    @BeforeEach
    void setUp() {
        this.model = new DominoModelImpl();

        this.p1 = new PlayerImplementation("player1", "Mario");
        this.p2 = new PlayerImplementation("player2", "Luigi");
    }

    @Test
    void testDistributionTiles() {
        this.model.initDomino(this.p1, this.p2);

        // Verify players' domino sets are not empty after initialization
        assertFalse(this.model.getPlayerTiles().getPlayerTiles(this.p1).isEmpty());
        assertFalse(this.model.getPlayerTiles().getPlayerTiles(this.p2).isEmpty());

        // Verify both players have the correct number of tiles
        assertEquals(DISTRIBUTION_TILES, this.model.getPlayerTiles().getPlayerTiles(this.p1).size());
        assertEquals(DISTRIBUTION_TILES, this.model.getPlayerTiles().getPlayerTiles(this.p2).size());

        // Verify the model's domino set has correct size after distribution
        assertEquals(DOMINO_SET_SIZE - (DISTRIBUTION_TILES * 2), this.model.getDominoSet().size());

        // Verify tiles assigned to players are removed from the main set
        assertFalse(this.model.getDominoSet().containsAll(this.model.getPlayerTiles().getPlayerTiles(this.p1)));
        assertFalse(this.model.getDominoSet().containsAll(this.model.getPlayerTiles().getPlayerTiles(this.p2)));

        // Verify players' domino sets are distinct from each other
        assertFalse(this.model.getPlayerTiles().getPlayerTiles(this.p1).containsAll(this.model.getPlayerTiles().getPlayerTiles(this.p2)));
    }

    @Test
    void testTurn() {
        testDistributionTiles();

        // Assuming the turn is set correctly, it should be either p1 or p2's turn
        assertTrue(this.model.isPlayer1Turn(this.p1, this.p2)
                || !this.model.isPlayer1Turn(this.p1, this.p2));
    }

    @Test
    void testMove() {
        this.testDistributionTiles();
        Set<Tile> p1Tiles = this.model.getPlayerTiles().getPlayerTiles(this.p1);

        Tile tileToMove = p1Tiles.iterator().next();
        boolean moveResult = this.model.checkMove(this.p1, tileToMove);

        // Verify the move result
        assertTrue(moveResult);

        // Verify the tile was removed from player1's set
        assertFalse(this.model.getPlayerTiles().getPlayerTiles(this.p1).contains(tileToMove));

        // Verify the tile was added to the board
        assertTrue(this.model.getBoardTile().getBoardTiles().contains(tileToMove));
    }

    @Test
    void testWinner() {
        this.testDistributionTiles();

        // Simulate player1 winning by emptying their tile set
        this.model.getPlayerTiles().getPlayerTiles(this.p1).clear();

        // Verify player1 is declared the winner
        assertEquals(this.p1, this.model.getWinner(this.p1, this.p2));

        // Simulate player2 winning by emptying their tile set
        this.model.getPlayerTiles().getPlayerTiles(this.p1).add(new TileImpl(SIDE1, SIDE1));
        this.model.getPlayerTiles().getPlayerTiles(this.p2).clear();

        // Verify player2 is declared the winner
        assertEquals(this.p2, this.model.getWinner(this.p1, this.p2));

        // No winner case
        this.model.getPlayerTiles().getPlayerTiles(this.p1).add(new TileImpl(SIDE1, SIDE3));
        this.model.getPlayerTiles().getPlayerTiles(this.p2).add(new TileImpl(SIDE2, SIDE2));

        // Verify no winner
        assertNull(this.model.getWinner(this.p1, this.p2));
    }
}
