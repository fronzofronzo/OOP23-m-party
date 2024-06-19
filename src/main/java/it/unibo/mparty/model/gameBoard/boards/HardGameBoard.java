package it.unibo.mparty.model.gameBoard.boards;

import java.util.List;
import java.util.Set;

import it.unibo.mparty.model.gameBoard.impl.AbstractBoardImpl;
import it.unibo.mparty.model.gameBoard.util.BoardType;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public class HardGameBoard extends AbstractBoardImpl{

    public HardGameBoard(int width, int height, Position initialPosition) {
        super();
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

    @Override
    protected Set<Position> setStarsPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setStarsPosition'");
    }

    @Override
    protected int setWidth() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setWidth'");
    }

    @Override
    protected int setHeight() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHeight'");
    }

    @Override
    protected Position setInitialPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setInitialPosition'");
    }

    @Override
    protected List<SlotType> setAviableSlotType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAviableSlotType'");
    }
    
}
