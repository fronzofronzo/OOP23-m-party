package it.unibo.mparty.model.gameboard.boards;

import it.unibo.mparty.model.gameboard.impl.AbstractGameBoardImpl;
import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

import java.util.Map;
import java.util.Set;

/**
 * The class EasyGameBoard models a {@link GameBoard} that correspond to the
 * {@link BoardType.EASY}.
 */
public class EasyGameBoard extends AbstractGameBoardImpl {

    private static final BoardType BOARD_TYPE = BoardType.EASY;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 20;
    private static final int INITIAL_X_EASY_BOARD = 7;
    private static final int INITIAL_Y_EASY_BOARD = 18;
    private static final int PROB_PATH = 15;
    private static final int PROB_SINGLEPLAYER = 15;
    private static final int PROB_MALUS = 10;
    private static final int PROB_MULTIPLAYER = 15;
    private static final int PROB_SHOP = 20;
    private static final int PROB_BONUS = 25;
    private static final String FILE_PATH = "EasyBoardGame.txt";
    private static final Map<SlotType, Integer> RULES = Map.of(SlotType.PATH, PROB_PATH,
            SlotType.SINGLEPLAYER, PROB_SINGLEPLAYER,
            SlotType.MALUS, PROB_MALUS,
            SlotType.MULTIPLAYER, PROB_MULTIPLAYER,
            SlotType.SHOP, PROB_SHOP,
            SlotType.BONUS, PROB_BONUS);
    private static final Set<Position> STARS_POSITIONS = Set.of(new Position(5, 7),
            new Position(22, 1),
            new Position(25, 18));

    /**
     * This is the constructor of the EasyGameBoard.
     */
    public EasyGameBoard() {
        super(WIDTH,
                HEIGHT,
                new Position(INITIAL_X_EASY_BOARD, INITIAL_Y_EASY_BOARD),
                STARS_POSITIONS,
                FILE_PATH,
                RULES,
                BOARD_TYPE);
    }
}
