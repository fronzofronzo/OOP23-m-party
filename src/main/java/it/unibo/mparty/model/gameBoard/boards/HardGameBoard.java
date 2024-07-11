package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.model.gameBoard.impl.AbstractGameBoardImpl;
import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

import java.util.Map;
import java.util.Set;

/**
 * The class EasyGameBoard models a {@link GameBoard} that correspond to the
 * {@link BoardType.HARD}.
 */
public class HardGameBoard extends AbstractGameBoardImpl {

    private final static BoardType BOARD_TYPE = BoardType.HARD;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 20;
    private static final int INITIAL_X_HARD_BOARD = 28;
    private static final int INITIAL_Y_HARD_BOARD = 14;
    private static final int PROB_PATH = 15;
    private static final int PROB_SINGLEPLAYER = 20;
    private static final int PROB_MALUS = 20;
    private static final int PROB_MULTIPLAYER = 20;
    private static final int PROB_SHOP = 10;
    private static final int PROB_BONUS = 15;
    private static final String FILE_PATH = "HardGameBoard.txt";
    private static final Map<SlotType, Integer> RULES = Map.of(SlotType.PATH, PROB_PATH,
            SlotType.SINGLEPLAYER, PROB_SINGLEPLAYER,
            SlotType.MALUS, PROB_MALUS,
            SlotType.MULTIPLAYER, PROB_MULTIPLAYER,
            SlotType.SHOP, PROB_SHOP,
            SlotType.BONUS, PROB_BONUS);
    private static final Set<Position> STARS_POSITIONS = Set.of(new Position(19, 12),
            new Position(12, 1),
            new Position(7, 18));

    /**
     * This is the constructor of the HardGameBoard.
     */
    public HardGameBoard() {
        super(WIDTH,
                HEIGHT,
                new Position(INITIAL_X_HARD_BOARD, INITIAL_Y_HARD_BOARD),
                STARS_POSITIONS,
                FILE_PATH,
                RULES,
                BOARD_TYPE);
    }
}
