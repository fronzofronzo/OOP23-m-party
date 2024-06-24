package it.unibo.mparty.model.gameBoard.api;

import java.util.Map;

import it.unibo.mparty.model.gameBoard.util.BoardType;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.Pair;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.SlotType;

/**
 * This interface models a Game Board
 */

public interface GameBoard {

    /**
     * Get a Position in the Board Game then return its Slot Type
     * @param position 
     * @return the {@link SlotType}
     */
    SlotType getSlotType(Position position);
    
    /**
     * Get the game board's starting position 
     * @return the starting position
     */
    Position getStrartingPosition();
    
    /**
     * Get the next(s) position(s) of the input position
     * @param position a position 
     * @return a map that contains directions and positions
     */
    Map<Direction, Position> getNextPositions(Position position);
    
    /**
     * Get the actual position of the active star
     * @return the actual position of the active star
     */
    Position getStarPosition();

    /**
     * Change tha actual position of the active star
     */
    void changeStarPosition();

    /**
     * Get the board's dimension
     * @return width x height
     */
    Pair<Integer, Integer> getDimension();

    void printBoard();

    Map<Position,Slot> getBoard();

    BoardType getBoardType();
}