package it.unibo.mparty.model.minigames.connect4.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.mparty.model.minigames.connect4.api.Connect4Model;
import it.unibo.mparty.utilities.Connect4Directions;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;

public class Connect4ModelImpl implements Connect4Model{

    private final static int ROW_NUMBER = 6;
    private final static int COLUMN_NUMBER = 8;
    private String player1;
    private String player2;
    private String turnPlayer;
    private Position lastSelected;
    private int coinsWon=0;
    private Map<Position,String> selectedMap;

    public Connect4ModelImpl() {
        selectedMap = new HashMap<>();
        lastSelected = new Position(-1, -1);
    }

    @Override
    public boolean isOver() {
        if (isADraw() == true) {
            turnPlayer=player2;
            coinsWon = 5;
            return true;
        }
        if (hasWon(lastSelected.getX(), lastSelected.getY())) {
            coinsWon = 10;
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
        lastSelected = new Position(row, column);
        selectedMap.put(lastSelected, getTurnPlayer());
        return true;
    }

    @Override
    public int getAvailableRow (int column) {
        return IntStream.range(0, ROW_NUMBER)
        .map(it -> ROW_NUMBER -1 -it)
        .filter(i -> !selectedMap.containsKey(new Position(i, column)))
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

    @Override
    public String getPlayer1() {
        return this.player1;
    }

}
