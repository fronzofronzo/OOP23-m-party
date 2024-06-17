package it.unibo.mparty.model.gameBoard.api;

import it.unibo.mparty.model.gameBoard.util.Position;
import it.unibo.mparty.model.gameBoard.util.Direction;
import it.unibo.mparty.model.gameBoard.util.SlotType;

public interface Board {

    void addSlot(Position position, SlotType slotType);

    void addConnection(Position from, Position to, Direction dir);

    Slot getSlot(Position positoin);

    Position getInitialPosition();
}