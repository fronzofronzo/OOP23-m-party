package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.impl.AbstractBoardImpl;
import it.unibo.mparty.model.gameBoard.util.BoardType;
import it.unibo.mparty.model.gameBoard.util.Pair;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.RandomFromSet;
import it.unibo.mparty.model.gameBoard.util.RandomListGenerator;
import it.unibo.mparty.model.gameBoard.util.SlotType;
import java.util.Set;
import java.util.List;
import java.util.Collections;

public class MediumGameBoard extends AbstractBoardImpl{

    private final static BoardType boardType = BoardType.MEDIUM; 

    private static final int WIDTH = 30;
    private static final int HEIGHT = 20;

    private static final int INITIAL_X_MEDIUM_BOARD = 4;
    private static final int INITIAL_Y_MEDIUM_BOARD = 16;

    private static final double PROB_PATH = 0.35;
    private static final double PROB_SINGLEPLAYER = 0.1;
    private static final double PROB_MALUS = 0.15;
    private static final double PROB_MULTIPLAYER = 0.15;
    private static final double PROB_SHOP = 0.1;
    private static final double PROB_BONUS = 0.15;

    private static final String FILE_PATH = "C:\\Users\\81W1019HIX\\OneDrive\\Desktop\\MARIO PARTY\\OOP23-m-party\\src\\main\\java\\it\\unibo\\mparty\\model\\gameBoard\\file\\MediumBoardGame.txt";

    private static final Set<Pair<SlotType,Double>> RULES = 
        Set.of(new Pair<>(SlotType.PATH, PROB_PATH),
               new Pair<>(SlotType.SINGLEPLAYER, PROB_SINGLEPLAYER),
               new Pair<>(SlotType.MALUS, PROB_MALUS),
               new Pair<>(SlotType.MULTIPLAYER, PROB_MULTIPLAYER),
               new Pair<>(SlotType.SHOP, PROB_SHOP),
               new Pair<>(SlotType.BONUS, PROB_BONUS));

    private static final Set<Position> STARS_POSITIONS = Set.of(new Position(5, 1), 
                                                                new Position(28, 9),
                                                                new Position(18, 18));
           
    public MediumGameBoard() {
        super();
    }

    @Override
    public void generateBoard() {
        this.addSlot(RandomFromSet.get(STARS_POSITIONS), SlotType.ACTIVE_STAR);
        STARS_POSITIONS.stream().forEach(p -> this.addSlot(p, SlotType.NOT_ACTIVE_STAR));
        this.addSlot(this.getStrartingPosition(), SlotType.PATH);
        this.createPathFromFile(FILE_PATH);
        }

    @Override
    public BoardType getBoardType() {
        return boardType;
    }

    @Override
    protected Set<Position> setStarsPosition() {
        return Collections.unmodifiableSet(STARS_POSITIONS);
    }

    @Override
    protected int setWidth() {
        return WIDTH;
    }

    @Override
    protected int setHeight() {
        return HEIGHT;
    }

    @Override
    protected Position setInitialPosition() {
        return new Position(INITIAL_X_MEDIUM_BOARD, INITIAL_Y_MEDIUM_BOARD);
    }

    @Override
    protected List<SlotType> setAviableSlotType() {
        return RandomListGenerator.generate(RULES);
    }
}
