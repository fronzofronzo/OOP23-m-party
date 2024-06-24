package it.unibo.mparty.model.gameBoard.impl;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

import it.unibo.mparty.model.gameBoard.api.Slot;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public class SlotImpl implements Slot {

    private final Position position;
    private SlotType slotType;
    private Map<Direction,Position> nextConnections;
    private Map<Direction,Position> prevConnections;

    public SlotImpl(Position position, SlotType slotType){
        this.position = position;
        this.slotType = slotType;
        this.nextConnections = new HashMap<>();
        this.prevConnections = new HashMap<>();
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public SlotType getSlotType() {
        return this.slotType;
    }

    @Override
    public void changeSlotType(SlotType newSlotType) {
        this.slotType = newSlotType;
    }

    @Override
    public void addNext(Direction dir, Position position) {
        if (isValidConnection(dir, position)) {
            this.nextConnections.put(dir, position);
        }
    }

    @Override
    public void addPrev(Direction dir, Position position) {
        if (isValidConnection(dir, position)) {
            this.prevConnections.put(dir, position);
        }
    }

    protected boolean isValidConnection(Direction dir, Position position) {
        return isNeighbor(position);
    }

    private boolean isNeighbor(Position position) {
        return Math.abs(this.position.getX()-position.getX()+this.position.getY()-position.getY())==1;
    }

    @Override
    public boolean hasNext(){
        return !this.nextConnections.isEmpty();        
    }

    @Override
    public Map<Direction, Position> getNextConnections() {
        return Collections.unmodifiableMap(this.nextConnections);
    }

    @Override
    public boolean hasPrev(){
        return !this.prevConnections.isEmpty();        
    }

    @Override
    public Map<Direction, Position> getPrevConnections() {
        return Collections.unmodifiableMap(this.prevConnections);
    }    

    public String toString(){
        return "[Pos: " + this.position + "; SlotType: " + this.slotType + "; Connections:(" + this.nextConnections + ")]";
    }
}