package it.unibo.mparty.model.gameBoard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;
import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.model.gameBoard.boards.BoardFactory;
import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

import java.util.Set;
import java.util.HashSet;

/**
 * This is a class that test a {@link GameBoard}.
 */
class TestGameBoard {

    private static final int ACTIVE_STAR_SLOTS_EXPECTED = 1;
    private static final int NOT_ACTIVE_STAR_SLOTS_EXPECTED = 2;
    private static final int INITIAL_X_EASY_BOARD = 7;
    private static final int INITIAL_Y_EASY_BOARD = 18;
    private static final int INITIAL_X_MEDIUM_BOARD = 4;
    private static final int INITIAL_Y_MEDIUM_BOARD = 16;
    private static final int INITIAL_X_HARD_BOARD = 28;
    private static final int INITIAL_Y_HARD_BOARD = 14;
    private static final Map<Position, Map<Direction, Position>> DATA_SET_TO_TEST_GET_NEXT_POSITIONS_EASY_BOARD = Map
            .of(
                    new Position(19, 10), Map.of(Direction.UP, new Position(19, 9)),
                    new Position(19, 13),
                    Map.of(Direction.UP, new Position(19, 12), Direction.LEFT, new Position(18, 13)),
                    new Position(15, 3), Collections.emptyMap());
    private static final Map<Position, Map<Direction, Position>> DATA_SET_TO_TEST_GET_NEXT_POSITIONS_MEDIUM_BOARD = Map
            .of(
                    new Position(5, 11), Map.of(Direction.UP, new Position(5, 10)),
                    new Position(25, 7),
                    Map.of(Direction.UP, new Position(25, 6), Direction.RIGHT, new Position(26, 7)),
                    new Position(15, 2), Collections.emptyMap());
    private static final Map<Position, Map<Direction, Position>> DATA_SET_TO_TEST_GET_NEXT_POSITIONS_HARD_BOARD = Map
            .of(
                    new Position(1, 13), Map.of(Direction.UP, new Position(1, 12)),
                    new Position(3, 6), Map.of(Direction.UP, new Position(3, 5), Direction.DOWN, new Position(3, 7)),
                    new Position(26, 1), Collections.emptyMap());
    private static final Set<GameBoard> BOARDS = new HashSet<>();
    private static final Set<BoardType> BOARDS_TYPES = Set.of(BoardType.EASY, BoardType.MEDIUM, BoardType.HARD);

    /**
     * This class initialise the game boards to test.
     */
    @BeforeAll
    public static void initialise() {
        for (final BoardType bt : BOARDS_TYPES) {
            BOARDS.add(BoardFactory.createBoard(bt));
        }
    }

    /**
     * This class test that each {@link GameBoard} has the right number of
     * {@link SlotType.ACTIVE_STAR} and {@link SlotType.NOT_ACTIVE_STAR}.
     */
    @Test
    public void testNumberStarsSlots() {
        for (GameBoard b : BOARDS) {
            int countActiveStar = (int) b.getBoard()
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().getSlotType().equals(SlotType.ACTIVE_STAR))
                    .count();
            int countNotActiveStar = (int) b.getBoard()
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().getSlotType().equals(SlotType.NOT_ACTIVE_STAR))
                    .count();
            assertEquals(ACTIVE_STAR_SLOTS_EXPECTED, countActiveStar);
            assertEquals(NOT_ACTIVE_STAR_SLOTS_EXPECTED, countNotActiveStar);
        }
    }

    /**
     * This class tests for each {@link GameBoard} the method changeStarPosition.
     */
    @Test
    public void testChangeStarPosition() {
        for (GameBoard b : BOARDS) {
            Position oldStarPosition = b.getStarPosition();
            assertNotNull(oldStarPosition);
            b.changeStarPosition();
            assertNotEquals(oldStarPosition, b.getStarPosition());
        }
    }

    /**
     * This class tests for each {@link GameBoard} the method getStrartingPosition.
     */
    @Test
    public void testStartingPosition() {
        for (GameBoard b : BOARDS) {
            BoardType bt = b.getBoardType();
            Position expected = null;
            switch (bt) {
                case EASY:
                    expected = new Position(INITIAL_X_EASY_BOARD, INITIAL_Y_EASY_BOARD);
                    break;
                case MEDIUM:
                    expected = new Position(INITIAL_X_MEDIUM_BOARD, INITIAL_Y_MEDIUM_BOARD);
                    break;
                case HARD:
                    expected = new Position(INITIAL_X_HARD_BOARD, INITIAL_Y_HARD_BOARD);
                    break;
                default:
                    break;
            }
            assertEquals(expected, b.getStrartingPosition());
        }
    }

    /**
     * This class tests for each {@link GameBoard} the method getNextPositions.
     */
    @Test
    public void testGetNextPositions() {
        for (GameBoard b : BOARDS) {
            BoardType bt = b.getBoardType();
            switch (bt) {
                case EASY:
                    DATA_SET_TO_TEST_GET_NEXT_POSITIONS_EASY_BOARD.entrySet()
                            .forEach(entry -> assertEquals(entry.getValue(), b.getNextPositions(entry.getKey())));
                    break;
                case MEDIUM:
                    DATA_SET_TO_TEST_GET_NEXT_POSITIONS_MEDIUM_BOARD.entrySet()
                            .forEach(entry -> assertEquals(entry.getValue(), b.getNextPositions(entry.getKey())));
                    break;
                case HARD:
                    DATA_SET_TO_TEST_GET_NEXT_POSITIONS_HARD_BOARD.entrySet()
                            .forEach(entry -> assertEquals(entry.getValue(), b.getNextPositions(entry.getKey())));
                    break;
                default:
                    break;
            }
        }
    }
}
