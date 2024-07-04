package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


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
    void canMatchBoardTile() {
        Tile tile1 = new TileImpl(SIDE3, SIDE5);
        Tile tile2 = new TileImpl(SIDE5, SIDE2);
        Tile tile3 = new TileImpl(SIDE2, SIDE4);
        Tile tile4 = new TileImpl(SIDE6, SIDE3);
        Tile tile5 = new TileImpl(SIDE1, SIDE4);

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
}
