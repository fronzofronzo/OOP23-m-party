package it.unibo.mparty.model.gameBoard.api;

import java.util.Map;

import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

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

    /**
     * Get the actual game board's map
     * @return the map of the actual board tha has as key a position and it has as value a {@link Slot}
     */
    Map<Position,Slot> getBoard();

    /**
     * Get the actual game board's map but with only SlotTypes
     * @return the map of the actual board tha has as key a position and it has as value a {@link Slot}
     */
    Map<Position,SlotType> getSlotTypeBoard();

    /**
     * Get the board's {@link BoardType}
     * @return the {@link BoardType}
     */
    BoardType getBoardType();
}