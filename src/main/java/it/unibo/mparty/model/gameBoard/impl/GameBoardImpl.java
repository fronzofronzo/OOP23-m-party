package it.unibo.mparty.model.gameBoard.impl;

import it.unibo.mparty.model.gameBoard.api.Board;
import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.model.gameBoard.boards.SimpleBoardFactory;
import it.unibo.mparty.model.gameBoard.util.BoardType;
import it.unibo.mparty.model.gameBoard.util.Position;

public class GameBoardImpl implements GameBoard {

    private SimpleBoardFactory factory = new SimpleBoardFactory();
    private final Board myBoard;

    public GameBoardImpl(BoardType boardSelected){
        this.myBoard = factory.createBoard(boardSelected);
    }
    @Override
    public Position getinitialPosition() {
        return this.myBoard.getInitialPosition();
    }
}