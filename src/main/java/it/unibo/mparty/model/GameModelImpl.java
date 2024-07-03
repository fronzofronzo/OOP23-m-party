package it.unibo.mparty.model;

import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.model.gameBoard.boards.SimpleBoardFactory;
import it.unibo.mparty.model.minigameHandler.MinigameHandler;
import it.unibo.mparty.model.minigameHandler.MinigameHandlerImplementation;
import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.shop.api.Shop;
import it.unibo.mparty.model.shop.impl.ShopImpl;
import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Models the core structure of the game
 */
public class GameModelImpl implements GameModel{

    private static final int TURNS_NUMBER = 10;
    private static final int MIN_COINS = 4;
    private static final int MAX_COINS = 10;

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
       this.shop = new ShopImpl();
       // deve essere modificato con la scelta della difficoltà della mappa
       final SimpleBoardFactory boardFactory = new SimpleBoardFactory();
       this.board = boardFactory.createBoard(BoardType.MEDIUM);
    }

    @Override
    public Position movePlayer() {
        final Position actualPlayerPosition = this.players.get(actualPlayerIndex).getPosition();
        //final Position nextPlayerPosition = this.board.getNextPositions(actualPlayerPosition).get(0).getY();

        return actualPlayerPosition;
    }

    @Override
    public int rollDice() {
        return this.players.get(actualPlayerIndex).getDice().generateNumber();
    }

    @Override
    public String getActiveMinigame() {
        return this.minigameHandler.getMinigame();
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

    @Override
    public void activateSlot() {
        final Player actualPlayer = this.players.get(actualPlayerIndex);
        final SlotType slot = this.board.getSlotType(actualPlayer.getPosition());
        final Random random = new Random();
        switch (slot) {
            case SINGLEPLAYER -> {
                try {
                    this.minigameHandler.startMinigame(List.of(actualPlayer), MinigameType.SINGLE_PLAYER);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            case MULTIPLAYER -> {
                try {
                    this.minigameHandler.startMinigame(List.of(actualPlayer), MinigameType.MULTI_PLAYER);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            case BONUS -> {
                actualPlayer.addCoins(random.nextInt(MIN_COINS, MAX_COINS));
            }
            case MALUS -> {
                actualPlayer.removeCoins(random.nextInt(MIN_COINS, MAX_COINS));
            }
            case ACTIVE_STAR -> {
            }
            default -> {break;}
        };

    }

    @Override
    public Map<Position, SlotType> getBoardConfiguration() {
        return this.board.getSlotTypeBoard();
    }

    @Override
    public Pair<Integer, Integer> getBoardDimensions() {
        return this.board.getDimension();
    }

    @Override
    public void endMinigame(Pair<String, Integer> result) {
        final Player winner = this.players.stream().filter(p -> p.getUsername().equals(result.getFirst())).findAny().get();
        winner.addCoins(result.getSecond());
        this.minigameHandler.stopMinigame();
    }
}
