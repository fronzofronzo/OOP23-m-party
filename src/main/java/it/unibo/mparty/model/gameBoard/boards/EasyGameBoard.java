package it.unibo.mparty.model.gameBoard.boards;

import it.unibo.mparty.model.gameBoard.impl.AbstractBoardImpl;
import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.SlotType;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Random;
import java.util.Map;

public class EasyGameBoard extends AbstractBoardImpl{

    private static final int N_PATH = 87;
    private static final int N_SINGLEPLAYER = 10;
    private static final int N_MULTIPLAYER = 10;
    private static final int N_BONUS = 20;
    private static final int N_MALUS = 10;
    private static final int N_SHOPS = 10;

    private Map<SlotType, Integer> rules = Map.of(SlotType.PATH, N_PATH,
                                                  SlotType.SINGLEPLAYER, N_SINGLEPLAYER, 
                                                  SlotType.MULTIPLAYER, N_MULTIPLAYER,
                                                  SlotType.BONUS, N_BONUS, 
                                                  SlotType.MALUS, N_MALUS,
                                                  SlotType.SHOP, N_SHOPS);

    private Set<Position> stars = Set.of(new Position(6, 6), 
                                         new Position(25, 21),
                                         new Position(34, 6));
    

    public EasyGameBoard(int width, int height, Position initialPosition) {
        super(width, height, initialPosition);
        this.generateBoard();
    }

    @Override
    public void generateBoard() {
        this.addSlot(getInitialPosition(), SlotType.PATH);
        this.stars.stream().forEach(p -> this.addSlot(p, SlotType.NOT_ACTIVE_STAR));
        
    }

    @Override
    protected SlotType getNewSlotType() {
        Set<SlotType> possibleSlotTypes = this.rules.entrySet()
                                                    .stream()
                                                    .filter(entry -> entry.getValue() > 0)
                                                    .map(entry -> entry.getKey())
                                                    .collect(Collectors.toSet());
        SlotType newSlotType = possibleSlotTypes.stream()
                                                .skip(new Random().nextInt(possibleSlotTypes.size()))
                                                .findFirst()
                                                .get();
        this.rules.put(newSlotType, this.rules.get(newSlotType)-1);
        return newSlotType;
        
    }
}
