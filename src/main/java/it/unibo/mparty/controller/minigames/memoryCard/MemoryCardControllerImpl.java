package it.unibo.mparty.controller.minigames.memoryCard;

import it.unibo.mparty.model.minigames.memoryCard.api.MemoryCardModel;
import it.unibo.mparty.model.minigames.memoryCard.impl.MemoryCardModelImpl;
import it.unibo.mparty.view.minigames.memoryCard.MemoryCardView;

import java.io.IOException;
import java.util.List;

/**
 * This class implements the {@link MemoryCardController} interface.
 */
public class MemoryCardControllerImpl implements MemoryCardController {

    private final MemoryCardModel model;
    private final MemoryCardView view;

    /**
     * Constructor of the Memory Card controller. It initialises the reference
     * to model and controller of the minigame.
     * @param view of the game.
     */
    public MemoryCardControllerImpl(final MemoryCardView view) {
        this.model = new MemoryCardModelImpl();
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectCard(final int index) {
        if (this.model.flip(index)) {
            this.view.setCardStatus(index, false);
            this.view.setCardType(index, this.model.getCards().get(index).getName());
        } else {
            if (this.model.isOver()) {
                this.updateEndGameView();
                this.view.showResult(this.model.getResult());
            } else {
                this.updateGameView();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame() {
        this.view.getMainController().saveMinigameResult(this.model.getResult());
        try {
            this.updateEndGameView();
            this.view.getMainView().setBoardScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame(final List<String> players) {
        this.model.setUpPlayers(players);
        final int n = this.model.getCards().size();
        for (int i = 0; i < n; i++) {
            this.view.addCard(this.model.getCards().get(i).getName());
        }
    }

    private void updateGameView() {
        final var guessed = this.model.guessedCardsType();
        this.view.setMistakesNumber(this.model.getMistakes());
        for (var e : this.model.getCards().entrySet()) {
            var type = e.getValue();
            var i = e.getKey();
            if (guessed.contains(type)) {
                this.view.setCardType(i, type.getName());
                this.view.setCardStatus(i, false);
            } else {
                this.view.setCardType(i, "");
                this.view.setCardStatus(i, true);
            }
        }
    }

    private void updateEndGameView(){
        final int n = this.model.getCards().size();
        for (int i = 0; i < n; i ++) {
            this.view.setCardStatus(i,false);
        }
    }

}
