package it.unibo.mparty.model;

import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.model.gameBoard.boards.SimpleBoardFactory;
import it.unibo.mparty.model.gameBoard.util.BoardType;
import it.unibo.mparty.model.minigameHandler.MinigameHandler;
import it.unibo.mparty.model.minigameHandler.MinigameHandlerImplementation;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Position;

import java.util.List;

/**
 * Models the core structure of the game
 */
public class GameModelImpl implements GameModel{

    private static final int TURNS_NUMBER = 10;

    private final List<Player> players;
    private final GameBoard board;
    private final Shop shop;
    private int turn = 1 ;
    private int actualPlayerIndex = 0;
    private final MinigameHandler minigameHandler;

    /**
     * Constructor of the Game Model: creates a new istance of game with players
     * insert and the selected difficulty for the board
     * @param players of the game
     * @param difficulty of the game. It's related to the {@link BoardType}
     */
    public GameModelImpl(List<Player> players, String difficulty){
       this.players = players;
       this.minigameHandler = new MinigameHandlerImplementation();
       // deve essere modificato con la scelta della difficoltà della mappa
       final SimpleBoardFactory boardFactory = new SimpleBoardFactory();
       this.board = boardFactory.createBoard(BoardType.MEDIUM);
    }

    @Override
    public void movePlayer() {
        final Position actualPlayerPosition = this.players.get(actualPlayerIndex).getPosition();
        final Position nextPlayerPosition = this.board.getNextPositions(actualPlayerPosition).get(0).getY();

    }

    @Override
    public int rollDice() {
        return this.players.get(actualPlayerIndex).getDice().generateNumber();
    }

    @Override
    public String getActiveMinigame() {
        return "";
    }

    @Override
    public boolean isActiveMinigame() {
        return this.minigameHandler.getMinigame() != null;
    }

    @Override
    public void nextPlayer() {
        this.actualPlayerIndex = (this.actualPlayerIndex + 1) % players.size();
        if(this.actualPlayerIndex == 0){
            this.turn++;
        }
    }

    @Override
    public String getWinner() {
        final int maxStars = players.stream().map(Player::getNumStars).sorted().limit(1).reduce(0 , Integer::sum);
        List<Player> winners = players.stream().filter(p -> p.getNumStars() == maxStars).toList();
        if (winners.size() != 1){
            final int maxMoney = winners.stream().map(Player::getNumCoins).sorted().limit(1).reduce(0 , Integer::sum);
            winners = winners.stream().filter(p -> p.getNumCoins() == maxMoney).toList();
        }
        return winners.get(0).getUsername();
    }

    @Override
    public boolean isOver() {
        return turn == TURNS_NUMBER;
    }
}
