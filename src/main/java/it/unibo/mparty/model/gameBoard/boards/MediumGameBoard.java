package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.impl.AbstractBoardImpl;
import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

import java.util.Map;
import java.util.Set;

public class MediumGameBoard extends AbstractBoardImpl{

    private final static BoardType BOARD_TYPE = BoardType.MEDIUM; 

    private static final int WIDTH = 30;
    private static final int HEIGHT = 20;

    private static final int INITIAL_X_MEDIUM_BOARD = 4;
    private static final int INITIAL_Y_MEDIUM_BOARD = 16;

    private static final int PROB_PATH = 35;
    private static final int PROB_SINGLEPLAYER = 10;
    private static final int PROB_MALUS = 15;
    private static final int PROB_MULTIPLAYER = 15;
    private static final int PROB_SHOP = 5;
    private static final int PROB_BONUS = 20;

    private static final String FILE_PATH = "MediumBoardGame.txt";

    private static final Map<SlotType,Integer> RULES = 
        Map.of(SlotType.PATH, PROB_PATH,
               SlotType.SINGLEPLAYER, PROB_SINGLEPLAYER,
               SlotType.MALUS, PROB_MALUS,
               SlotType.MULTIPLAYER, PROB_MULTIPLAYER,
               SlotType.SHOP, PROB_SHOP,
               SlotType.BONUS, PROB_BONUS);

    private static final Set<Position> STARS_POSITIONS = Set.of(new Position(5, 1), 
                                                                new Position(28, 9),
                                                                new Position(18, 18));
           
    public MediumGameBoard() {
        super(WIDTH, HEIGHT, new Position(INITIAL_X_MEDIUM_BOARD, INITIAL_Y_MEDIUM_BOARD), STARS_POSITIONS, FILE_PATH, RULES, BOARD_TYPE);
    }
}