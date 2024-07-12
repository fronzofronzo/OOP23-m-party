package it.unibo.mparty.controller.minigames.secretcode;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.mparty.model.minigames.secretcode.api.SecretCodeModel;
import it.unibo.mparty.model.minigames.secretcode.impl.SecretCodeModelImpl;
import it.unibo.mparty.model.minigames.secretcode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretcode.util.SecretCodeResults;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.minigames.secretcode.SecretCodeView;

/**
 * This class implements {@link SecretCodeController}.
 */
public class SecretCodeControllerImpl implements SecretCodeController {

    private static final Logger LOGGER = Logger.getLogger(SecretCodeControllerImpl.class.getName());
    private final SecretCodeModel model;
    private final SecretCodeView view;

    /**
     * This is the constructor of this class that. It set the reference to the model
     * and the view of the game.
     * 
     * @param view the view of the game.
     */
    public SecretCodeControllerImpl(final SecretCodeView view) {
        this.model = new SecretCodeModelImpl();
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame() {
        if (this.model.isOver()) {
            this.view.getMainController().saveMinigameResult(this.model.getResult());
            try {
                this.view.getMainView().setBoardScene();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "ERROR: ", e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame(final List<String> players) {
        this.model.setUpPlayers(players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addColor(final SecretCodeColors color) {
        if (!this.model.isOver() && this.model.addColor(color)) {
            final Pair<Integer, Integer> pos = new Pair<>(this.model.getCurrentGuess().size() - 1,
                    this.model.getTurn());
            this.view.updateGuess(this.model.getCurrentPlayer(), pos, color);
        }
        this.updateEndaGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guess() {
        if (!this.model.isOver()) {
            final String player = this.model.getCurrentPlayer();
            final int turn = this.model.getTurn();
            final List<SecretCodeResults> res = this.model.getGuessResult();
            if (!res.isEmpty()) {
                this.view.updateResults(player, turn, res);
            }
        }
        this.updateEndaGame();
    }

    private void updateEndaGame() {
        if (this.model.isOver()) {
            this.view.showSolution(this.model.getSoluction());
            this.view.showResult(new Pair<String, Integer>(this.model.getWinner(), 10));
        }
    }
}
