package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void testCanMatchLeft() {
        this.tile = new TileImpl(SIDE_3, SIDE_5);
        assertTrue(this.tile.canMatchLeft(SIDE_3));
        assertFalse(this.tile.canMatchLeft(SIDE_4));

        this.tile = new TileImpl(SIDE_3, SIDE_3);
        assertTrue(this.tile.canMatchLeft(SIDE_3));

        this.tile = new TileImpl(SIDE_6, SIDE_1);
        assertTrue(this.tile.canMatchLeft(SIDE_6));
        assertFalse(this.tile.canMatchLeft(SIDE_2));

        this.tile = new TileImpl(SIDE_0, SIDE_0);
        assertTrue(this.tile.canMatchLeft(SIDE_0));
    }

    @Test
    void testCanMatchRight() {
        this.tile = new TileImpl(SIDE_3, SIDE_5);
        assertTrue(this.tile.canMatchRight(SIDE_5));
        assertFalse(this.tile.canMatchRight(SIDE_4));

        this.tile = new TileImpl(SIDE_3, SIDE_3);
        assertTrue(this.tile.canMatchRight(SIDE_3));

        this.tile = new TileImpl(SIDE_2, SIDE_4);
        assertTrue(this.tile.canMatchRight(SIDE_4));
        assertFalse(this.tile.canMatchRight(SIDE_1));

        this.tile = new TileImpl(SIDE_0, SIDE_0);
        assertTrue(this.tile.canMatchRight(SIDE_0));
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
}
