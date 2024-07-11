package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.utilities.BoardType;

/**
 * This class is a factory that creates a {@link GameBoard} based on the selected {@link BoardType}.
 */
public class SimpleBoardFactory {

    /**
     * Return the {@link GameBoard} based on the selected {@link BoardType}.
     * @param boardSelected the idicates the {@link BoardType}
     * @return the {@link GameBoard}.
     */
    public GameBoard createBoard(BoardType boardSelected) {
        switch (boardSelected) {
            case EASY: return new EasyGameBoard();
            case MEDIUM: return new MediumGameBoard();
            case HARD: return new HardGameBoard();
            default: throw new IllegalStateException();
        }
    }    
}
