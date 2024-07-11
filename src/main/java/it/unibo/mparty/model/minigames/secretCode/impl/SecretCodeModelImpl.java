package it.unibo.mparty.model.minigames.secretCode.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Map;

import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.minigames.secretCode.api.SecretCodeModel;
import it.unibo.mparty.model.minigames.secretCode.api.SecretCodePlayer;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeResults;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.RandomFromSet;

/**
 * Thi class implements {@link SecretCodeModel}.
 */
public class SecretCodeModelImpl implements SecretCodeModel {

    private static final int DIM_SOLUCTION = 4;
    private static final int TURNS = 8;
    private static final int PUNTI_POS_COL_COR = 5;
    private static final int PUNTI_COL_COR = 2;
    private static final int PUNTI_ERRORE = 0;
    private static final Map<SecretCodeResults, Integer> PUNTEGGIO = Map.of(
            SecretCodeResults.CORRECT_COLOR_AND_POSITION, PUNTI_POS_COL_COR,
            SecretCodeResults.CORRECT_COLOR, PUNTI_COL_COR,
            SecretCodeResults.WRONG_COLOR, PUNTI_ERRORE);
    private static final Integer COINS_WINNER = 10;
    private List<SecretCodePlayer> players = new ArrayList<>();;
    private List<SecretCodeColors> soluction = new ArrayList<>();
    private int actualPlayerIndex = 0;
    private int turn = 1;
    private Optional<String> winner = Optional.empty();

    public SecretCodeModelImpl() {
        this.generateSoluction();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SecretCodeResults> getGuessResult() {
        List<SecretCodeResults> res = computeResult(this.players.get(actualPlayerIndex).getGuess());
        if (!res.isEmpty()) {
            this.players.get(actualPlayerIndex).startNewGuess();
            res.forEach(r -> this.players.get(actualPlayerIndex).addPoints(PUNTEGGIO.get(r)));
            this.nextPlayer();
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SecretCodeColors> getCurrentGuess() {
        return this.players.get(actualPlayerIndex).getGuess();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SecretCodeColors> getSoluction() {
        return Collections.unmodifiableList(this.soluction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addColor(SecretCodeColors color) {
        return this.players.get(actualPlayerIndex).addColor(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCurrentPlayer() {
        return this.players.get(actualPlayerIndex).getNamePlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.turn > TURNS || this.winner.isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWinner() {
        if (this.winner.isEmpty()) {
            double maxPoints = 0;
            for (int i = 0; i < this.players.size(); i++) {
                double points = this.players.get(actualPlayerIndex).getPoints();
                if (points > maxPoints) {
                    maxPoints = points;
                    this.winner = Optional.of(this.players.get(actualPlayerIndex).getNamePlayer());
                }
            }
        }
        return this.winner.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTurn() {
        return this.turn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<String, Integer> getResult() {
        return new Pair<String, Integer>(this.getWinner(), COINS_WINNER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpPlayers(List<String> players) {
        players
                .forEach(p -> this.players.add(new SecretCodePlayerImpl(p, this.soluction.size())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "secretCode";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MinigameType getType() {
        return MinigameType.MULTI_PLAYER;
    }

    private void generateSoluction() {
        do {
            SecretCodeColors tmp = RandomFromSet.get(Set.of(SecretCodeColors.values()));
            if (this.soluction.isEmpty() || !this.soluction.contains(tmp)) {
                this.soluction.add(tmp);
            }
        } while (this.soluction.size() < DIM_SOLUCTION);
    }

    private List<SecretCodeResults> computeResult(List<SecretCodeColors> guess) {
        if (guess.size() != DIM_SOLUCTION) {
            return Collections.emptyList();
        }
        List<SecretCodeResults> results = new ArrayList<>();
        for (int i = 0; i < guess.size(); i++) {
            if (this.soluction.contains(guess.get(i))
                    && i == this.soluction.indexOf(guess.get(i))) {
                results.add(SecretCodeResults.CORRECT_COLOR_AND_POSITION);
            } else if (this.soluction.contains(guess.get(i))) {
                results.add(SecretCodeResults.CORRECT_COLOR);
            } else {
                results.add(SecretCodeResults.WRONG_COLOR);
            }
        }
        if (hasGuessed(results)) {
            this.winner = Optional.of(this.getCurrentPlayer());
        }
        return Collections.unmodifiableList(results);
    }

    private boolean hasGuessed(List<SecretCodeResults> results) {
        return !results.contains(SecretCodeResults.CORRECT_COLOR)
                && !results.contains(SecretCodeResults.WRONG_COLOR);
    }

    private void nextPlayer() {
        this.actualPlayerIndex = (this.actualPlayerIndex + 1) % players.size();
        if (this.actualPlayerIndex == 0) {
            this.turn++;
        }
    }
}
