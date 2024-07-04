package it.unibo.mparty.controller.minigames.domino.api;

public interface DominoController {
    void setUp();

    void playTile(int SideA, int SideB);

    void checkDraw();

    void drawTile();

    void updatePlayersTiles();

    void updateTurn();

    void updateBoard();

    void haveWinner();
}
