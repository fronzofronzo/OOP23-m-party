package it.unibo.mparty.model.minigames.connect4.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.mparty.model.gameBoard.util.Pair;
import it.unibo.mparty.model.minigames.connect4.api.Connect4Model;
import it.unibo.mparty.utilities.Connect4Directions;
import it.unibo.mparty.utilities.Position;

public class Connect4ModelImpl implements Connect4Model{

    private final static int ROW_NUMBER = 6;
    private final static int COLUMN_NUMBER = 8;
    private String player1;
    private String player2;
    private String turnPlayer;
    private Position lastSelected = new Position(-1, -1);
    private int coinsWon;
    private Map<Position,String> selectedMap = new HashMap<>();

    @Override
    public boolean isOver() {
        if (isADraw() == true) {
            turnPlayer=player2;
            coinsWon = 5;
            return true;
        }
        if (hasWon(lastSelected.getX(), lastSelected.getY())) {
            coinsWon = 15;
            return true;
        }
        manageTurn();
        return false;
    }

    @Override
    public Pair<String, Integer> getResult() {
        return new Pair<String,Integer>(getTurnPlayer(),coinsWon);
    }

    @Override
    public void setUpPlayers(List<String> players) {
        player1 = players.get(0);
        player2 = players.get(1);
        turnPlayer = player1;
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
        lastSelected = new Position(column, row);
        selectedMap.put(lastSelected, getTurnPlayer());
        return true;
    }

    private int getAvailableRow (int column) {
        return IntStream.range(0, ROW_NUMBER)
        .filter(i -> !(selectedMap.keySet().contains(new Position(i,column))))
        .findFirst()
        .orElse(-1);
    }

    private void manageTurn () {
        if (getTurnPlayer()==player1) {
            turnPlayer=player2;
        }
        else {
            turnPlayer=player1;
        }
    }

    private boolean hasWon(int i, int j) {
        return Stream.of(Connect4Directions.values())
        .anyMatch(dir -> checkDirections(i, j, dir));
    }

    private boolean checkDirections (int i, int j, Connect4Directions direction)
    {
        return IntStream.rangeClosed(-3, 3)
        .map(off -> countMatches(i, j, direction, off))
        .sum() >= 4;
    }

    private int countMatches (int i,int j, Connect4Directions direction, int offset) {
        Position check = new Position(i + offset * direction.getPosition().getX(), j + offset * direction.getPosition().getY());
        if (selectedMap.containsKey(check) && selectedMap.get(check).equals(getTurnPlayer())) {
            return 1;
        }
        return 0;
    }

}
