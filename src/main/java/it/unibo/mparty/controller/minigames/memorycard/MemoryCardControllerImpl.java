package it.unibo.mparty.controller.minigames.memorycard;

import it.unibo.mparty.model.minigames.memorycard.api.MemoryCardModel;
import it.unibo.mparty.model.minigames.memorycard.impl.MemoryCardModelImpl;
import it.unibo.mparty.view.minigames.memorycard.MemoryCardView;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;
import java.util.List;

/**
 * This class implements the {@link MemoryCardController} interface.
 */
public class MemoryCardControllerImpl implements MemoryCardController {

    private final MemoryCardModel model;
    private MemoryCardView view;

    /**
     * Constructor of the Memory Card controller. It initialises the reference
     * to model and controller of the minigame.
     *
     * @param view of the game.
     */
    public MemoryCardControllerImpl(final MemoryCardView view) {
        this.model = new MemoryCardModelImpl();
        this.setView(view);
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
            this.updateGameView();
            if (this.model.isOver()) {
                this.updateEndGameView();
                this.view.showResult(this.model.getResult());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame() {
        final Logger logger = Logger.getAnonymousLogger();
        this.view.getMainController().saveMinigameResult(this.model.getResult());
        try {
            this.view.getMainView().setBoardScene();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.toString());
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
        this.view.showMistakesNumber(this.model.getMistakes());
        for (final var e : this.model.getCards().entrySet()) {
            final var type = e.getValue();
            final var i = e.getKey();
            if (guessed.contains(type)) {
                this.view.setCardType(i, type.getName());
                this.view.setCardStatus(i, false);
            } else {
                this.view.setCardType(i, "");
                this.view.setCardStatus(i, true);
            }
        }
    }

    private void updateEndGameView() {
        final int n = this.model.getCards().size();
        for (int i = 0; i < n; i++) {
            this.view.setCardStatus(i, false);
        }
    }

    private void setView(final MemoryCardView view) {
        this.view  = view;
    }

}
