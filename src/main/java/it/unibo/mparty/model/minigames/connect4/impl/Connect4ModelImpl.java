package it.unibo.mparty.model.minigames.connect4.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import it.unibo.mparty.model.gameBoard.util.Pair;
import it.unibo.mparty.model.minigames.connect4.api.Connect4Model;
import it.unibo.mparty.utilities.Position;

public class Connect4ModelImpl implements Connect4Model{

    private final static int ROW_NUMBER = 6;
    private final static int COLUMN_NUMBER = 8;
    private String player1;
    private String player2;
    private String turnPlayer;
    private Map<String,Position> selectedMap = new HashMap<>();

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

    private boolean isADraw() {
        return selectedMap.size()>=(ROW_NUMBER * COLUMN_NUMBER);
    }

    @Override
    public String getTurnPlayer() {
        return this.turnPlayer;
    }

    @Override
    public boolean addDisc(int column) {
        int row = getAvailableRow(column);
        if (row ==-1) {
            return false;
        }
        manageTurn();
        return true;
    }

    private int getAvailableRow (int column) {
        try {
            return IntStream.range(0, ROW_NUMBER)
            .filter(i -> !(selectedMap.values().contains(new Position(i,column))))
            .findFirst()
            .getAsInt();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    private void manageTurn () {
        if (getTurnPlayer()==player1) {
            turnPlayer=player2;
        }
        else {
            turnPlayer=player1;
        }
    }

    

}
