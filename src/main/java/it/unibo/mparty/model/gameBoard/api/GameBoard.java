package it.unibo.mparty.model.gameBoard.api;

import it.unibo.mparty.model.gameBoard.util.Position;

public interface GameBoard {

    Slot getSlot(Position positoin);

    Position getinitialPosition();
}