package it.unibo.mparty.model.gameBoard.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    private static final int N_PARTS_INPUT_FILE = 4;
    private static final String UP = "U";
    private static final String DOWN = "D";
    private static final String RIGHT = "R";
    private static final String LEFT = "L";

    private final int width;
    private final int height;
    private final Position initialPosition;
    private final Set<Position> starsPositions;
    private Map<Position,Slot> board = new HashMap<>();
    private List<SlotType> avaiableSlotTypes;
    private final Set<Pair<SlotType,Double>> rules;
    private final BoardType boardType; 
    private final String filePath;

    public AbstractBoardImpl(int width,
                             int height,
                             Position initialPosition,
                             Set<Position> starPositions,
                             String filePath,
                             Set<Pair<SlotType,Double>> rules,
                             BoardType boardType){
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
    
    protected void generateBoard() {
        this.addSlot(RandomFromSet.get(this.starsPositions), SlotType.ACTIVE_STAR);
        this.starsPositions.stream()
                           .forEach(p -> this.addSlot(p, SlotType.NOT_ACTIVE_STAR));
        this.addSlot(this.getStrartingPosition(), SlotType.PATH);
        this.createPathFromFile(this.filePath);
        }

    @Override
    public SlotType getSlotType(Position position) {
        return this.board.containsKey(position) ? 
               this.board.get(position).getSlotType() : SlotType.VOID;
    }
    
    @Override
    public Position getStrartingPosition() {
        return this.initialPosition;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Direction, Position> getNextPositions(Position position) {
        return this.getSlot(position).hasNext() ? 
        Collections.unmodifiableMap(this.getSlot(position).getNextConnections()) :
        Collections.EMPTY_MAP;
    }

    @Override
    public Position getStarPosition() throws IllegalStateException{
        Set<Position> star = this.starsPositions
                                 .stream()
                                 .filter(p -> this.getSlotType(p).equals(SlotType.ACTIVE_STAR))
                                 .collect(Collectors.toSet());
        if (star.size() != 1) {
            throw new IllegalStateException();
        } 
        return star.stream().findFirst().get();
    }

    @Override
    public void changeStarPosition() throws IllegalStateException{
        Set<Position> nextStars = this.starsPositions
                                      .stream()
                                      .filter(p -> this.getSlotType(p).equals(SlotType.NOT_ACTIVE_STAR))
                                      .collect(Collectors.toSet());
        if (nextStars.isEmpty()) {
            throw new IllegalStateException();
        }
        Position newStarPosition = RandomFromSet.get(nextStars);
        Position oldStarPosition = this.getStarPosition();
        this.board.get(newStarPosition).changeSlotType(SlotType.ACTIVE_STAR);
        this.board.get(oldStarPosition).changeSlotType(SlotType.NOT_ACTIVE_STAR);
    }

    @Override
    public Pair<Integer, Integer> getDimension() {
        return new Pair<Integer,Integer>(this.width, this.height);
    }

    @Override
    public Map<Position,Slot> getBoard() {
        return Collections.unmodifiableMap(this.board);
    }

    protected void addSlot(Position position, SlotType slotType) {
        if (!this.board.containsKey(position) && isValidPosition(position)){
            this.board.put(position, new SlotImpl(position, slotType));
        }
    }

    protected void addConnection(Position from, Position to, Direction dir) {
        if (this.board.containsKey(from) && this.board.containsKey(to)) {
            this.getSlot(from).addNext(dir, to);
            this.getSlot(to).addPrev(dir, from);
        };
    }

    protected boolean isValidPosition(Position position) {
        return isInTheBoard(position);
    }

    protected boolean isInTheBoard(Position position){
        return  position.getX() >= 0 && position.getX() < this.width &&
                position.getY() >= 0 && position.getY() < this.height;
    }

    protected void createPath(Position from, int steps, Direction currentDir){
        this.addSlot(from, getNewSlotType());
        Position to = this.getNeighbor(from, currentDir);
        for (int i = 0; i < steps; i++) {
            this.addSlot(to, getNewSlotType());
            this.addConnection(from, to, currentDir);
            from = to;
            to = this.getNeighbor(to, currentDir);
        }
    }

    protected void createPathFromFile(String filePath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == N_PARTS_INPUT_FILE){
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    Position pos = new Position(x, y);
                    int steps = Integer.parseInt(parts[2]);
                    String d = parts[3];
                    Direction dir = d.equals(UP) ?
                                    Direction.UP : d.equals(DOWN) ?
                                    Direction.DOWN : d.equals(LEFT) ?
                                    Direction.LEFT : d.equals(RIGHT) ?
                                    Direction.RIGHT : null;
                    
                    if (this.isValidPosition(pos) && Objects.nonNull(dir)) {
                        this.createPath(pos, steps, dir);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Slot getSlot(Position position) {
        return this.board.containsKey(position) ?
               this.board.get(position) : new SlotImpl(position, SlotType.VOID);
    }

    protected Position getNeighbor(Position p, Direction dir) {
        return new Position(p.getX()
                            + (dir.equals(Direction.RIGHT) ? 1 : 0)
                            + (dir.equals(Direction.LEFT) ? -1 : 0),
                            p.getY()
                            + (dir.equals(Direction.UP) ? -1 : 0)
                            + (dir.equals(Direction.DOWN) ? 1 : 0));
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