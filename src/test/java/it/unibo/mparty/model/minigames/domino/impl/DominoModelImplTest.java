package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.DominoModel;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DominoModelImplTest {

    private final int DISTRIBUTION_NUMBER = 7;
    private final int DOMINO_SET_SIZE = 28;
    private DominoModel model;
    private Player p1;
    private Player p2;

    @BeforeEach
    void setUp() {
        model = new DominoModelImpl();
        p1 = new PlayerImplementation("player1", "Mario");
        p2 = new PlayerImplementation("player2", "Luigi");
    }

    @Test
    void testDistributionDomino() {
        model.initDomino(p1, p2);
        assertEquals(DOMINO_SET_SIZE-DISTRIBUTION_NUMBER-DISTRIBUTION_NUMBER, model.getDominoSet().size());
        assertEquals(DISTRIBUTION_NUMBER, model.getPlayerDomino().getPlayerTiles(p1).size());
    }

}