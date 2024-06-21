package it.unibo.samplejavafx.gameBoard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.gameBoard.api.Board;
import it.unibo.mparty.model.gameBoard.boards.SimpleBoardFactory;
import it.unibo.mparty.model.gameBoard.util.BoardType;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.SlotType;

class TestGameBoard{

    private static final int ACTIVE_STAR_SLOTS_EXPECTED = 1;
    private static final int NOT_ACTIVE_STAR_SLOTS_EXPECTED = 2;
    private static final int INITIAL_X_EASY_BOARD = 7;
    private static final int INITIAL_Y_EASY_BOARD = 18;
    private static final int INITIAL_X_MEDIUM_BOARD = 4;
    private static final int INITIAL_Y_MEDIUM_BOARD = 16;

    
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
    
    private static Board myEasyBoard;
    private static Board myMediumBoard;

    @BeforeAll
    public static void initialise(){
        //EASY
        SimpleBoardFactory factory = new SimpleBoardFactory();
        myEasyBoard = factory.createBoard(BoardType.EASY);
        myEasyBoard.printBoard();
        //MEDIUM
        myMediumBoard = factory.createBoard(BoardType.MEDIUM);
        myMediumBoard.printBoard();
    }

    @Test 
    public void testNumberStarsSlots(){
        //EASY
        int count_active_star = (int)myEasyBoard.getBoard()
                                                 .entrySet()
                                                 .stream()
                                                 .filter(entry -> entry.getValue().getSlotType().equals(SlotType.ACTIVE_STAR))
                                                 .count();
        int count_not_active_star = (int)myEasyBoard.getBoard()
                                                     .entrySet()
                                                     .stream()
                                                     .filter(entry -> entry.getValue().getSlotType().equals(SlotType.NOT_ACTIVE_STAR))
                                                     .count();
        assertEquals(ACTIVE_STAR_SLOTS_EXPECTED, count_active_star);
        assertEquals(NOT_ACTIVE_STAR_SLOTS_EXPECTED, count_not_active_star);
        //MEDIUM
        count_active_star = (int)myMediumBoard.getBoard()
                                                 .entrySet()
                                                 .stream()
                                                 .filter(entry -> entry.getValue().getSlotType().equals(SlotType.ACTIVE_STAR))
                                                 .count();
        count_not_active_star = (int)myMediumBoard.getBoard()
                                                     .entrySet()
                                                     .stream()
                                                     .filter(entry -> entry.getValue().getSlotType().equals(SlotType.NOT_ACTIVE_STAR))
                                                     .count();
        assertEquals(ACTIVE_STAR_SLOTS_EXPECTED, count_active_star);
        assertEquals(NOT_ACTIVE_STAR_SLOTS_EXPECTED, count_not_active_star);
    }

    @Test
    public void testChangeStarPosition() {
        //EASY
        Position oldStarPosition = myEasyBoard.getStarPosition();
        assertNotNull(oldStarPosition);
        myEasyBoard.changeStarPosition();
        assertNotEquals(oldStarPosition, myEasyBoard.getStarPosition());
        //MEDIUM
        oldStarPosition = myMediumBoard.getStarPosition();
        assertNotNull(oldStarPosition);
        myMediumBoard.changeStarPosition();
        assertNotEquals(oldStarPosition, myMediumBoard.getStarPosition());
    }

    @Test 
    public void testStartingPosition(){
        //EASY
        Position expected = new Position(INITIAL_X_EASY_BOARD, INITIAL_Y_EASY_BOARD);
        assertEquals(expected, myEasyBoard.getStrartingPosition());
        //MEDIUM
        expected = new Position(INITIAL_X_MEDIUM_BOARD, INITIAL_Y_MEDIUM_BOARD);
        assertEquals(expected, myMediumBoard.getStrartingPosition());
    }

    @Test
    public void testGetNextPositions() {
        this.dataSetToTestgetNextPositionsEasyBoard.entrySet().stream().forEach(entry -> assertEquals(entry.getValue(), myEasyBoard.getNextPositions(entry.getKey())));
        this.dataSetToTestgetNextPositionsMediumBoard.entrySet().stream().forEach(entry -> assertEquals(entry.getValue(), myMediumBoard.getNextPositions(entry.getKey())));
    }
}