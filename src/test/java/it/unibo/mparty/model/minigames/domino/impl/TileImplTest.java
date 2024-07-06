package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.model.minigames.domino.tile.impl.TileImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TileImplTest {

    private static final int SIDE_0 = 0;
    private static final int SIDE_1 = 1;
    private static final int SIDE_2 = 2;
    private static final int SIDE_3 = 3;
    private static final int SIDE_4 = 4;
    private static final int SIDE_5 = 5;
    private static final int SIDE_6 = 6;

    private Tile tile;

    @Test
    void testTile() {
        this.tile = new TileImpl(SIDE_0, SIDE_1);
        assertEquals(SIDE_0, this.tile.getSideA().getValue());
        assertEquals(SIDE_1, this.tile.getSideB().getValue());
    }

    @Test
    void testMatch() {
        this.tile = new TileImpl(SIDE_3, SIDE_5);
        Tile tile1 = new TileImpl(SIDE_1, SIDE_2);
        Tile tile2 = new TileImpl(SIDE_3, SIDE_3);
        Tile tile3 = new TileImpl(SIDE_6, SIDE_4);

        assertFalse(this.tile.match(tile1));
        assertTrue(this.tile.match(tile2));
        assertFalse(this.tile.match(tile3));
    }

    @Test
    void testIsDoubleSide() {
        this.tile = new TileImpl(SIDE_3, SIDE_3);
        assertTrue(this.tile.isDoubleSide());

        this.tile = new TileImpl(SIDE_0, SIDE_3);
        assertFalse(this.tile.isDoubleSide());

        this.tile = new TileImpl(SIDE_1, SIDE_1);
        assertTrue(this.tile.isDoubleSide());

        this.tile = new TileImpl(SIDE_5, SIDE_2);
        assertFalse(this.tile.isDoubleSide());
    }

    @Test
    void testReverse() {
        this.tile = new TileImpl(SIDE_1, SIDE_2);
        this.tile.reverse();
        assertEquals(SIDE_2, this.tile.getSideA().getValue());
        assertEquals(SIDE_1, this.tile.getSideB().getValue());
    }

    @Test
    void testCanMatch() {
        this.tile = new TileImpl(SIDE_2, SIDE_5);
        Tile tile1 = new TileImpl(SIDE_1, SIDE_2);
        Tile tile2 = new TileImpl(SIDE_6, SIDE_5);
        Tile tile3 = new TileImpl(SIDE_3, SIDE_4);

        assertTrue(this.tile.canMatch(tile1));
        assertTrue(this.tile.canMatch(tile2));
        assertFalse(this.tile.canMatch(tile3));
    }
}
