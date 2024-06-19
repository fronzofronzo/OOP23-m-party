package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.api.Board;
import it.unibo.mparty.model.gameBoard.util.BoardType;
import it.unibo.mparty.model.gameBoard.util.Position;

public class SimpleBoardFactory {

    private static final int WIDTH = 40;
    private static final int HEIGHT = 30;
    private static final int INITIAL_X_MEDIUM_BOARD = 5;
    private static final int INITIAL_Y_MEDIUM_BOARD = 25;
    private static final int INITIAL_X_HARD_BOARD = 5;
    private static final int INITIAL_Y_HARD_BOARD = 25;

    public Board createBoard(BoardType boardSelected) {
        if (boardSelected.equals(BoardType.EASY)){
            return new EasyGameBoard();
        }
        if (boardSelected.equals(BoardType.MEDIUM)){
            return new MediumGameBoard(WIDTH, HEIGHT, new Position(INITIAL_X_MEDIUM_BOARD, INITIAL_Y_MEDIUM_BOARD));
        }
        return new HardGameBoard(WIDTH, HEIGHT, new Position(INITIAL_X_HARD_BOARD, INITIAL_Y_HARD_BOARD));
    }
    
}
