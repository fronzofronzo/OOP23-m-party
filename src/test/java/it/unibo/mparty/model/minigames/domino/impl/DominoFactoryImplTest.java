package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DominoFactoryImplTest {

    private final int DOMINO_SET_SIZE = 28;
    private TileFactoryImpl dominoFactory;


    @BeforeEach
    void setUp() {
        dominoFactory = new TileFactoryImpl();
    }

    @Test
    void testCreateFullSet() {
        Set<Tile> fullSet = dominoFactory.createDoubleSixSet();
        Set<Tile> expectedSet = generateFullSet();

        assertEquals(DOMINO_SET_SIZE, dominoFactory.createDoubleSixSet().size());
        assertTrue(fullSet.containsAll(expectedSet));
    }

    private Set<Tile> generateFullSet() {
        Set<Tile> fullSet = new HashSet<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                fullSet.add(new TileImpl(i, j));
            }
        }
        return fullSet;
    }

}