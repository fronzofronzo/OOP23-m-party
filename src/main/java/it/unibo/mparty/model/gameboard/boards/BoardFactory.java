package it.unibo.mparty.model.gameboard.boards;

import it.unibo.mparty.model.gameboard.api.GameBoard;
import it.unibo.mparty.utilities.BoardType;

/**
 * This class is a static factory that creates a {@link GameBoard} based on the
 * selected {@link BoardType}.
 */
public final class BoardFactory {

    private BoardFactory() {
    }

    /**
     * Return the {@link GameBoard} based on the selected {@link BoardType}.
     * 
     * @param boardSelected the idicates the {@link BoardType}
     * @return the {@link GameBoard}.
     */
    public static GameBoard createBoard(final BoardType boardSelected) {
        switch (boardSelected) {
            case EASY:
                return new EasyGameBoard();
            case MEDIUM:
                return new MediumGameBoard();
            case HARD:
                return new HardGameBoard();
            default:
                throw new IllegalStateException();
        }
    }
}