package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileImplTest {

    private Tile tile;

    @Test
    void testCanMatchLeft() {
        this.tile = new TileImpl(3, 5);
        assertTrue(this.tile.canMatchLeft(3));
        assertFalse(this.tile.canMatchLeft(4));

        this.tile = new TileImpl(3, 3);
        assertTrue(this.tile.canMatchLeft(3));

        this.tile = new TileImpl(6, 1);
        assertTrue(this.tile.canMatchLeft(6));
        assertFalse(this.tile.canMatchLeft(2));

        this.tile = new TileImpl(0, 0);
        assertTrue(this.tile.canMatchLeft(0));
    }

    @Test
    void testCanMatchRight() {
        this.tile = new TileImpl(3, 5);
        assertTrue(this.tile.canMatchRight(5));
        assertFalse(this.tile.canMatchRight(4));

        this.tile = new TileImpl(3, 3);
        assertTrue(this.tile.canMatchRight(3));

        this.tile = new TileImpl(2, 4);
        assertTrue(this.tile.canMatchRight(4));
        assertFalse(this.tile.canMatchRight(1));

        this.tile = new TileImpl(0, 0);
        assertTrue(this.tile.canMatchRight(0));
    }

    @Test
    void testIsDoubleSide() {
        this.tile = new TileImpl(3, 3);
        assertTrue(this.tile.isDoubleSide());

        this.tile = new TileImpl(0, 3);
        assertFalse(this.tile.isDoubleSide());

        this.tile = new TileImpl(1, 1);
        assertTrue(this.tile.isDoubleSide());

        this.tile = new TileImpl(5, 2);
        assertFalse(this.tile.isDoubleSide());
    }
}
