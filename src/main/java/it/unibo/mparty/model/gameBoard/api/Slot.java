package it.unibo.mparty.model.gameBoard.api;

import java.util.Map;
import java.util.Optional;

import it.unibo.mparty.model.gameBoard.util.Coordinate;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public interface Slot {

    public Coordinate getCoordinate();

    public void addConnection();

    public Optional<Slot> getConnection(Direction dir);

    public Map<Direction, Slot> getConnections();

    public SlotType getSlotType();    
}