package it.unibo.mparty.model.gameBoard.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.RandomFromSet;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.model.gameBoard.util.RandomListGenerator;
import it.unibo.mparty.model.gameBoard.api.Slot;

/**
 * This is an Abstract class that implements a GameBoard
 */
public abstract class AbstractBoardImpl implements GameBoard {

    private static final int N_PARTS_INPUT_FILE = 4;
    private static final String UP = "U";
    private static final String DOWN = "D";
    private static final String RIGHT = "R";
    private static final String LEFT = "L";

    private final int width;
    private final int height;
    private final Position initialPosition;
    private final Map<SlotType, Integer> rules;
    private final BoardType boardType;
    private final String filePath;
    private final Set<Position> starsPositions;
    private Map<Position, Slot> board = new HashMap<>();
    private List<SlotType> avaiableSlotTypes;
    private boolean updateStarsSlot = false;

    /**
     * This is the constructor of this abstract class
     * @param width that is the width of the game board
     * @param height that is the height of the game board
     * @param initialPosition that is the spawn {@link Position} of the players
     * in the game board
     * @param starPositions that are the positions where the star could spawn
     * @param filePath that is the path of the configuration file txt of
     * the game board
     * @param rules that contains rules about probabilities of spawn for
     * each {@link SlotType} in the board
     * @param boardType that is the type that identifies this board.
     */
    public AbstractBoardImpl(int width,
                             int height,
                             Position initialPosition,
                             Set<Position> starPositions,
                             String filePath,
                             Map<SlotType, Integer> rules,
                             BoardType boardType) {
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
     * {@inheritDoc}
     */
    @Override
    public SlotType getSlotType(Position position) throws IllegalStateException{
        if (!isInBounds(position)) {
            throw new IllegalStateException();
        }
        return this.board.containsKey(position) ? this.board.get(position).getSlotType() : SlotType.VOID;
    }

    private boolean isInBounds(Position pos) {
        return pos.getX() >= 0
               && pos.getY() >= 0
               && pos.getX() < this.width
               && pos.getY() < this.height;
    }

    private void generateBoard() {
        this.addSlot(RandomFromSet.get(this.starsPositions), SlotType.ACTIVE_STAR);
        this.starsPositions
                .forEach(p -> this.addSlot(p, SlotType.NOT_ACTIVE_STAR));
        this.addSlot(this.getStrartingPosition(), SlotType.PATH);
        this.createPathFromFile(this.filePath);
    }

    @Override
    public Position getStrartingPosition() {
        return this.initialPosition;
    }

    @Override
    public Map<Direction, Position> getNextPositions(Position position) {
        return this.getSlot(position).hasNext()
                ? Collections.unmodifiableMap(this.getSlot(position).getNextConnections())
                : Collections.emptyMap();
    }

    @Override
    public Position getStarPosition() throws IllegalStateException {
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
    public void changeStarPosition() throws IllegalStateException {
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
        this.updateStarsSlot = true;
    }

    @Override
    public Pair<Integer, Integer> getDimension() {
        return new Pair<Integer, Integer>(this.width, this.height);
    }

    @Override
    public Map<Position, Slot> getBoard() {
        return Collections.unmodifiableMap(this.board);
    }

    @Override
    public Map<Position, SlotType> getSlotTypeBoardConfiguration() {
        Map<Position, SlotType> output = new HashMap<>();
        for (Entry<Position, Slot> entry : this.board.entrySet()) {
            output.put(entry.getKey(), entry.getValue().getSlotType());
        }
        return Collections.unmodifiableMap(output);
    }

    @Override
    public Map<Position, SlotType> getSlotsToUpdate() {
        if (this.updateStarsSlot) {
            Map<Position, SlotType> slotsToUpdate = new HashMap<>();
            this.starsPositions.forEach(p -> slotsToUpdate.put(p, this.getSlotType(p)));
            this.updateStarsSlot = false;
            return Collections.unmodifiableMap(slotsToUpdate);
        }
        return Collections.emptyMap();
    }

    private void addSlot(Position position, SlotType slotType) {
        if (!this.board.containsKey(position) && isValidPosition(position)) {
            this.board.put(position, new SlotImpl(position, slotType));
        }
    }

    private void addConnection(Position from, Position to, Direction dir) {
        if (this.board.containsKey(from) && this.board.containsKey(to)) {
            this.getSlot(from).addNext(dir, to);
            this.getSlot(to).addPrev(dir, from);
        }
        ;
    }
    protected boolean isValidPosition(Position position) {
        return isInTheBoard(position);
    }

    /**
     * Check if the position is in board limits
     * 
     * @param position the position to check
     * @return true if is in board limits, otherwise false
     */
    protected boolean isInTheBoard(Position position) {
        return position.getX() >= 0 && position.getX() < this.width &&
                position.getY() >= 0 && position.getY() < this.height;
    }

    private void createPath(Position from, int steps, Direction currentDir) {
        this.addSlot(from, getNewSlotType());
        Position to = this.getNeighbor(from, currentDir);
        for (int i = 0; i < steps; i++) {
            this.addSlot(to, getNewSlotType());
            this.addConnection(from, to, currentDir);
            from = to;
            to = this.getNeighbor(to, currentDir);
        }
    }

    private void createPathFromFile(String filePath) {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(this.filePath);
        if (Objects.isNull(inputStream)) {
            throw new IllegalStateException();
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == N_PARTS_INPUT_FILE) {
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    Position pos = new Position(x, y);
                    int steps = Integer.parseInt(parts[2]);
                    String d = parts[3];
                    Direction dir = d.equals(UP) ? Direction.UP
                            : d.equals(DOWN) ? Direction.DOWN
                                    : d.equals(LEFT) ? Direction.LEFT : d.equals(RIGHT) ? Direction.RIGHT : null;

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
     * 
     * @param position the position requested
     * @return the {@link Slot} of the position
     */
    protected Slot getSlot(Position position) {
        return this.board.containsKey(position) ? this.board.get(position) : new SlotImpl(position, SlotType.VOID);
    }

    /**
     * Get the next Slot from a position towards a direction
     * 
     * @param from the starting position
     * @param dir  the direction
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

    private SlotType getNewSlotType() {
        if (this.avaiableSlotTypes.isEmpty()) {
            this.avaiableSlotTypes = setAviableSlotType();
        }
        SlotType output = this.avaiableSlotTypes.get(0);
        this.avaiableSlotTypes.remove(0);
        return output;
    }
    
    private List<SlotType> setAviableSlotType() {
        return RandomListGenerator.generate(this.rules);
    }

    /**
     * Get the Board Type
     * 
     * @return the board type of the board
     */
    public BoardType getBoardType() {
        return this.boardType;
    }
}
