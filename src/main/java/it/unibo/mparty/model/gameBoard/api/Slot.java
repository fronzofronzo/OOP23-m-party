package it.unibo.mparty.model.gameBoard.api;

import java.util.Map;

import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

/**
 * This interface models a Slot that is in a {@link GameBoard}.
 */
public interface Slot {

    /**
     * Get the position of the Slot.
     * 
     * @return the {@link Position} of this slot.
     */
    Position getPosition();

    /**
     * Get the {@link SlotType} of the Slot.
     * 
     * @return the {@link SlotType} of this slot.
     */
    SlotType getSlotType();

    /**
     * Change the {@link SlotType} of the slot.
     * 
     * @param newSlotType the new {@link SlotType}.
     */
    void changeSlotType(SlotType newSlotType);

    /**
     * Add the forward connection from this slot to another slot.
     * 
     * @param dir the {@link Direction} of the next slot.
     * @param next the {@link Position} of the next slot.
     * @throws {@link IllegalStateException} if the input position is equals to the position of this slot.
     */
    void addNext(Direction dir, Position next);

    /**
     * Add the backward connection from another slot to this slot.
     * 
     * @param dir  the {@link Direction} of the previous slot.
     * @param prev the {@link Position} of the previous slot.
     * @throws {@link IllegalStateException} if the input position is equals to the position of this slot.
     */
    void addPrev(Direction dir, Position prev);

    /**
     * Get all the forwards connections.
     * 
     * @return a {@link Map} of the forwards connections.
     */
    Map<Direction, Position> getNextConnections();

    /**
     * Get all the backwards connections.
     * 
     * @return a {@link Map} of the backwards connections.
     */
    Map<Direction, Position> getPrevConnections();
}
