package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.impl.AbstractBoardImpl;
import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

import java.util.Set;

public class EasyGameBoard extends AbstractBoardImpl{

    private final static BoardType BOARD_TYPE = BoardType.EASY; 

    private static final int WIDTH = 30;
    private static final int HEIGHT = 20;

    private static final int INITIAL_X_EASY_BOARD = 7;
    private static final int INITIAL_Y_EASY_BOARD = 18;

    private static final double PROB_PATH = 0.5;
    private static final double PROB_SINGLEPLAYER = 0.1;
    private static final double PROB_MALUS = 0.05;
    private static final double PROB_MULTIPLAYER = 0.1;
    private static final double PROB_SHOP = 0.1;
    private static final double PROB_BONUS = 0.15;

    private static final String FILE_PATH = "EasyBoardGame.txt";

    private static final Set<Pair<SlotType,Double>> RULES = 
        Set.of(new Pair<>(SlotType.PATH, PROB_PATH),
               new Pair<>(SlotType.SINGLEPLAYER, PROB_SINGLEPLAYER),
               new Pair<>(SlotType.MALUS, PROB_MALUS),
               new Pair<>(SlotType.MULTIPLAYER, PROB_MULTIPLAYER),
               new Pair<>(SlotType.SHOP, PROB_SHOP),
               new Pair<>(SlotType.BONUS, PROB_BONUS));

    private static final Set<Position> STARS_POSITIONS = Set.of(new Position(5, 7), 
                                                                new Position(22, 1),
                                                                new Position(25, 18));
           
    public EasyGameBoard() {
        super(WIDTH, HEIGHT, new Position(INITIAL_X_EASY_BOARD, INITIAL_Y_EASY_BOARD), STARS_POSITIONS, FILE_PATH, RULES, BOARD_TYPE);
    }
}
