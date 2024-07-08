package it.unibo.mparty.model.minigames.memorysweep.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;

public class MemorySweepImpl implements MemorySweep{

    private final Set<Position> randomList;
    private int counter;
    private final Set<Position> player1;
    private final Set<Position> player2;
    private final Random random;
    private final int side;
    private boolean turn = true;
    private Set<Position> winner;

    public MemorySweepImpl(int side){
        this.random = new Random();
        this.randomList = new HashSet<>();
        this.player1 = new HashSet<>();
        this.player2 = new HashSet<>();
        this.side = side;
        this.counter = 3;
    }

    @Override
    public void setRandomList() {
        this.setCounter();
        this.randomList.clear();
        Stream.iterate(0, i -> i + 1).limit(this.counter).map(i -> this.randomList.add(getNewPosition()));
    }

    @Override
    public Set<Position> getRandomList() {
        return this.randomList;
    }

    
    @Override
    public HitType hit(Position p) {
        return this.getTurn() ? this.playerTurn(player1, p) : this.playerTurn(player2, p);
    }

    @Override
    public boolean getTurn() {
        return this.turn;
    }

    @Override
    public Set<Position> getWinner() {
        return this.winner;
    }
    /**
     * method for getting a new position which is not already included in the randomList variable
     * @return the new position 
     */
    private Position getNewPosition(){
        Position p;
        do {
            p = new Position(random.nextInt(this.side),random.nextInt(this.side));
        }while(this.getRandomList().contains(p));
        return p;
    }
    /**
     * method for increasing the list of buttons that will be recreated
     */
    private void setCounter(){
        this.counter++;
    }
    /**
     * the actual method that manages the players turns
     * @param player the set of the player
     * @param p the position clicked by that player
     * @return whether his guess was right(in this case returns whether the guess is over or not) or wrong
     */
    private HitType playerTurn(Set<Position> player,Position p){
        if(this.randomList.contains(p)){
            player.add(p);
            if(player.size() == this.randomList.size()){
                this.changeTurn();
                player.clear();
                return HitType.TURN_END;//player 1 ha passato il turno tocca al player 2
            }
            return HitType.RIGHT_CHOICE;
        }
        this.winner = player.equals(this.player1) ? player2 : player1;
        return HitType.LOSS;//player 1 ha perso,ha vinto player 2
    }

    /**
     * changes the turn
     */
    private void changeTurn(){
        this.turn = !this.turn;
    }

    @Override
    public int getCounter() {
        return this.counter;
    }


    @Override
    public Pair<String, Integer> getResult() {
        return null;
    }

    @Override
    public void setUpPlayers(List<String> players) {

    }

    @Override
    public boolean isOver() {
        return false;
    }
}