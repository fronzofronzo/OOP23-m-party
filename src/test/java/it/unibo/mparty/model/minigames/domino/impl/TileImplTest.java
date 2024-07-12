package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.model.minigames.domino.tile.impl.TileImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link TileImpl}.
 */
class TileImplTest {

    private static final int SIDE_0 = 0;
    private static final int SIDE_1 = 1;
    private static final int SIDE_2 = 2;
    private static final int SIDE_3 = 3;
    private static final int SIDE_4 = 4;
    private static final int SIDE_5 = 5;
    private static final int SIDE_6 = 6;

    private Tile tile;

    /**
     * Tests the creation of a {@code TileImpl} instance.
     */
    @Test
    void testTile() {
        this.tile = new TileImpl(SIDE_0, SIDE_1);
        assertEquals(SIDE_0, this.tile.getSideA().getValue());
        assertEquals(SIDE_1, this.tile.getSideB().getValue());
    }

    /**
     * Tests the {@code match} method.
     * Ensures that tiles match correctly based on their sides.
     */
    @Test
    void testMatch() {
        this.tile = new TileImpl(SIDE_3, SIDE_5);
        final Tile tile1 = new TileImpl(SIDE_1, SIDE_2);
        final Tile tile2 = new TileImpl(SIDE_3, SIDE_3);
        final Tile tile3 = new TileImpl(SIDE_6, SIDE_4);

        assertFalse(this.tile.match(tile1));
        assertTrue(this.tile.match(tile2));
        assertFalse(this.tile.match(tile3));
    }

    /**
     * Tests the {@code isDoubleSide} method.
     * Ensures that tiles correctly identify if both sides are the same.
     */
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

    /**
     * Tests the {@code reverse} method.
     * Ensures that the sides of the tile are correctly reversed.
     */
    @Test
    void testReverse() {
        this.tile = new TileImpl(SIDE_1, SIDE_2);
        this.tile.reverse();
        assertEquals(SIDE_2, this.tile.getSideA().getValue());
        assertEquals(SIDE_1, this.tile.getSideB().getValue());
    }

    /**
     * Tests the {@code canMatch} method.
     * Ensures that tiles can match correctly with another tile based on their sides.
     */
    @Test
    void testCanMatch() {
        this.tile = new TileImpl(SIDE_2, SIDE_5);
        final Tile tile1 = new TileImpl(SIDE_1, SIDE_2);
        final Tile tile2 = new TileImpl(SIDE_6, SIDE_5);
        final Tile tile3 = new TileImpl(SIDE_3, SIDE_4);

        assertTrue(this.tile.canMatch(tile1));
        assertTrue(this.tile.canMatch(tile2));
        assertFalse(this.tile.canMatch(tile3));
    }
}
