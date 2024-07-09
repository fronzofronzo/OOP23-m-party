package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.impl.AbstractBoardImpl;
import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

import java.util.Set;

public class HardGameBoard extends AbstractBoardImpl{

    private final static BoardType BOARD_TYPE = BoardType.HARD; 

    private static final int WIDTH = 30;
    private static final int HEIGHT = 20;

    private static final int INITIAL_X_HARD_BOARD = 28;
    private static final int INITIAL_Y_HARD_BOARD = 14;

    private static final int PROB_PATH = 10;
    private static final int PROB_SINGLEPLAYER = 15;
    private static final int PROB_MALUS = 30;
    private static final int PROB_MULTIPLAYER = 20;
    private static final int PROB_SHOP = 15;
    private static final int PROB_BONUS = 10;

    private static final String FILE_PATH = "HardGameBoard.txt";

    private static final Set<Pair<SlotType,Integer>> RULES = 
        Set.of(new Pair<>(SlotType.PATH, PROB_PATH),
               new Pair<>(SlotType.SINGLEPLAYER, PROB_SINGLEPLAYER),
               new Pair<>(SlotType.MALUS, PROB_MALUS),
               new Pair<>(SlotType.MULTIPLAYER, PROB_MULTIPLAYER),
               new Pair<>(SlotType.SHOP, PROB_SHOP),
               new Pair<>(SlotType.BONUS, PROB_BONUS));

    private static final Set<Position> STARS_POSITIONS = Set.of(new Position(19, 12), 
                                                                new Position(12, 1),
                                                                new Position(7, 18));
           
    public HardGameBoard() {
        super(WIDTH, HEIGHT, new Position(INITIAL_X_HARD_BOARD, INITIAL_Y_HARD_BOARD), STARS_POSITIONS, FILE_PATH, RULES, BOARD_TYPE);
    }
}