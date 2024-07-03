package it.unibo.mparty.gameBoard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.model.gameBoard.boards.SimpleBoardFactory;
import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

import java.util.Set;
import java.util.HashSet;

class TestGameBoard{

    private static final int ACTIVE_STAR_SLOTS_EXPECTED = 1;
    private static final int NOT_ACTIVE_STAR_SLOTS_EXPECTED = 2;
    private static final int INITIAL_X_EASY_BOARD = 7;
    private static final int INITIAL_Y_EASY_BOARD = 18;
    private static final int INITIAL_X_MEDIUM_BOARD = 4;
    private static final int INITIAL_Y_MEDIUM_BOARD = 16;
    private static final int INITIAL_X_HARD_BOARD = 28;
    private static final int INITIAL_Y_HARD_BOARD = 14;

    
    @SuppressWarnings("unchecked")
    private final Map<Position,Map<Direction, Position>> dataSetToTestgetNextPositionsEasyBoard = 
    Map.of(
        new Position(19, 10), Map.of(Direction.UP, new Position(19, 9)),
        new Position(19, 13), Map.of(Direction.UP, new Position(19, 12),Direction.LEFT, new Position(18, 13)),
        new Position(15,3), Collections.EMPTY_MAP
        );
    @SuppressWarnings("unchecked")
    private final Map<Position,Map<Direction, Position>> dataSetToTestgetNextPositionsMediumBoard = 
        Map.of(
            new Position(5, 11), Map.of(Direction.UP, new Position(5, 10)),
            new Position(25, 7), Map.of(Direction.UP, new Position(25, 6),Direction.RIGHT, new Position(26, 7)),
            new Position(15,2), Collections.EMPTY_MAP
            );
    @SuppressWarnings("unchecked")
    private final Map<Position,Map<Direction, Position>> dataSetToTestgetNextPositionsHardBoard = 
        Map.of(
            new Position(1, 13), Map.of(Direction.UP, new Position(1, 12)),
            new Position(3, 6), Map.of(Direction.UP, new Position(3, 5),Direction.DOWN, new Position(3, 7)),
            new Position(26,1), Collections.EMPTY_MAP
            );
    
    private static Set<GameBoard> boards = new HashSet<>();
    private static Set<BoardType> boardTypes = Set.of(BoardType.EASY, BoardType.MEDIUM, BoardType.HARD);

    @BeforeAll
    public static void initialise(){
        SimpleBoardFactory factory = new SimpleBoardFactory();
        for (BoardType bt : boardTypes) {
            boards.add(factory.createBoard(bt));
        }
        for (GameBoard b : boards) {
            System.out.println(b.getBoardType());  
        }
    }

    @Test 
    public void testNumberStarsSlots(){
        for (GameBoard b : boards) {
            int count_active_star = (int)b.getBoard()
                                          .entrySet()
                                          .stream()
                                          .filter(entry -> entry.getValue().getSlotType().equals(SlotType.ACTIVE_STAR))
                                          .count();
            int count_not_active_star = (int)b.getBoard()
                                              .entrySet()
                                              .stream()
                                              .filter(entry -> entry.getValue().getSlotType().equals(SlotType.NOT_ACTIVE_STAR))
                                              .count();
        assertEquals(ACTIVE_STAR_SLOTS_EXPECTED, count_active_star);
        assertEquals(NOT_ACTIVE_STAR_SLOTS_EXPECTED, count_not_active_star);
        }
    }

    @Test
    public void testChangeStarPosition() {
        for (GameBoard b : boards) {
            Position oldStarPosition = b.getStarPosition();
            assertNotNull(oldStarPosition);
            b.changeStarPosition();
            assertNotEquals(oldStarPosition, b.getStarPosition());   
        }
    }

    @Test 
    public void testStartingPosition(){
        for (GameBoard b : boards) {
            BoardType bt = b.getBoardType();
            Position expected = null;
            switch (bt) {
                case EASY: expected = new Position(INITIAL_X_EASY_BOARD, INITIAL_Y_EASY_BOARD); break;
                case MEDIUM: expected = new Position(INITIAL_X_MEDIUM_BOARD, INITIAL_Y_MEDIUM_BOARD); break;
                case HARD: expected = new Position(INITIAL_X_HARD_BOARD, INITIAL_Y_HARD_BOARD); break;
                default: break;
            }
            assertEquals(expected, b.getStrartingPosition());            
        }
    }

    @Test
    public void testGetNextPositions() {
        for (GameBoard b : boards) {
            BoardType bt = b.getBoardType();
            switch (bt) {
                case EASY: this.dataSetToTestgetNextPositionsEasyBoard.entrySet().stream().forEach(entry -> assertEquals(entry.getValue(), b.getNextPositions(entry.getKey()))); break;
                case MEDIUM: this.dataSetToTestgetNextPositionsMediumBoard.entrySet().stream().forEach(entry -> assertEquals(entry.getValue(), b.getNextPositions(entry.getKey()))); break;
                case HARD: this.dataSetToTestgetNextPositionsHardBoard.entrySet().stream().forEach(entry -> assertEquals(entry.getValue(), b.getNextPositions(entry.getKey()))); break;
                default: break;
            }       
        }
        
    }
}