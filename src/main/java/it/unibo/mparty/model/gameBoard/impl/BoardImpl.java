package it.unibo.mparty.model.gameBoard.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.mparty.model.gameBoard.api.Board;
import it.unibo.mparty.model.gameBoard.api.Slot;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public class BoardImpl implements Board{

    private final int width;
    private final int height;
    private final Position initialPosition;
    private Map<Position,Slot> myBoard = new HashMap<>();

    public BoardImpl(int lenght, int height, Position initialPosition){
        this.width = lenght;
        this.height = height;
        this.initialPosition = initialPosition;
    }

    @Override
    public void addSlot(Position position, SlotType slotType) {
        if (isValidPosition(position)){
            this.myBoard.put(position, new SlotImpl(position, slotType));
        }
    }

    private boolean isValidPosition(Position position) {
        return position.getX() < this.width && position.getY() < this.height;
    }

    @Override
    public void addConnection(Position from, Position to, Direction dir) {
        if (this.myBoard.containsKey(from) && this.myBoard.containsKey(to)) {
            this.myBoard.get(from).addConnection(dir, to);
        };
    }

    @Override
    public Slot getSlot(Position position) {
        return this.myBoard.containsKey(position) ? this.myBoard.get(position) : new SlotImpl(position, SlotType.EMPTY);
    }

    @Override
    public Position getInitialPosition() {
        return this.initialPosition;
    }
    
}
