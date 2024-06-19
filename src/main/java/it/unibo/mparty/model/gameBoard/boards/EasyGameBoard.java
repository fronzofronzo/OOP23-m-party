package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.impl.AbstractBoardImpl;
import it.unibo.mparty.model.gameBoard.util.BoardType;
import it.unibo.mparty.model.gameBoard.util.Pair;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.RandomListGenerator;
import it.unibo.mparty.model.gameBoard.util.SlotType;
import java.util.Set;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class EasyGameBoard extends AbstractBoardImpl{

    private final static BoardType myBoardType = BoardType.EASY; 

    private static final int WIDTH = 40;
    private static final int HEIGHT = 30;

    private static final int INITIAL_X_EASY_BOARD = 5;
    private static final int INITIAL_Y_EASY_BOARD = 25;

    private static final double PROB_PATH = 0.5;
    private static final double PROB_SINGLEPLAYER = 0.1;
    private static final double PROB_MALUS = 0.05;
    private static final double PROB_MULTIPLAYER = 0.1;
    private static final double PROB_SHOP = 0.1;
    private static final double PROB_BONUS = 0.15;

    private static final String FilePath = "C:\\Users\\81W1019HIX\\OneDrive\\Desktop\\MARIO PARTY\\OOP23-m-party\\src\\main\\java\\it\\unibo\\mparty\\model\\gameBoard\\file\\EasyBoardGame.txt";

    private Set<Pair<SlotType,Double>> rules = 
        Set.of(new Pair<>(SlotType.PATH, PROB_PATH),
               new Pair<>(SlotType.SINGLEPLAYER, PROB_SINGLEPLAYER),
               new Pair<>(SlotType.MALUS, PROB_MALUS),
               new Pair<>(SlotType.MULTIPLAYER, PROB_MULTIPLAYER),
               new Pair<>(SlotType.SHOP, PROB_SHOP),
               new Pair<>(SlotType.BONUS, PROB_BONUS));

    private static final Set<Position> starsPositions = Set.of(new Position(5, 6), 
                                                               new Position(25, 21),
                                                               new Position(34, 6));
           
    private List<SlotType> avaiableSlotsType = RandomListGenerator.generateRandomList(this.rules);

    public EasyGameBoard() {
        super();
        this.generateBoard();
    }

    @Override
    public void generateBoard() {
        this.addSlot(starsPositions.stream().skip(new Random().nextInt(starsPositions.size())).findFirst().get(), SlotType.ACTIVE_STAR);
        starsPositions.stream().forEach(p -> this.addSlot(p, SlotType.NOT_ACTIVE_STAR));
        this.addSlot(getStrartingPosition(), SlotType.PATH);
        this.createPathFromFile(FilePath);
        }

    @Override
    protected SlotType getNewSlotType() {
        if (this.avaiableSlotsType.isEmpty()) {
            this.avaiableSlotsType = RandomListGenerator.generateRandomList(this.rules);
        }
        SlotType output = this.avaiableSlotsType.getFirst();
        this.avaiableSlotsType.removeFirst();
        return output;
    }

    @Override
    public BoardType getBoardType() {
        return myBoardType;
    }

    @Override
    protected Set<Position> setStarsPosition() {
        return Collections.unmodifiableSet(starsPositions);
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
        return new Position(INITIAL_X_EASY_BOARD, INITIAL_Y_EASY_BOARD);
    }
}
