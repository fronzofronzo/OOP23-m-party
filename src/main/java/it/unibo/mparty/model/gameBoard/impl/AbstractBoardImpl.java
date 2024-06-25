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

/**
 * This is an Abstract class that implements GameBoard that could be considerated as a
 * directed graph that contains his nodes (slots)
 */
public abstract class AbstractBoardImpl implements GameBoard{

    private static final int N_PARTS_INPUT_FILE = 4;
    private static final String UP = "U";
    private static final String DOWN = "D";
    private static final String RIGHT = "R";
    private static final String LEFT = "L";

    private final int width;
    private final int height;
    private final Position initialPosition;
    private final Set<Pair<SlotType,Double>> rules;
    private final BoardType boardType; 
    private final String filePath;
    private final Set<Position> starsPositions;
    private Map<Position,Slot> board = new HashMap<>();
    private List<SlotType> avaiableSlotTypes;

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
    
    /**
     * Generate tha actual GameBoard
     */
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

    /**
     * If the slot is not in the board and the position is accepted,
     * then create and add the slot to the board
     * @param position position of the new slot
     * @param slotType type of the new slot
     */
    protected void addSlot(Position position, SlotType slotType) {
        if (!this.board.containsKey(position) && isValidPosition(position)){
            this.board.put(position, new SlotImpl(position, slotType));
        }
    }

    /**
     * If both the position are in the board, then create a directed connection (edge)
     * @param from the position where starts the connection
     * @param to the position where ends the connection
     * @param dir the direction of the connection
     */
    protected void addConnection(Position from, Position to, Direction dir) {
        if (this.board.containsKey(from) && this.board.containsKey(to)) {
            this.getSlot(from).addNext(dir, to);
            this.getSlot(to).addPrev(dir, from);
        };
    }

    /**
     * Check if the position is valid for the board
     * @param position the position to check
     * @return true if is acceptable, otherwise false
     */
    protected boolean isValidPosition(Position position) {
        return isInTheBoard(position);
    }

    /**
     * Check if the position is in board limits
     * @param position the position to check
     * @return true if is in board limits, otherwise false
     */
    protected boolean isInTheBoard(Position position){
        return  position.getX() >= 0 && position.getX() < this.width &&
                position.getY() >= 0 && position.getY() < this.height;
    }

    /**
     * Create recursively connections from a position towards a direction
     * @param from the starting position
     * @param steps the number of recursions
     * @param currentDir the direction towards create the connections
     */
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

    /**
     * Read from a file the data to call recursively {@link createPath}
     * @param filePath file path of the file to read
     */
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
                    
                    if (Objects.nonNull(dir)) {
                        this.createPath(pos, steps, dir);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the slot that correspond a the position
     * @param position the position requested
     * @return the {@link Slot} of the position
     */
    protected Slot getSlot(Position position) {
        return this.board.containsKey(position) ?
               this.board.get(position) : new SlotImpl(position, SlotType.VOID);
    }

    /**
     * Get the next Slot from a position towards a direction
     * @param from the starting position
     * @param dir the direction
     * @return
     */
    protected Position getNeighbor(Position from, Direction dir) {
        return new Position(from.getX()
                            + (dir.equals(Direction.RIGHT) ? 1 : 0)
                            + (dir.equals(Direction.LEFT) ? -1 : 0),
                            from.getY()
                            + (dir.equals(Direction.UP) ? -1 : 0)
                            + (dir.equals(Direction.DOWN) ? 1 : 0));
    }

    /**
     * Get randomly a new SlotType
     * @return
     */
    protected SlotType getNewSlotType(){
        if (this.avaiableSlotTypes.isEmpty()) {
            this.avaiableSlotTypes = setAviableSlotType();
        }
        SlotType output = this.avaiableSlotTypes.getFirst();
        this.avaiableSlotTypes.removeFirst();
        return output;
    }
    
    /**
     * Create a list that contains in a random order based on the board's rules'
     * @return the random ordered list
     */
    protected List<SlotType> setAviableSlotType() {
        return RandomListGenerator.generate(this.rules);
    }
    
    /**
     * Get the Board Type
     * @return the board type of the board
     */
    public BoardType getBoardType() {
        return this.boardType;
    }
}