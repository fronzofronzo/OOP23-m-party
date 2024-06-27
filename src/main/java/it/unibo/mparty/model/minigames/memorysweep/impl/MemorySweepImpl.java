package it.unibo.mparty.model.minigames.memorysweep.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    /*
     * DA SISTEMARE
     */
    @Override
    public HitType hit(Position p) {
        if(this.getTurn()){
            if(this.randomList.contains(p)){
                this.player1.add(p);
                if(this.player1.size() == this.randomList.size()){
                    this.turn = false;
                    this.player1.clear();
                    return HitType.TURN_END;//player 1 ha passato il turno tocca al player 2
                }
                return HitType.RIGHT_CHOICE;
            }
            this.winner = player2;
            return HitType.LOSS;//player 1 ha perso,ha vinto player 2
        }
        
        if(this.randomList.contains(p)){
            this.player2.add(p);
            if(this.player2.size() == this.randomList.size()){
                this.turn = true;
                this.player2.clear();
                return HitType.TURN_END;//player 2 ha passato il turno tocca al player 1
            }
            return HitType.RIGHT_CHOICE;
        }
        this.winner = player1;
        return HitType.LOSS;//player 2 ha perso,ha vinto player 1
    }

    @Override
    public boolean getTurn() {
        return this.turn;
    }

    

    private void setCounter(){
        this.counter++;
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

    
}