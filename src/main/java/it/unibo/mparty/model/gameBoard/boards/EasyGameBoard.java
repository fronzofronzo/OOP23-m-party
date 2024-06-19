package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.impl.AbstractBoardImpl;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.Pair;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.RandomListGenerator;
import it.unibo.mparty.model.gameBoard.util.SlotType;
import java.util.Set;
import java.util.List;
import java.util.Random;

public class EasyGameBoard extends AbstractBoardImpl{

    private Set<Pair<SlotType,Double>> rules = Set.of(new Pair<>(SlotType.PATH, 0.5),
                                                      new Pair<>(SlotType.SINGLEPLAYER, 0.1),
                                                      new Pair<>(SlotType.MALUS, 0.05),
                                                      new Pair<>(SlotType.MULTIPLAYER, 0.1),
                                                      new Pair<>(SlotType.SHOP, 0.1),
                                                      new Pair<>(SlotType.BONUS, 0.15));

    private List<SlotType> avaiableSlotsType = RandomListGenerator.generateRandomList(this.rules);

    private Set<Position> stars = Set.of(new Position(5, 6), 
                                         new Position(25, 21),
                                         new Position(34, 6));
    

    public EasyGameBoard(int width, int height, Position initialPosition) {
        super(width, height, initialPosition);
        this.generateBoard();
    }

    @Override
    public void generateBoard() {
        this.addSlot(this.stars.stream().skip(new Random().nextInt(this.stars.size())).findFirst().get(), SlotType.ACTIVE_STAR);
        this.addSlot(getStrartingPosition(), SlotType.PATH);
        this.stars.stream().forEach(p -> this.addSlot(p, SlotType.NOT_ACTIVE_STAR));
        createPath(this.getStrartingPosition(), 19, Direction.UP);
        /* 
        createPath(new Position(5, 6), 11, Direction.RIGHT);
        createPath(new Position(16, 6), 22, Direction.DOWN);        
        createPath(new Position(16, 28), 5, Direction.LEFT);       
        createPath(new Position(11, 28), 4, Direction.UP);       
        createPath(new Position(11, 24), 5, Direction.LEFT);       
        createPath(new Position(16, 10), 7, Direction.RIGHT);      
        createPath(new Position(16, 18), 7, Direction.RIGHT);      
        createPath(new Position(23, 19), 17, Direction.UP);      
        createPath(new Position(23, 2), 11, Direction.RIGHT);    
        createPath(new Position(34, 2), 24, Direction.DOWN);       
        createPath(new Position(34, 26), 9, Direction.LEFT);     
        createPath(new Position(25, 26), 7, Direction.UP);              
        createPath(new Position(25, 19), 2, Direction.LEFT);
        */
        this.createPathFromFile("C:\\Users\\81W1019HIX\\OneDrive\\Desktop\\MARIO PARTY\\OOP23-m-party\\src\\main\\java\\it\\unibo\\mparty\\model\\gameBoard\\file\\EasyBoardGame.txt");
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
}
