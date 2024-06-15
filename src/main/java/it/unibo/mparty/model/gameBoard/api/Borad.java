package it.unibo.mparty.model.gameBoard.api;

import it.unibo.mparty.model.gameBoard.util.Coordinate;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public interface Borad {

    public void addSlot(Coordinate position, SlotType slotType);

    public void addConnection(Coordinate from, Coordinate to, Direction dir);

    public Slot geSlot(Coordinate positoin);
}