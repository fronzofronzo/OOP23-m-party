package it.unibo.mparty.model.gameBoard.impl;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Optional;

import it.unibo.mparty.model.gameBoard.api.Slot;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public class SlotImpl implements Slot {

    private final Position position;
    private final SlotType slotType;
    private Map<Direction,Position> connections;

    public SlotImpl(Position position, SlotType slotType){
        this.position = position;
        this.slotType = slotType;
        this.connections = new HashMap<>();
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void addConnection(Direction dir, Position position) {
        if (isValidConnection(dir, position)) {
            this.connections.put(dir, position);
        }
    }

    protected boolean isValidConnection(Direction dir, Position position) {
        return !this.connections.containsKey(dir) && isNeighbor(position);
    }

    private boolean isNeighbor(Position position) {
        return Math.abs(this.position.getX()-position.getX())==1 
            && Math.abs(this.position.getY()-position.getY())==0
            || Math.abs(this.position.getX()-position.getX())==0 
            && Math.abs(this.position.getY()-position.getY())==1;
    }

    @Override
    public Position getConnection(Direction dir) {
        return this.connections.get(dir);
    }

    @Override
    public Map<Direction, Position> getConnections() {
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

    public String toString(){
        return "[Pos: " + this.position + "; SlotType: " + this.slotType + "; Connections:(" + this.connections + ")]";
    }

    @Override
    public boolean hasConnections() {
        return !this.connections.isEmpty();
    };
}