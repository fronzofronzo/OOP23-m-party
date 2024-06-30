package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTileImplTest {

    private static final int SIDE1 = 1;
    private static final int SIDE2 = 2;
    private static final int SIDE3 = 3;
    private static final int SIDE4 = 4;
    private static final int SIDE5 = 5;
    private static final int SIDE6 = 6;

    private BoardTileImpl boardTile;

    @BeforeEach
    void setUp() {
        boardTile = new BoardTileImpl();
    }

    @Test
    void canPlaceTile() {
        Tile tile1 = new TileImpl(SIDE3, SIDE5);
        Tile tile2 = new TileImpl(SIDE5, SIDE2);
        Tile tile3 = new TileImpl(SIDE2, SIDE4);
        Tile tile4 = new TileImpl(SIDE6, SIDE3);
        Tile tile5 = new TileImpl(SIDE1, SIDE4);

        // Test placing the first tile on an empty board
        assertTrue(boardTile.canPlaceTile(tile1));
        assertEquals(1, boardTile.getBoardTiles().size());
        assertTrue(boardTile.getBoardTiles().contains(tile1));

        // Test placing a matching tile on the right end
        assertTrue(boardTile.canPlaceTile(tile2));
        assertEquals(2, boardTile.getBoardTiles().size());
        assertTrue(boardTile.getBoardTiles().contains(tile2));

        // Test placing a matching tile on the left end
        assertTrue(boardTile.canPlaceTile(tile4));
        assertEquals(3, boardTile.getBoardTiles().size());
        assertTrue(boardTile.getBoardTiles().contains(tile4));

        // Test placing a non-matching tile
        assertFalse(boardTile.canPlaceTile(tile5));
        assertEquals(3, boardTile.getBoardTiles().size());
        assertFalse(boardTile.getBoardTiles().contains(tile5));

        // Test placing another matching tile on the right end
        assertTrue(boardTile.canPlaceTile(tile3));
        assertEquals(4, boardTile.getBoardTiles().size());
        assertTrue(boardTile.getBoardTiles().contains(tile3));
    }
}
