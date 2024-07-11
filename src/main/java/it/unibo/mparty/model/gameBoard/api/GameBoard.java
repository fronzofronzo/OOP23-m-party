package it.unibo.mparty.model.gameBoard.api;

import java.util.Map;

import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

/**
 * This interface models a game board.
 */
public interface GameBoard {

    /**
     * Change tha actual {@link Position} of the {@link SlotType.ACTIVE_STAR}.
     */
    void changeStarPosition();

    /**
     * Get a {@link Position} that is in this game board then return its
     * {@link SlotType}.
     * 
     * @param position the position of the {@link Slot} interested.
     * @return the {@link SlotType} of the {@link Slot} that is in the
     *         {@link Position} requested.
     * @throws {@link IllegalStateException} if the input {@link Position} isn't in
     *                the game board.
     */
    SlotType getSlotType(Position position);

    /**
     * Get the game board's starting position
     * 
     * @return the starting position
     */
    Position getStrartingPosition();

    /**
     * Get the next(s) position(s) of the input position
     * 
     * @param position a position
     * @return a {@link Map} that contains any {@link Direction} and
     *         the corresponding {@link Position}.
     */
    Map<Direction, Position> getNextPositions(Position position);

    /**
     * Get the {@link Position} of the {@link SlotType.ACTIVE_STAR}.
     * 
     * @return the actual position of the {@link SlotType.ACTIVE_STAR}.
     * @throws {@link IllegalStateException} if there is not an
     *                {@link SlotType.ACTIVE_STAR} in the game board.
     */
    Position getStarPosition();

    /**
     * Get the game board's dimension.
     * 
     * @return a {@link Pair} containing width and height.
     */
    Pair<Integer, Integer> getDimension();

    /**
     * Get the {@link Map} of the game board that contains for each {@link Position}
     * the corrispondent {@link Slot}. This map contains only slots that are not
     * {@link SlotType.VOID}.
     * 
     * @return the {@link Map} of the game board that has as key a {@link Position}
     *         and it has
     *         as value a {@link Slot}.
     */
    Map<Position, Slot> getBoard();

    /**
     * Get the {@link Map} of the game board that contains for each {@link Position}
     * the corrispondent {@link SlotType}.
     * 
     * @return the {@link Map} of the game board that has as key a {@link Position}
     *         and it has
     *         as value a {@link SlotType}.
     */
    Map<Position, SlotType> getSlotTypeBoardConfiguration();

    /**
     * Get the board's {@link BoardType}.
     * 
     * @return the {@link BoardType} of this board.
     */
    BoardType getBoardType();

    /**
     * Get the {@link Map} that contains the positions that changed their slot type.
     * 
     * @return the {@link Map} that has as key a {@link Position} and it has as
     *         value the
     *         corrispondent {@link SlotType}.
     */
    Map<Position, SlotType> getModifiedSlots();
}
