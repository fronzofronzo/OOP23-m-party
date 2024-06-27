package it.unibo.mparty.model.minigames.memorysweep.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.utilities.Position;

public class MemorySweepImpl implements MemorySweep{

    private Set<Position> randomList;
    private int counter;
    private Set<Position> player1;
    private Set<Position> player2;
    private Random random;
    private int side;
    private boolean turn = true;
    private Set<Position> winner;
    private static int START = 3;

    public MemorySweepImpl(int side){
        this.random = new Random();
        this.randomList = new HashSet<>();
        this.player1 = new HashSet<>();
        this.player2 = new HashSet<>();
        this.side = side;
        this.counter = START;
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

    private Position getNewPosition(){
        Position p;
        do {
            p = new Position(random.nextInt(this.side),random.nextInt(this.side));
        }while(this.getRandomList().contains(p));
        return p;
    }

    private void setCounter(){
        this.counter++;
    }

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

    private void changeTurn(){
        this.turn = !this.turn;
    }

    
}