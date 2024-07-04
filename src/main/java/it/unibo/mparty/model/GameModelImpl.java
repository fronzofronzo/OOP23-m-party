package it.unibo.mparty.model;

import it.unibo.mparty.model.gameBoard.api.GameBoard;
import it.unibo.mparty.model.gameBoard.boards.SimpleBoardFactory;
import it.unibo.mparty.model.minigameHandler.MinigameHandler;
import it.unibo.mparty.model.minigameHandler.MinigameHandlerImplementation;
import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.shop.api.Shop;
import it.unibo.mparty.model.shop.impl.ShopImpl;
import it.unibo.mparty.utilities.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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
    private GameStatus status = GameStatus.ROLL_DICE;
    private int actualPlayerIndex = 0;
    private int steps = 0;
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
       final SimpleBoardFactory boardFactory = new SimpleBoardFactory();
       this.board = boardFactory.createBoard(BoardType.valueOf(difficulty));
       this.players.forEach(p -> p.setPosition(this.board.getStrartingPosition()));
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean movePlayer() {
        while (this.steps < this.players.get(actualPlayerIndex).getDice().getResult()) {
            final Position actualPlayerPosition = this.players.get(actualPlayerIndex).getPosition();
            final Map<Direction, Position> nextPlayerPosition = this.board.getNextPositions(actualPlayerPosition);
            if (nextPlayerPosition.size() == 1) {
                this.players.get(actualPlayerIndex).setPosition(nextPlayerPosition.entrySet().stream().findFirst().get().getValue());
            } else {
                return false;
            }
            this.steps++;
        }
        this.steps = 0;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int rollDice() {
       if(this.status == GameStatus.ROLL_DICE){
           this.players.get(actualPlayerIndex).getDice().rollDice();
           this.switchStatus();
           return this.players.get(actualPlayerIndex).getDice().getResult();
       } else {
           return 0;
       }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> getActiveMinigame() {
        if(this.minigameHandler.isInGame()){
            return Optional.of(this.minigameHandler.getMinigame());
        } else {
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextPlayer() {
        this.actualPlayerIndex = (this.actualPlayerIndex + 1) % players.size();
        if(this.actualPlayerIndex == 0){
            this.turn++;
        }
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return turn == TURNS_NUMBER;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Position, SlotType> getBoardConfiguration() {
        return this.board.getSlotTypeBoard();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getBoardDimensions() {
        return this.board.getDimension();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endMinigame(Pair<String, Integer> result) {
        final Player winner = this.players.stream().filter(p -> p.getUsername().equals(result.getX())).findAny().get();
        winner.addCoins(result.getY());
        this.minigameHandler.stopMinigame();
    }

    @Override
    public Set<Direction> getDirections() {
        Map<Direction,Position> pos = this.board.getNextPositions(this.players.get(actualPlayerIndex).getPosition());
        return pos.entrySet().stream().map(entry -> entry.getKey()).collect(Collectors.toSet());
    }

    @Override
    public void movePlayerWithDirection(Direction dir) {
        Map<Direction,Position> p = this.board.getNextPositions(this.players.get(actualPlayerIndex).getPosition());
        this.players.get(actualPlayerIndex).setPosition(p.get(dir));
        this.steps++;
    }

    @Override
    public List<String> getPlayersNicknames() {
        List<String> output = new ArrayList<>();
        this.players.stream().forEach(p -> output.add(p.getUsername()));
        return Collections.unmodifiableList(output);
    }

    @Override
    public Pair<String, Position> getActualPlayerInfo() {
        final Player pl = this.players.get(actualPlayerIndex);
        return new Pair<>(pl.getUsername(),pl.getPosition());
    }

    private void switchStatus(){
        switch (this.status) {
            case ROLL_DICE -> {
                this.status = GameStatus.MOVE_PLAYER;
            }
            case MOVE_PLAYER -> {
                this.status = GameStatus.ROLL_DICE;
            }
        };

    }

}
