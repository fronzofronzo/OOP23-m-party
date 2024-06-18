package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.impl.AbstractBoardImpl;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.SlotType;
import java.util.Set;
import java.util.Random;

public class EasyGameBoard extends AbstractBoardImpl{

    private Set<SlotType> avaiableSlotsType = Set.of(SlotType.PATH,
                                                     SlotType.SINGLEPLAYER,
                                                     SlotType.MULTIPLAYER,
                                                     SlotType.BONUS,
                                                     SlotType.MALUS,
                                                     SlotType.SHOP);

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
        this.addSlot(getInitialPosition(), SlotType.PATH);
        this.stars.stream().forEach(p -> this.addSlot(p, SlotType.NOT_ACTIVE_STAR));
        createPath(this.getInitialPosition(), 19, Direction.UP);
        createPath(new Position(5, 6), 11, Direction.RIGHT);
        createPath(new Position(16, 6), 22, Direction.DOWN);        
        createPath(new Position(16, 28), 6, Direction.LEFT);       
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
        this.changeStarPosition();    
        }

    private void createPath(Position from, int steps, Direction currentDir){
        this.addSlot(from, getNewSlotType());
        Position to = this.getNeighbor(from, currentDir);
        for (int i = 0; i < steps; i++) {
            this.addSlot(to, getNewSlotType());
            this.addConnection(from, to, currentDir);
            from=to;
            to=this.getNeighbor(to, currentDir);
        }
    }

    @Override
    protected SlotType getNewSlotType() {
        return this.avaiableSlotsType.stream()
        .skip(new Random().nextInt(this.avaiableSlotsType.size()))
        .findFirst()
        .get();
    }
}
