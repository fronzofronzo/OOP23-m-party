package it.unibo.mparty.model.gameBoard.impl;

import java.util.Map;
import java.util.Optional;

import it.unibo.mparty.model.gameBoard.api.Slot;
import it.unibo.mparty.model.gameBoard.util.Coordinate;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public class SlotImpl implements Slot {

    @Override
    public Coordinate getCoordinate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoordinate'");
    }

    @Override
    public void addConnection() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addConnection'");
    }

    @Override
    public Optional<Slot> getConnection(Direction dir) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConnection'");
    }

    @Override
    public Map<Direction, Slot> getConnections() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConnections'");
    }

    @Override
    public SlotType getSlotType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSlotType'");
    }
}