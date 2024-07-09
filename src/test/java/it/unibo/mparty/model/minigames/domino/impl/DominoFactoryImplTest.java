package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.model.minigames.domino.tile.impl.TileFactoryImpl;
import it.unibo.mparty.model.minigames.domino.tile.impl.TileImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link TileFactoryImpl}.
 */
class DominoFactoryImplTest {

    private static final int DOMINO_SET_SIZE = 28;
    private static final int MAX_TILE_VALUE = 6;
    private TileFactoryImpl dominoFactory;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        this.dominoFactory = new TileFactoryImpl();
    }

    /**
     * Tests the creation of the full domino set.
     * Ensures that the set contains the correct number of tiles and all expected tiles.
     */
    @Test
    void testCreateFullSet() {
        final List<Tile> fullSet = this.dominoFactory.createDoubleSixSet();
        final Set<Tile> expectedSet = this.generateFullSet();

        assertEquals(DOMINO_SET_SIZE, this.dominoFactory.createDoubleSixSet().size());
        assertTrue(fullSet.containsAll(expectedSet));
    }

    private Set<Tile> generateFullSet() {
        final Set<Tile> fullSet = new HashSet<>();
        for (int i = 0; i <= MAX_TILE_VALUE; i++) {
            for (int j = i; j <= MAX_TILE_VALUE; j++) {
                fullSet.add(new TileImpl(i, j));
            }
        }
        return fullSet;
    }
}
