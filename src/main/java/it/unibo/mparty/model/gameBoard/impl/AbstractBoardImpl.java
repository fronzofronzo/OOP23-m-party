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
        return this.myBoard.containsKey(position) ? this.myBoard.get(position) : new SlotImpl(position, SlotType.VOID);
    }
    
    @Override
    public SlotType getSlotType(Position position) {
        return this.myBoard.containsKey(position) ? this.myBoard.get(position).getSlotType() : SlotType.VOID;
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

    protected boolean addSlot(Position position, SlotType slotType) {
        if (!this.myBoard.containsKey(position) && isValidPosition(position)){
            this.myBoard.put(position, new SlotImpl(position, slotType));
            return true;
        }
        return false;
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

    protected void addConnections(Set<Position> positions, Direction dir){
        for (Position p : positions) {
            Position nextP = new Position(p.getX() + (dir.equals(Direction.RIGHT) ? 1 : 0) + (dir.equals(Direction.LEFT) ? -1 : 0), p.getY() + (dir.equals(Direction.UP) ? -1 : 0) + (dir.equals(Direction.DOWN) ? 1 : 0));
            if (positions.contains(nextP)) {
                this.addConnection(p, nextP, dir);
            }
        }
    }

    protected void addSlots(Set<Set<Position>> positions){
        positions.stream().forEach(s -> s.stream().forEach(p -> this.addSlot(p, this.getNewSlotType())));
    }

    protected abstract SlotType getNewSlotType();

    @Override
    public void printBoard(){
        for(int j = 0; j < this.height; j++){
            String line = "";
            for(int i = 0; i < this.width; i++){
                SlotType slotType = this.myBoard.get(new Position(i, j)).getSlotType();
                String c = "";
                switch (slotType) {
                    case VOID: c = "."; break;
                    case ACTIVE_STAR: c = "*"; break;
                    case PATH: c = "O"; break;
                    case SINGLEPLAYER: c = "1"; break;
                    case MULTIPLAYER: c = "2"; break;
                    case BONUS: c = "B"; break;
                    case MALUS: c = "M"; break;
                    case NOT_ACTIVE_STAR: c = "s"; break;
                    case SHOP: c = "I"; break;
                    default: c = "."; break;
                } 
                line = line + c;
            }
            System.out.println(line);
        }
    }
}
