package it.unibo.mparty.model.gameBoard.api;

public interface SlotFactory {

    Slot empty();

    Slot miniGameSinglePlayer();

    Slot miniGameMultiPlayer();

    Slot bonus();

    Slot malus();

    Slot star();

    Slot shop();
}