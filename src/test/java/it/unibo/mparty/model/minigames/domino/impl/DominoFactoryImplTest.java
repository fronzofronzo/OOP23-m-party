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

class DominoFactoryImplTest {

    private static final int DOMINO_SET_SIZE = 28;
    private TileFactoryImpl dominoFactory;

    @BeforeEach
    void setUp() {
        this.dominoFactory = new TileFactoryImpl();
    }

    @Test
    void testCreateFullSet() {
        final List<Tile> fullSet = this.dominoFactory.createDoubleSixSet();
        final Set<Tile> expectedSet = this.generateFullSet();

        assertEquals(DOMINO_SET_SIZE, this.dominoFactory.createDoubleSixSet().size());
        assertTrue(fullSet.containsAll(expectedSet));
    }

    private Set<Tile> generateFullSet() {
        final Set<Tile> fullSet = new HashSet<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                fullSet.add(new TileImpl(i, j));
            }
        }
        return fullSet;
    }
}
