package it.unibo.mparty.model.minigames.perilouspath;


import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.impl.PathPosition;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPathModel;
import it.unibo.mparty.model.minigames.perilouspath.impl.PerilousPathModelImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * testing for {@link PerilousPathModelImpl}.
 */
class TestPerilousPathModel {

    private static final int SIDE = 8;
    private static final int NUM_BALLS = 2;
    private static final int NUM_BOMBS = 8;
    private static final int FIRST_COLUMN = 0;
    private static final int LAST_COLUMN = 7;

    private final PerilousPathModel model = new PerilousPathModelImpl(SIDE);

    /**
     * testing for {@link PerilousPathModelImpl} constructor.
     */
    @Test
    void testPerilousPathConstructor() {
        assertEquals(FIRST_COLUMN, this.model.getBalls().size());
        assertEquals(FIRST_COLUMN, this.model.getBombs().size());

    }

    /**
     * testing for method setBombs which sets the bombs in the grid.
     */
    @Test
    void testSetBombs() {
        this.model.setBombs();
        final var bombs = this.model.getBombs();
        assertEquals(NUM_BOMBS, bombs.size());
    }

    /**
     * testing for method setBombs which sets the balls in the grid.
     */
    @Test
    void testSetBalls() {
        this.model.setBalls();
        assertEquals(NUM_BALLS, this.model.getBalls().size());
        assertEquals(FIRST_COLUMN, this.model.getBalls().get(0).getY());
        assertEquals(LAST_COLUMN, this.model.getBalls().get(1).getY());
    }

    /**
     * test that simulates what happens when a generic button is clicked in the grid.
     */
    @Test
    void testHit() {
        this.model.setBalls();
        this.model.setBombs();
        final var p1 = this.model.getBalls().get(1);
        assertEquals(PerilousPathModel.Type.WRONG, this.model.hit(getPosition(p1)));
    }

    private PathPosition getPosition(final AbstractPosition p) {
        return new PathPosition(p.getX(), p.getY(), this.model.getSize());
    }

}