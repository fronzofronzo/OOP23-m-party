package it.unibo.mparty.model.gameBoard.api;

import java.util.Map;

import it.unibo.mparty.model.gameBoard.util.BoardType;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public interface Board {

    void generateBoard();

    SlotType getSlotType(Position position);
    
    Position getStrartingPosition();
    
    Map<Direction, Position> getNextPositions(Position position);
    
    Position getStarPosition();

    void changeStarPosition();

    void printBoard();

    Map<Position,Slot> getBoard();

    BoardType getBoardType();
}