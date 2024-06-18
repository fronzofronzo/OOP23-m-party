package it.unibo.mparty.model.gameBoard.api;

import java.util.Map;

import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public interface Slot {
    
    Position getPosition();
    
    SlotType getSlotType();

    void changeSlotType(SlotType newSlotType);
    
    void addNext(Direction dir, Position next);
    
    void addPrev(Direction dir, Position prev);
    
    boolean hasNext();
    
    Map<Direction, Position> getNextConnections();
    
    boolean hasPrev();
    
    Map<Direction, Position> getPrevConnections();
}