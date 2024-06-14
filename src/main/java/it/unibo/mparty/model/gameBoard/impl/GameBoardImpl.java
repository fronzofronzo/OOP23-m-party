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
    private static final int N_SLOTS_EMPTY = 12;
    private static final int N_SLOT_MINIGAME_SINGLEPLAYER = 5;
    private static final int N_SLOTS_MINIGAME_MULTIPLAYER = 5;
    private static final int N_SLOTS_BONUS = 10;
    private static final int N_SLOTS_MALUS = 10;
    private static final int N_SLOTS_STAR = 4;
    private static final int N_SLOTS_SHOP = 4;
    private Map<Integer,SlotType> myBoard = new HashMap<>();
    private final SlotFactory factory = new SlotFactoyImpl();
    private final Set<SlotType> slotTypes = new HashSet<>(Set.of(SlotType.BONUS,SlotType.MALUS,SlotType.SINGLEPLAYER,SlotType.MULTIPLAYER,SlotType.STAR,SlotType.SHOP,SlotType.EMPTY));

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
        if (tmpSlotType == SlotType.EMPTY){
            return count <= N_SLOTS_EMPTY;
        };
        return false;
    }

    private SlotType getRandomSlotType() {
        Random random = new Random();
        Optional<SlotType> output = this.slotTypes.stream()
                                                    .skip(random.nextInt(this.slotTypes.size()))
                                                    .findFirst();
        return output.get();
    }
    

}