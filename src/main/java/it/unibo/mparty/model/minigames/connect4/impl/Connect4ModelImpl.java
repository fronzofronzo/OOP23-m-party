package it.unibo.mparty.model.minigames.connect4.impl;

import java.util.List;

import it.unibo.mparty.model.gameBoard.util.Pair;
import it.unibo.mparty.model.minigames.connect4.api.Connect4Model;

public class Connect4ModelImpl implements Connect4Model{

    private final static int ROW_NUMBER = 6;
    private final static int COLUMN_NUMBER = 8;
    private String player1;
    private String player2;

    @Override
    public boolean isOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOver'");
    }

    @Override
    public Pair<String, Integer> getResult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResult'");
    }

    @Override
    public void setUpPlayers(List<String> players) {
        player1 = players.get(0);
        player2 = players.get(1);
    }

}
