package it.unibo.mparty.model.gameBoard.impl;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

import it.unibo.mparty.model.gameBoard.api.Slot;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

/**
 * The class SlotImpl implements the interfae {@link Slot}. Each slot is
 * identified
 * by a {@link Position} and a {@link SlotType}, then it could have connections
 * to others slots.
 */
public class SlotImpl implements Slot {

    private final Position position;
    private SlotType slotType;
    private final Map<Direction, Position> nextConnections;
    private final Map<Direction, Position> prevConnections;

    /**
     * Create a new slot.
     * 
     * @param position of the slot.
     * @param slotType of the slot.
     */
    public SlotImpl(final Position position, final SlotType slotType) {
        this.position = position;
        this.slotType = slotType;
        this.nextConnections = new HashMap<>();
        this.prevConnections = new HashMap<>();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public SlotType getSlotType() {
        return this.slotType;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void changeSlotType(final SlotType newSlotType) {
        this.slotType = newSlotType;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void addNext(final Direction dir, final Position position) {
        this.checkPosition(position);
        this.nextConnections.put(dir, position);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void addPrev(final Direction dir, final Position position) {
        this.checkPosition(position);
        this.prevConnections.put(dir, position);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Map<Direction, Position> getNextConnections() {
        return Collections.unmodifiableMap(this.nextConnections);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Map<Direction, Position> getPrevConnections() {
        return Collections.unmodifiableMap(this.prevConnections);
    }

    private void checkPosition(final Position position) {
        if (this.position.equals(position)) {
            throw new IllegalStateException();
        }
    }
}
