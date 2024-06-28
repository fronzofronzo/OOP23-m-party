package it.unibo.mparty.model.minigames.nanogram.api;

/**
 * Interface for a factory that creates boards for a Nanogram game.
 */
public interface BoardFactory {

    /**
     * Creates a simple board with the specified size and fill percentage.
     *
     * @param size the size of the board (both width and height).
     * @param fillPercentage the percentage of cells that should be filled.
     * @return a new Board instance configured as a simple board.
     */
    Board createSimpleBoard(int size, int fillPercentage);

    /**
     * Creates a hard board with the specified size, fill percentage, and show percentage.
     *
     * @param size the size of the board (both width and height).
     * @param fillPercentage the percentage of cells that should be filled.
     * @param showPercentage the percentage of the filled cells that should be shown to the player.
     * @return a new Board instance configured as a hard board.
     */
    Board createHardBoard(int size, int fillPercentage, int showPercentage);
}
