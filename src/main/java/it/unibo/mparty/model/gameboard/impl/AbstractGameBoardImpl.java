package it.unibo.mparty.model.gameboard.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.stream.Collectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import it.unibo.mparty.model.gameboard.api.GameBoard;
import it.unibo.mparty.model.gameboard.api.Slot;
import it.unibo.mparty.model.gameboard.util.RandomListGenerator;
import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.RandomFromSet;
import it.unibo.mparty.utilities.SlotType;

/**
 * This is an abstract class that implements a {@link GameBoard}.
 */
public abstract class AbstractGameBoardImpl implements GameBoard {

    private static final Logger LOGGER = Logger.getLogger(AbstractGameBoardImpl.class.getName());
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
    private final Map<Position, Slot> board;
    private List<SlotType> avaiableSlotTypes;
    private boolean updateStarsSlot;

    /**
     * This is the constructor of this abstract class.
     * 
     * @param width           that is the width of the game board
     * @param height          that is the height of the game board
     * @param initialPosition that is the spawn {@link Position} of the players
     *                        in the game board
     * @param starPositions   that are the positions where the star could spawn
     * @param filePath        that is the path of the configuration file txt of
     *                        the game board
     * @param rules           that contains rules about probabilities of spawn for
     *                        each {@link SlotType} in the board
     * @param boardType       that is the type that identifies this board.
     */
    public AbstractGameBoardImpl(final int width,
            final int height,
            final Position initialPosition,
            final Set<Position> starPositions,
            final String filePath,
            final Map<SlotType, Integer> rules,
            final BoardType boardType) {
        this.width = width;
        this.height = height;
        this.initialPosition = initialPosition;
        this.starsPositions = new HashSet<>(starPositions);
        this.filePath = filePath;
        this.rules = new HashMap<>(rules);
        this.boardType = boardType;
        this.board = new HashMap<>();
        this.avaiableSlotTypes = getAviableSlotType();
        this.updateStarsSlot = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialiseBoard() {
        this.generateBoard();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeStarPosition() {
        final Set<Position> nextStars = this.starsPositions
                .stream()
                .filter(p -> this.getSlotType(p).equals(SlotType.NOT_ACTIVE_STAR))
                .collect(Collectors.toSet());
        if (nextStars.size() != this.starsPositions.size() - 1) {
            throw new IllegalStateException();
        }
        final Position newStarPosition = RandomFromSet.get(nextStars);
        final Position oldStarPosition = this.getStarPosition();
        this.board.get(newStarPosition).changeSlotType(SlotType.ACTIVE_STAR);
        this.board.get(oldStarPosition).changeSlotType(SlotType.NOT_ACTIVE_STAR);
        this.updateStarsSlot = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SlotType getSlotType(final Position position) {
        if (!isInTheBoard(position)) {
            throw new IllegalStateException();
        }
        return this.board.containsKey(position)
                ? this.board.get(position).getSlotType()
                : SlotType.VOID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getStrartingPosition() {
        return this.initialPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Direction, Position> getNextPositions(final Position position) {
        return Collections.unmodifiableMap(this.getSlot(position).getNextConnections());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getStarPosition() {
        final Set<Position> star = this.starsPositions
                .stream()
                .filter(p -> this.getSlotType(p).equals(SlotType.ACTIVE_STAR))
                .collect(Collectors.toSet());
        if (star.size() != 1) {
            throw new IllegalStateException();
        }
        return star.stream().findFirst().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getDimension() {
        return new Pair<Integer, Integer>(this.width, this.height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Position, Slot> getBoard() {
        return Collections.unmodifiableMap(this.board);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Position, SlotType> getSlotTypeBoardConfiguration() {
        final Map<Position, SlotType> output = new HashMap<>();
        for (final Entry<Position, Slot> entry : this.board.entrySet()) {
            output.put(entry.getKey(), entry.getValue().getSlotType());
        }
        return Collections.unmodifiableMap(output);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoardType getBoardType() {
        return this.boardType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Position, SlotType> getModifiedSlots() {
        if (this.updateStarsSlot) {
            final Map<Position, SlotType> slotsToUpdate = new HashMap<>();
            this.starsPositions.forEach(p -> slotsToUpdate.put(p, this.getSlotType(p)));
            this.updateStarsSlot = false;
            return Collections.unmodifiableMap(slotsToUpdate);
        }
        return Collections.emptyMap();
    }

    /**
     * Get the slot that correspond a the position.
     * 
     * @param position the position requested.
     * @return the {@link Slot} of the position.
     */
    protected Slot getSlot(final Position position) {
        return this.board.containsKey(position)
                ? this.board.get(position)
                : new SlotImpl(position, SlotType.VOID);
    }

    /**
     * Compute the neighbor {@link Position} towards a {@link Direction}.
     * 
     * @param from the starting {@link Position}.
     * @param dir  the {@link Direction}.
     * @return the computed {@link Position}.
     */
    protected Position getNeighbor(final Position from, final Direction dir) {
        return new Position(from.getX()
                + (dir.equals(Direction.RIGHT) ? 1 : 0)
                + (dir.equals(Direction.LEFT) ? -1 : 0),
                from.getY()
                        + (dir.equals(Direction.UP) ? -1 : 0)
                        + (dir.equals(Direction.DOWN) ? 1 : 0));
    }

    /**
     * Check if is possible to connect two positions.
     * 
     * @param from the starting {@link Position}.
     * @param to   the destination.
     * @return true if the connection is accepted, otherwise false.
     */
    protected boolean isValidConnection(final Position from, final Position to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY()) == 1;
    }

    private void generateBoard() {
        this.addSlot(RandomFromSet.get(this.starsPositions), SlotType.ACTIVE_STAR);
        this.starsPositions
                .forEach(p -> this.addSlot(p, SlotType.NOT_ACTIVE_STAR));
        this.addSlot(this.initialPosition, SlotType.PATH);
        this.createPathFromFile();
    }

    private void addSlot(final Position position, final SlotType slotType) {
        if (!this.board.containsKey(position) && isInTheBoard(position)) {
            this.board.put(position, new SlotImpl(position, slotType));
        }
    }

    private void addConnection(final Position from, final Position to, final Direction dir) {
        if (this.board.containsKey(from) && this.board.containsKey(to) && this.isValidConnection(from, to)) {
            this.getSlot(from).addNext(dir, to);
            this.getSlot(to).addPrev(dir, from);
        }
    }

    private boolean isInTheBoard(final Position position) {
        return position.getX() >= 0 && position.getX() < this.width
                && position.getY() >= 0 && position.getY() < this.height;
    }

    private void createPath(final Position from, final int steps, final Direction currentDir) {
        this.addSlot(from, getNewSlotType());
        final Position to = this.getNeighbor(from, currentDir);
        if (steps > 0) {
            this.addSlot(to, getNewSlotType());
            this.addConnection(from, to, currentDir);
            this.createPath(to, steps - 1, currentDir);
        }
    }

    private void createPathFromFile() {
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream(this.filePath);
        if (Objects.isNull(inputStream)) {
            throw new IllegalStateException();
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            String line = br.readLine();
            while (line != null) {
                final String[] parts = line.split(" ");
                if (parts.length == N_PARTS_INPUT_FILE) {
                    final int x = Integer.parseInt(parts[0]);
                    final int y = Integer.parseInt(parts[1]);
                    final Position pos = new Position(x, y);
                    final int steps = Integer.parseInt(parts[2]);
                    final String d = parts[3];
                    final Direction dir = this.getDirection(d);
                    this.createPath(pos, steps, dir);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "ERROR: ", e);
        }
    }

    private Direction getDirection(final String d) {
        switch (d) {
            case UP:
                return Direction.UP;
            case DOWN:
                return Direction.DOWN;
            case LEFT:
                return Direction.LEFT;
            case RIGHT:
                return Direction.RIGHT;
            default:
                throw new IllegalStateException();
        }
    }

    private SlotType getNewSlotType() {
        if (this.avaiableSlotTypes.isEmpty()) {
            this.avaiableSlotTypes = getAviableSlotType();
        }
        final SlotType output = this.avaiableSlotTypes.get(0);
        this.avaiableSlotTypes.remove(0);
        return output;
    }

    private List<SlotType> getAviableSlotType() {
        return RandomListGenerator.generate(this.rules);
    }
}