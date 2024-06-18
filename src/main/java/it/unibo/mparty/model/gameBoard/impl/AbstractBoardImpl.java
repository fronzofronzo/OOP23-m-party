package it.unibo.mparty.model.gameBoard.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.mparty.model.gameBoard.api.Board;
import it.unibo.mparty.model.gameBoard.api.Slot;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public abstract class AbstractBoardImpl implements Board{

    private final int width;
    private final int height;
    private final Position initialPosition;
    private Map<Position,Slot> myBoard = new HashMap<>();

    public AbstractBoardImpl(int width, int height, Position initialPosition){
        this.width = width;
        this.height = height;
        this.initialPosition = initialPosition;
    }

    @Override
    public Slot getSlot(Position position) {
        return this.myBoard.containsKey(position) ? this.myBoard.get(position) : new SlotImpl(position, SlotType.EMPTY);
    }
    
    @Override
    public SlotType getSlotType(Position position) {
        return this.myBoard.containsKey(position) ? this.myBoard.get(position).getSlotType() : SlotType.EMPTY;
    }
    
    @Override
    public Position getInitialPosition() {
        return this.initialPosition;
    }

    //Lancia eccezione se position non ha next o position non è nella board
    @Override
    public Map<Direction, Position> getNextPositions(Position position) {
        return Collections.unmodifiableMap(this.myBoard.get(position).getNextConnections());
    }

    @Override
    public Position getStarPosition() {
        return  this.myBoard.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getSlotType().equals(SlotType.ACTIVE_STAR))
                .findFirst()
                .get()
                .getKey();
    }

    @Override
    public void changeStarPosition() {
        Set<Position> possibleNextStarPosition = this.myBoard.entrySet()
                                                .stream()
                                                .filter(entry -> entry.getValue().getSlotType().equals(SlotType.NOT_ACTIVE_STAR))
                                                .map(entry -> entry.getKey())
                                                .collect(Collectors.toSet());
        Position newStarPosition = possibleNextStarPosition.stream()
                                    .skip(new Random().nextInt(possibleNextStarPosition.size()))
                                    .findFirst()
                                    .get();
        Position oldStarPosition = this.getStarPosition();
        this.myBoard.get(newStarPosition).changeSlotType(SlotType.ACTIVE_STAR);
        this.myBoard.get(oldStarPosition).changeSlotType(SlotType.NOT_ACTIVE_STAR);
    }

    protected void addSlot(Position position, SlotType slotType) {
        if (isValidPosition(position)){
            this.myBoard.put(position, new SlotImpl(position, slotType));
        }
    }

    protected boolean isValidPosition(Position position) {
        return isInTheBoard(position);
    }

    private boolean isInTheBoard(Position position){
        return  position.getX() >= 0 && position.getX() < this.width &&
                position.getY() >= 0 && position.getY() < this.height;
    }

    protected void addConnection(Position from, Position to, Direction dir) {
        if (this.myBoard.containsKey(from) && this.myBoard.containsKey(to)) {
            this.myBoard.get(from).addNext(dir, to);
            this.myBoard.get(to).addPrev(dir, from);
        };
    }
}
