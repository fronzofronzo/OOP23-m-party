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
    private int counter = 3;
    private Set<Position> player1;
    private Set<Position> player2;
    private Random random;
    private int side;
    private boolean turn = true;

    public MemorySweepImpl(int side){
        this.random = new Random();
        this.randomList = new HashSet<>();
        this.player1 = new HashSet<>();
        this.player2 = new HashSet<>();
        this.side = side;
    }

    /*
     * MIGLIORA CON STREAM FUNZIONALI
     */
    @Override
    public Set<Position> getRandomList() {
        this.counter++;
        this.randomList.clear();
        for(var i = 0; i < this.counter; i++){
            this.randomList.add(new Position(random.nextInt(this.side),random.nextInt(this.side)));
        }
        return this.randomList;
    }

    @Override
    public HitType hit(Position p) {
        if(turn){
            if(this.player1.size() < this.randomList.size()){
                this.player1.add(p);
                return HitType.FIRST;
            }
            this.turn = false;
        }
        if(this.player2.size() < this.randomList.size()){
            this.player2.add(p);
        }
        this.turn = true;
        return HitType.SECOND;
    }

    @Override
    public boolean getTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTurn'");
    }

    
}