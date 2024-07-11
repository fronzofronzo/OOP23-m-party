package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.board.impl.BoardTileImpl;
import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.model.minigames.domino.tile.impl.TileImpl;
import it.unibo.mparty.utilities.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for {@link BoardTileImpl}.
 */
class BoardTileImplTest {

    private static final int SIDE1 = 1;
    private static final int SIDE2 = 2;
    private static final int SIDE3 = 3;
    private static final int SIDE4 = 4;
    private static final int SIDE5 = 5;
    private static final int SIDE6 = 6;

    private BoardTileImpl boardTile;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        this.boardTile = new BoardTileImpl();
    }

    /**
     * Tests the {@code canMatchBoardTile} method.
     * Ensures that tiles can be matched and added to the board correctly.
     */
    @Test
    void testCanMatchBoardTile() {
        final Tile tile1 = new TileImpl(SIDE3, SIDE5);
        final Tile tile2 = new TileImpl(SIDE5, SIDE2);
        final Tile tile3 = new TileImpl(SIDE2, SIDE4);
        final Tile tile4 = new TileImpl(SIDE6, SIDE3);
        final Tile tile5 = new TileImpl(SIDE1, SIDE4);

        // Test placing the first tile on an empty board
        assertTrue(this.boardTile.canMatchBoardTile(tile1));
        this.boardTile.addTileToBoard(tile1);
        assertEquals(1, this.boardTile.getBoardTiles().size());
        assertTrue(this.boardTile.getBoardTiles().contains(tile1));

        // Test placing a matching tile on the right end
        assertTrue(this.boardTile.canMatchBoardTile(tile2));
        this.boardTile.addTileToBoard(tile2);
        assertEquals(2, this.boardTile.getBoardTiles().size());
        assertTrue(this.boardTile.getBoardTiles().contains(tile2));

        // Test placing a matching tile on the left end
        assertTrue(this.boardTile.canMatchBoardTile(tile4));
        this.boardTile.addTileToBoard(tile4);
        assertEquals(3, this.boardTile.getBoardTiles().size());
        assertTrue(this.boardTile.getBoardTiles().contains(tile4));

        // Test placing a non-matching tile
        assertFalse(this.boardTile.canMatchBoardTile(tile5));
        assertEquals(3, this.boardTile.getBoardTiles().size());
        assertFalse(this.boardTile.getBoardTiles().contains(tile5));

        // Test placing another matching tile on the right end
        assertTrue(this.boardTile.canMatchBoardTile(tile3));
        this.boardTile.addTileToBoard(tile3);
        assertEquals(4, this.boardTile.getBoardTiles().size());
        assertTrue(this.boardTile.getBoardTiles().contains(tile3));
    }

    /**
     * Tests the {@code getBoardTiles} method.
     * Ensures that tiles added to the board are retrieved correctly.
     */
    @Test
    void testGetBoardTiles() {
        final Tile tile1 = new TileImpl(SIDE1, SIDE2);
        final Tile tile2 = new TileImpl(SIDE2, SIDE4);
        final Tile tile3 = new TileImpl(SIDE5, SIDE1);

        this.boardTile.addTileToBoard(tile1);
        this.boardTile.addTileToBoard(tile2);
        this.boardTile.addTileToBoard(tile3);

        final List<Tile> tiles = this.boardTile.getBoardTiles();
        assertEquals(3, tiles.size());
        assertTrue(tiles.contains(tile1));
        assertTrue(tiles.contains(tile2));
        assertTrue(tiles.contains(tile3));
    }

    /**
     * Tests the {@code addTileToBoard} method.
     * Ensures that tiles are correctly added to the board.
     */
    @Test
    void testAddTileToBoard() {
        final Tile tile1 = new TileImpl(SIDE1, SIDE2);
        final Tile tile2 = new TileImpl(SIDE2, SIDE3);
        final Tile tile3 = new TileImpl(SIDE3, SIDE4);

        this.boardTile.addTileToBoard(tile1);
        assertEquals(1, this.boardTile.getBoardTiles().size());
        assertTrue(this.boardTile.getBoardTiles().contains(tile1));

        this.boardTile.addTileToBoard(tile2);
        assertEquals(2, this.boardTile.getBoardTiles().size());
        assertTrue(this.boardTile.getBoardTiles().contains(tile2));

        this.boardTile.addTileToBoard(tile3);
        assertEquals(3, this.boardTile.getBoardTiles().size());
        assertTrue(this.boardTile.getBoardTiles().contains(tile3));
    }

    /**
     * Tests the {@code notifyObservers} method.
     * Ensures that observers are correctly notified when a tile is added to the board.
     */
    @Test
    void testNotifyObservers() {
        final Tile tile1 = new TileImpl(SIDE1, SIDE2);
        final Tile tile2 = new TileImpl(SIDE2, SIDE3);

        this.boardTile.addTileToBoard(tile1);
        this.boardTile.addTileToBoard(tile2);

        final List<Pair<Integer, Integer>> expectedPairs = List.of(
                new Pair<>(SIDE1, SIDE2),
                new Pair<>(SIDE2, SIDE3)
        );

        final List<Pair<Integer, Integer>> actualPairs = this.boardTile.getBoardTiles().stream()
                .map(t -> new Pair<>(t.getSideA().getValue(), t.getSideB().getValue()))
                .toList();

        assertEquals(expectedPairs, actualPairs);
    }
}
