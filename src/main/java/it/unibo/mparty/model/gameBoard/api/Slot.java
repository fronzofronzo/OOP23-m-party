package it.unibo.mparty.model.gameBoard.api;

import java.util.Map;

import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public interface Slot {
    
    Position getPosition();
    
    SlotType getSlotType();

    boolean hasConnections();
    
    Position getConnection(Direction dir);
    
    Map<Direction, Position> getConnections();
    
    void addConnection(Direction dir, Position position);

    void removeConnection(Direction dir);
}