package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.api.Board;
import it.unibo.mparty.model.gameBoard.util.BoardType;

public class SimpleBoardFactory {

    public Board createBoard(BoardType boardSelected) {
        switch (boardSelected) {
            case EASY: return new EasyGameBoard();
            case MEDIUM: return new MediumGameBoard();
            case HARD: return new HardGameBoard();
            default: return new EasyGameBoard();
        }
    }    
}
