package it.unibo.mparty.model.gameBoard.api;

public interface GameBoard {

    //come input potrebbe avere un numero di caselle o una difficoltà selezionata
    void createGameBoard();

    Slot geSlot(int position);

}