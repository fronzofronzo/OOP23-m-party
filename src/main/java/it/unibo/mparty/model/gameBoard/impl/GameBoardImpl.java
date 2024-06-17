package it.unibo.mparty.model.gameBoard.impl;

import it.unibo.mparty.model.gameBoard.api.Board;
import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.model.gameBoard.util.Position;

public class GameBoardImpl implements GameBoard {

    private final Board myBoard = new BoardImpl(10, 10, new Position(0, 0));

    @Override
    public Position getinitialPosition() {
        return this.myBoard.getInitialPosition();
    }
}