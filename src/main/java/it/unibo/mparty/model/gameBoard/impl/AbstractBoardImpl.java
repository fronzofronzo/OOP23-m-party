package it.unibo.mparty.model.gameBoard.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.model.gameBoard.util.BoardType;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.Pair;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.RandomFromSet;
import it.unibo.mparty.model.gameBoard.util.RandomListGenerator;
import it.unibo.mparty.model.gameBoard.api.Slot;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public abstract class AbstractBoardImpl implements GameBoard{

    private final int width;
    private final int height;
    private final Position initialPosition;
    private final Set<Position> starsPositions;
    private Map<Position,Slot> board = new HashMap<>();
    protected List<SlotType> avaiableSlotTypes;
    private final Set<Pair<SlotType,Double>> rules;
    private final BoardType boardType; 

    private final String filePath;

    public AbstractBoardImpl(int width, int height, Position initialPosition, Set<Position> starPositions, String filePath, Set<Pair<SlotType,Double>> rules, BoardType boardType){
        this.width = width;
        this.height = height;
        this.initialPosition = initialPosition;
        this.starsPositions = starPositions;
        this.rules = rules;
        this.avaiableSlotTypes = setAviableSlotType();
        this.boardType = boardType;
        this.filePath = filePath;
        this.generateBoard();
    }
    
    private void generateBoard() {
        this.addSlot(RandomFromSet.get(this.starsPositions), SlotType.ACTIVE_STAR);
        this.starsPositions.stream().forEach(p -> this.addSlot(p, SlotType.NOT_ACTIVE_STAR));
        this.addSlot(this.getStrartingPosition(), SlotType.PATH);
        this.createPathFromFile(this.filePath);
        }

    @Override
    public SlotType getSlotType(Position position) {
        return this.board.containsKey(position) ? this.board.get(position).getSlotType() : SlotType.VOID;
    }
    
    @Override
    public Position getStrartingPosition() {
        return this.initialPosition;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Direction, Position> getNextPositions(Position position) {
        return this.getSlot(position).hasNext() ? Collections.unmodifiableMap(this.getSlot(position).getNextConnections()) : Collections.EMPTY_MAP;
    }

    @Override
    public Position getStarPosition() {
        return this.starsPositions.stream()
                                  .filter(p -> this.getSlotType(p).equals(SlotType.ACTIVE_STAR))
                                  .findFirst()
                                  .get();
    }

    @Override
    public void changeStarPosition() {
        Set<Position> possibleNextStarPosition = this.starsPositions.stream()
                                                                    .filter(p -> this.getSlotType(p).equals(SlotType.NOT_ACTIVE_STAR))
                                                                    .collect(Collectors.toSet());
        Position newStarPosition = RandomFromSet.get(possibleNextStarPosition);
        Position oldStarPosition = this.getStarPosition();
        this.board.get(newStarPosition).changeSlotType(SlotType.ACTIVE_STAR);
        this.board.get(oldStarPosition).changeSlotType(SlotType.NOT_ACTIVE_STAR);
    }

    @Override
    public Pair<Integer, Integer> getDimension() {
        return new Pair<Integer,Integer>(this.width, this.height);
    }

    /*
    @Override
    public void printBoard(){
        for(int j = 0; j < this.height; j++){
            String line = "";
            for(int i = 0; i < this.width; i++){
                SlotType slotType = this.getSlotType(new Position(i, j));
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
            System.out.println(j<10 ? " " + j + line : "" + j + line);
        }
    }
    */

    @Override
    public Map<Position,Slot> getBoard() {
        return Collections.unmodifiableMap(this.board);
    }

    private void addSlot(Position position, SlotType slotType) {
        if (!this.board.containsKey(position) && isValidPosition(position)){
            this.board.put(position, new SlotImpl(position, slotType));
        }
    }

    /* 
    private void addSlots(Set<Position> positions){
        positions.stream().forEach(p -> this.addSlot(p, this.getNewSlotType()));
    }
    */

    private void addConnection(Position from, Position to, Direction dir) {
        if (this.board.containsKey(from) && this.board.containsKey(to)) {
            this.board.get(from).addNext(dir, to);
            this.board.get(to).addPrev(dir, from);
        };
    }

    /* 
    private void addConnections(Set<Position> positions, Direction dir){
        for (Position p : positions) {
            Position nextP = getNeighbor(p, dir);
            if (positions.contains(nextP)) {
                this.addConnection(p, nextP, dir);
            }
        }
    }
    */

    protected boolean isValidPosition(Position position) {
        return isInTheBoard(position);
    }

    private boolean isInTheBoard(Position position){
        return  position.getX() >= 0 && position.getX() < this.width &&
                position.getY() >= 0 && position.getY() < this.height;
    }

    private void createPath(Position from, int steps, Direction currentDir){
        this.addSlot(from, getNewSlotType());
        Position to = this.getNeighbor(from, currentDir);
        for (int i = 0; i < steps; i++) {
            this.addSlot(to, getNewSlotType());
            this.addConnection(from, to, currentDir);
            from=to;
            to=this.getNeighbor(to, currentDir);
        }
    }

    private void createPathFromFile(String filePath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 4){
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    int steps = Integer.parseInt(parts[2]);
                    String d = parts[3];
                    Direction dir = d.equals("U") ? Direction.UP : d.equals("D") ? Direction.DOWN : d.equals("L") ? Direction.LEFT : Direction.RIGHT;
                    this.createPath(new Position(x, y), steps, dir);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Slot getSlot(Position position) {
        return this.board.containsKey(position) ? this.board.get(position) : new SlotImpl(position, SlotType.VOID);
    }

    protected Position getNeighbor(Position p, Direction dir) {
        return new Position(p.getX() + (dir.equals(Direction.RIGHT) ? 1 : 0) + (dir.equals(Direction.LEFT) ? -1 : 0), p.getY() + (dir.equals(Direction.UP) ? -1 : 0) + (dir.equals(Direction.DOWN) ? 1 : 0));
    }

    protected SlotType getNewSlotType(){
        if (this.avaiableSlotTypes.isEmpty()) {
            this.avaiableSlotTypes = setAviableSlotType();
        }
        SlotType output = this.avaiableSlotTypes.getFirst();
        this.avaiableSlotTypes.removeFirst();
        return output;
    }
    
    protected List<SlotType> setAviableSlotType() {
        return RandomListGenerator.generate(this.rules);
    }
    
    public BoardType getBoardType() {
        return this.boardType;
    }
}
