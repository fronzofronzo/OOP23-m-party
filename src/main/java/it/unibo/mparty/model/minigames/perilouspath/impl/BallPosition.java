package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.List;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;

/**
 * implementation of {@link AbstractPosition} that models a path in a grid of button.
 */
public class BallPosition extends AbstractPosition {

    private static final int MINUS = 1;

    /**
     * constructor which calls the constructor of AbstractPosition.
     *
     * @param x    the x position of a button in a grid
     * @param y    the y position of a button in a grid
     * @param size the sie of the side of the grid
     */
    public BallPosition(final int x, final int y, final int size) {
        super(x, y, size);
    }

    /**
     * implementation of the abstract method isSafe().
     * in this case the position is safe only if it is on the first or the last column
     */
    @Override
    public boolean isSafe(final List<AbstractPosition> list1, final List<AbstractPosition> list2) {
        return (this.getY() == 0 || this.getY() == this.getSize() - MINUS)
                && (this.getX() != 0 && this.getX() != this.getSize() - MINUS);
    }

}
