package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.impl.AbstractBoardImpl;
import it.unibo.mparty.model.gameBoard.util.BoardType;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public class MediumGameBoard extends AbstractBoardImpl{

    public MediumGameBoard(int width, int height, Position initialPosition) {
        super(width, height, initialPosition);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void generateBoard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateBoard'");
    }

    @Override
    protected SlotType getNewSlotType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNewSlotType'");
    }

    @Override
    public BoardType getBoardType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBoardType'");
    }
    
}
