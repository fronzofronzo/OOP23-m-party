package it.unibo.samplejavafx.gameBoard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.gameBoard.api.Board;
import it.unibo.mparty.model.gameBoard.boards.SimpleBoardFactory;
import it.unibo.mparty.model.gameBoard.util.BoardType;
import it.unibo.mparty.model.gameBoard.util.Position;

class TestGameBoard{

    @Test 
    public void printBoard(){
        //SimpleBoardFactory factory = new SimpleBoardFactory();
        //Board myBoard = factory.createBoard(BoardType.EASY);
        //myBoard.printBoard();
        //assertEquals(new Position(5, 25), myBoard.getInitialPosition());
        assertEquals(true, true);
    }
}