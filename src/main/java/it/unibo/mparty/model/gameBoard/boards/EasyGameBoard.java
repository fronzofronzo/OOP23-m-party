package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.impl.AbstractBoardImpl;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public class EasyGameBoard extends AbstractBoardImpl{

    public EasyGameBoard(int width, int height, Position initialPosition) {
        super(width, height, initialPosition);
    }

    @Override
    public void generateBoard() {
        this.addSlot(getInitialPosition(), SlotType.PATH);
    }

    @Override
    protected SlotType getNewSlotType() {
        return SlotType.VOID;
        
    }
}
