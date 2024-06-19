package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;

public class PerilousPathImpl implements PerilousPath{

    private Optional<List<BombPosition>> bombs = Optional.empty();
    private Optional<List<BallPosition>> balls = Optional.empty();
    private Optional<List<PathPosition>> path = Optional.empty();

    public PerilousPathImpl(){
        this.bombs = Optional.of(new LinkedList<>());
        this.balls = Optional.of(new LinkedList<>());
        this.path = Optional.of(new LinkedList<>());
    }

    @Override
    public void setBombs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBombs'");
    }

    @Override
    public void setBalls() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBalls'");
    }

    @Override
    public List<AbstractPosition> getBombs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBombs'");
    }

    @Override
    public List<AbstractPosition> getBalls() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBalls'");
    }

    
}
