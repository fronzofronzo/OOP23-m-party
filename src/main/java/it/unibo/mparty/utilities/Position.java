package it.unibo.mparty.utilities;

/**
 * This record models a 2-D position.
 *
 * @param x - x axis coordinate.
 * @param y - y axis coordinate.
 */
public record Position(int x, int y) {

    /**
     * Method to get the x coordinate.
     *
     * @return - x coordinate.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Method to get the y coordinate.
     *
     * @return - y coordinate.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Method to get a standard position value.
     *
     * @return a position with standard coordinates.
     */
    public static Position getStandardPosition() {
        return new Position(0, 0);
    }
}
