package it.unibo.mparty.model.gameBoard.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.model.gameBoard.api.Slot;

public class GameBoardImpl implements GameBoard {

    private static final int N_SLOTS = 30;
    private Map<Slot,Set<Integer>> myBoard = new HashMap<>();

    @Override
    public void createGameBoard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createGameBoard'");
    }
    

}