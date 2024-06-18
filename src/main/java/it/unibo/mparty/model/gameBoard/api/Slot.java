package it.unibo.mparty.model.gameBoard.api;

import java.util.Map;

import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public interface Slot {
    
    Position getPosition();
    
    SlotType getSlotType();
    
    void addNext(Direction dir, Position position);
    
    void addPrev(Direction dir, Position position);
    
    boolean hasNext();
    
    //Position getNextConnection(Direction dir);
    
    Map<Direction, Position> getNextConnections();
    
    boolean hasPrev();
    
    //Position getPrevConnection(Direction dir);
    
    Map<Direction, Position> getPrevConnections();

    //void removeConnection(Direction dir);
}