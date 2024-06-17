package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.api.Board;
import it.unibo.mparty.model.gameBoard.util.BoardType;

public class SimpleBoardFactory {

    public Board createBoard(BoardType boardSelected) {
        if (boardSelected.equals(BoardType.EASY)){
            return new EasyGameBoard(0, 0, null);
        }
        if (boardSelected.equals(BoardType.MEDIUM)){
            return new MediumGameBoard(0, 0, null);
        }
        return new HardGameBoard(0, 0, null);
    }
    
}
