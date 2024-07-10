package it.unibo.mparty.model.minigames.perilouspath.api;

import java.util.List;

import it.unibo.mparty.utilities.Pair;

/**
 * An abstract class for implementing the position  of a button inside a grid.
 * A generic button has a generic position (x,y) inside a cartesian system.
 */
public abstract class AbstractPosition {

    private final Pair<Integer, Integer> position;
    private final int size;
    private static final int ADJ = 1;

    /**
     * constructor of a new instance of {@link AbstractPosition}.
     *
     * @param x    the x position of a button in a grid
     * @param y    the y position of a button in a grid
     * @param size the size of the side of the grid
     */
    public AbstractPosition(final int x, final int y, final int size) {
        this.position = new Pair<>(x, y);
        this.size = size;
    }

    /**
     * @return the x position of a button
     */
    public int getX() {
        return position.getFirst();
    }

    /**
     * @return the y position of a button
     */
    public int getY() {
        return position.getSecond();
    }

    /**
     * @param p the position we want to check whether is adjacent to this
     * @return true if they are adjacent, false otherwise
     */
    public boolean adjacent(final AbstractPosition p) {
        return Math.abs(this.getX() - p.getX()) <= ADJ && Math.abs(this.getY() - p.getY()) <= ADJ;
    }

    /**
     * @return the size of the grid
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @param p the position we want to check whether is adjacent and in horizontal to this
     * @return true if they are adjacent and in horizontal, false otherwise
     */
    public boolean inHorizontal(final AbstractPosition p) {
        return this.getX() == p.getX() && this.adjacent(p);
    }

    /**
     * @param p the position we want to check whether is adjacent and in vertical to this
     * @return true if they are adjacent and in vertical, false otherwise
     */
    public boolean inVertical(final AbstractPosition p) {
        return this.getY() == p.getY() && this.adjacent(p);
    }

    /**
     *
     * @param p the position to be checked if is the same
     * @return true if they are in the same position, false otherwise
     */
    public boolean samePosition(final AbstractPosition p) {
        return this.getX() == p.getX() && this.getY() == p.getY();
    }

    /**
     * the politics regarding whether the position of a button inside a grid is safe or not, it depends on the implementation.
     *
     * @param list1 the first list of positions of buttons to compare to this to know if it is safe
     * @param list2 the second list of positions of buttons to compare to this to know if it is safe
     * @return true if the position is safe, false otherwise
     */
    public abstract boolean isSafe(List<AbstractPosition> list1, List<AbstractPosition> list2);

}


