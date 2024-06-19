package it.unibo.samplejavafx.gameBoard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

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
    private static final int INITIAL_X_EASY_BOARD = 5;
    private static final int INITIAL_Y_EASY_BOARD = 25;

    
    private final Map<Position,Map<Direction, Position>> dataSetToTestgetNextPositions = 
    Map.of(
        new Position(5, 6), Map.of(Direction.RIGHT, new Position(6, 6)),
        new Position(16, 10), Map.of(Direction.RIGHT, new Position(17, 10),Direction.DOWN, new Position(16, 11))
        );
    
    private static Board myBoard;

    @BeforeAll
    public static void initialise(){
        SimpleBoardFactory factory = new SimpleBoardFactory();
        myBoard = factory.createBoard(BoardType.EASY);
    }

    @Test 
    public void testNumberStarsSlots(){
        int count_active_star = (int)myBoard.getBoard()
                                                 .entrySet()
                                                 .stream()
                                                 .filter(entry -> entry.getValue().getSlotType().equals(SlotType.ACTIVE_STAR))
                                                 .count();
        int count_not_active_star = (int)myBoard.getBoard()
                                                     .entrySet()
                                                     .stream()
                                                     .filter(entry -> entry.getValue().getSlotType().equals(SlotType.NOT_ACTIVE_STAR))
                                                     .count();
        assertEquals(ACTIVE_STAR_SLOTS_EXPECTED, count_active_star);
        assertEquals(NOT_ACTIVE_STAR_SLOTS_EXPECTED, count_not_active_star);
    }

    @Test
    public void testChangeStarPosition() {
        Position oldStarPosition = myBoard.getStarPosition();
        assertNotNull(oldStarPosition);
        myBoard.changeStarPosition();
        assertNotEquals(oldStarPosition, myBoard.getStarPosition());
    }

    @Test 
    public void testStartingPosition(){
        Position expected = null;
        expected = new Position(INITIAL_X_EASY_BOARD, INITIAL_Y_EASY_BOARD);
        assertEquals(expected, myBoard.getStrartingPosition());
        //assertTrue(myBoard.getSlot(expected).hasNext());
        //assertFalse(myBoard.getSlot(expected).hasPrev());
        //Map<Direction, Position>  getNextPositions = myBoard.getSlot(expected).getNextConnections();
        //assertEquals(1, getNextPositions.size());
        //getNextPositions.entrySet().stream().forEach(entry -> assertNotEquals(myBoard.getStrartingPosition(), entry.getValue()));
    }

    @Test
    public void testGetNextPositions() {
        this.dataSetToTestgetNextPositions.entrySet().stream().forEach(entry -> assertEquals(entry.getValue(), myBoard.getNextPositions(entry.getKey())));
        //System.out.println(myBoard.getNextPositions(new Position(5, 6)));
    }
}