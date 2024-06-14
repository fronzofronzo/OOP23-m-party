package it.unibo.mparty.model.gameBoard.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.model.gameBoard.api.Slot;
import it.unibo.mparty.model.gameBoard.api.SlotFactory;

public class GameBoardImpl implements GameBoard {

    private static final int N_SLOTS = 50;
    private static final int SLOTS_EMPTY = 12;
    private static final int SLOTS_MINIGAME_SINGLEPLAYER = 5;
    private static final int SLOTS_MINIGAME_MULTIPLAYER = 5;
    private static final int SLOTS_BONUS = 10;
    private static final int SLOTS_MALUS = 10;
    private static final int SLOTS_STAR = 4;
    private static final int SLOTS_SHOP = 4;
    private Map<Integer,SlotType> myBoard = new HashMap<>();
    private final SlotFactory factory = new SlotFactoyImpl();
    private final Set<Pair<SlotType,Integer>> rules = new HashSet<>(Set.of( new Pair<>(SlotType.EMPTY, SLOTS_EMPTY),
                                                                            new Pair<>(SlotType.SINGLEPLAYER, SLOTS_MINIGAME_SINGLEPLAYER),
                                                                            new Pair<>(SlotType.MULTIPLAYER, SLOTS_MINIGAME_MULTIPLAYER),
                                                                            new Pair<>(SlotType.BONUS, SLOTS_BONUS),
                                                                            new Pair<>(SlotType.MALUS, SLOTS_MALUS),
                                                                            new Pair<>(SlotType.STAR, SLOTS_STAR),
                                                                            new Pair<>(SlotType.SHOP, SLOTS_SHOP)));

    @Override
    public void createGameBoard() {
        this.myBoard = this.setUpBoard();
    }

    private Map<Integer,SlotType> setUpBoard() {
        Map<Integer,SlotType> tmp = new HashMap<>();
        for (int i = 0; i == N_SLOTS; i++){
            SlotType tmpSlotType;
            do {
                tmpSlotType = this.getRandomSlotType();                
            } while (!isValidSlot(tmp,tmpSlotType));
            tmp.put(i, tmpSlotType);
        }
        return tmp;
    }

    private boolean isValidSlot(Map<Integer, SlotType> tmp, SlotType tmpSlotType) {
        int count = 0;
        for (final Map.Entry<Integer, SlotType> entry : tmp.entrySet()) {
            if (entry.getValue() == tmpSlotType) {
                count++;
            }
        }
        for (Pair<SlotType,Integer> rule : rules) {
            if (rule.get1() == tmpSlotType){
                return count <= rule.get2();
            }
        }
        return false;
    }

    private SlotType getRandomSlotType() {
        Random random = new Random();
        return this.rules.stream().skip(random.nextInt(this.rules.size())).findFirst().get().get1();
    }

    @Override
    public Slot geSlot(int position) {
        if (myBoard.get(position) == SlotType.EMPTY) {
            return this.factory.empty();
        }
        return null;
    }
    

}