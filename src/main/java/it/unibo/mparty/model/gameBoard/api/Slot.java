package it.unibo.mparty.model.gameBoard.api;

import java.util.Map;

import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.SlotType;

/**
 * This interface models a Slot, that it is like a graph'node
 */
public interface Slot {
    
    /**
     * Get the position of the Slot
     * @return the position of the slot
     */
    Position getPosition();
    
    /**
     * Get the {@link SlotType} of the Slot
     * @return the {@link SlotType}
     */
    SlotType getSlotType();

    /**
     * Change the SlotType of the slot
     * @param newSlotType the new slot type that
     */
    void changeSlotType(SlotType newSlotType);
    
    /**
     * Add the forward connection from this slot to another slot
     * @param dir the {@link Direction} of the next slot
     * @param next the {@link Position} of the next slot
     */
    void addNext(Direction dir, Position next);
    
    /**
     * Add the backward connection from another slot to this slot
     * @param dir the {@link Direction} of the previous slot
     * @param next the {@link Position} of the previous slot
     */
    void addPrev(Direction dir, Position prev);
    
    /**
     * Check if the slot has forward(s) connection(s) 
     * @return True if the slot has one or more forward connections, False otherwise
     */
    boolean hasNext();
    
    /**
     * Get all the forwards connections
     * @return a map of the forwards connections
     */
    Map<Direction, Position> getNextConnections();
    
    /**
     * Check if the slot has backward(s) connection(s)
     * @return True if the slot has one or more forward connections, False otherwise
     */
    boolean hasPrev();
    
    /**
     * Get all the backwards connections
     * @return a map of the backwards connections
     */
    Map<Direction, Position> getPrevConnections();
}