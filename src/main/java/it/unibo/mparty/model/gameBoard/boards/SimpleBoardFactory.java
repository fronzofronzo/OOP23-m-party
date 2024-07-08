package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.utilities.BoardType;

/**
 * This class is a factory that creates a board based on a selection
 */
public class SimpleBoardFactory {

    public GameBoard createBoard(BoardType boardSelected) {
        switch (boardSelected) {
            case EASY: return new EasyGameBoard();
            case MEDIUM: return new MediumGameBoard();
            case HARD: return new HardGameBoard();
            default: return new EasyGameBoard();
        }
    }    
}
