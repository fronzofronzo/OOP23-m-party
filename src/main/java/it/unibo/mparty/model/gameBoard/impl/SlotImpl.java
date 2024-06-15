package it.unibo.mparty.model.gameBoard.impl;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Optional;

import it.unibo.mparty.model.gameBoard.api.Slot;
import it.unibo.mparty.model.gameBoard.util.Coordinate;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public class SlotImpl implements Slot {

    private final Coordinate position;
    private final SlotType slotType;
    private Map<Direction,Slot> connections;

    public SlotImpl(Coordinate position, SlotType slotType){
        this.position = position;
        this.slotType = slotType;
        this.connections = new HashMap<>();
    }

    @Override
    public Coordinate getCoordinate() {
        return this.position;
    }

    @Override
    public void addConnection(Direction dir, Slot slot) {
        if (isValidConnection(slot)) {
            this.connections.put(dir, slot);
        }
    }

    protected boolean isValidConnection(Slot slot) {
        return isNeighbor(slot.getCoordinate());
    }

    private boolean isNeighbor(Coordinate coordinate) {
        return Math.abs(this.position.getX()-coordinate.getX())==1 
            && Math.abs(this.position.getY()-coordinate.getY())==0
            || Math.abs(this.position.getX()-coordinate.getX())==0 
            && Math.abs(this.position.getY()-coordinate.getY())==1;
    }

    @Override
    public Optional<Slot> getConnection(Direction dir) {
        return Optional.ofNullable(this.connections.get(dir));
    }

    @Override
    public Map<Direction, Slot> getConnections() {
        return Collections.unmodifiableMap(this.connections);
    }

    @Override
    public SlotType getSlotType() {
        return this.slotType;
    }

    @Override
    public void removeConnection(Direction dir) {
        this.connections.remove(dir);
    }
}