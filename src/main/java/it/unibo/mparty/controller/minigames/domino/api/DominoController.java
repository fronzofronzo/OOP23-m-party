package it.unibo.mparty.controller.minigames.domino.api;

import it.unibo.mparty.controller.minigames.MinigameController;

public interface DominoController extends MinigameController {

    void playTile(int SideA, int SideB);

    void checkDraw();

    void drawTile();
}
