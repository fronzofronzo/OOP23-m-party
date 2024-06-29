package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Domino;
import it.unibo.mparty.model.minigames.domino.api.DominoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DominoFactoryImplTest {

    private final int DOMINO_SET_SIZE = 28;
    private DominoFactoryImpl dominoFactory;


    @BeforeEach
    void setUp() {
        dominoFactory = new DominoFactoryImpl();
    }

    @Test
    void testCreateFullSet() {
        Set<Domino> fullSet = dominoFactory.createFullSet();
        Set<Domino> expectedSet = generateFullSet();

        assertEquals(DOMINO_SET_SIZE, dominoFactory.createFullSet().size());
        assertTrue(fullSet.containsAll(expectedSet));
    }

    private Set<Domino> generateFullSet() {
        Set<Domino> fullSet = new HashSet<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                fullSet.add(new DominoImpl(i, j));
            }
        }
        return fullSet;
    }

}