package it.unibo.mparty.model.gameBoard.api;

import java.util.Map;

import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public interface Board {

    void generateBoard();

    Slot getSlot(Position position);

    SlotType getSlotType(Position position);
    
    Position getInitialPosition();
    
    Map<Direction, Position> getNextPositions(Position position);
    
    Position getStarPosition();

    void changeStarPosition();
}