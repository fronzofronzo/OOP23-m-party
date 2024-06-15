package it.unibo.mparty.model.gameBoard.api;

import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public interface Borad {

    public void addSlot(Position position, SlotType slotType);

    public void addConnection(Position from, Position to, Direction dir);

    public Slot geSlot(Position positoin);
}