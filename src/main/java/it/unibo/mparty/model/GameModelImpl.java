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
import it.unibo.mparty.model.shop.api.Shop;
import it.unibo.mparty.model.shop.impl.ShopImpl;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
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
    public void movePlayer(Optional<Direction> dir) {
        if (this.status.equals(GameStatus.MOVE_PLAYER)) {
            while (this.steps < this.players.get(actualPlayerIndex).getDice().getResult()) {
                final Position actualPlayerPosition = this.players.get(actualPlayerIndex).getPosition();
                final Map<Direction, Position> nextPlayerPosition = this.board.getNextPositions(actualPlayerPosition);
                if (nextPlayerPosition.size() == 1 && dir.isEmpty()) {
                    this.players.get(actualPlayerIndex).setPosition(nextPlayerPosition.entrySet().stream().findFirst().get().getValue());
                } else {
                    if (dir.isEmpty() || nextPlayerPosition.size() < 1 || !nextPlayerPosition.containsKey(dir.get())) {
                        return;
                    } else {
                        this.players.get(actualPlayerIndex).setPosition(nextPlayerPosition.get(dir.get()));
                        dir = Optional.empty();
                    }
                }
                this.steps++;
            }
            this.steps = 0;
            this.status = this.status.switchStatus();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int rollDice() {
       if(this.status == GameStatus.ROLL_DICE){
           this.players.get(actualPlayerIndex).getDice().rollDice();
           this.status = this.status.switchStatus();
       }
        return this.players.get(actualPlayerIndex).getDice().getResult();
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
    public void action() {
        if (this.status.equals(GameStatus.ACTIVE_SLOT)) {
            this.activateSlot();
            this.status = this.status.switchStatus();
        } else if (this.status.equals(GameStatus.END_TURN)) {
            this.nextPlayer();
            this.status = this.status.switchStatus();
        }
    }

    private void activateSlot() {
        final Player actualPlayer = this.players.get(actualPlayerIndex);
        final SlotType slot = this.board.getSlotType(actualPlayer.getPosition());
        final Random random = new Random();
        if(this.status.equals(GameStatus.ACTIVE_SLOT)){
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

    /*
    private Set<Direction> getDirections() {
        if (this.status.equals(GameStatus.MOVE_PLAYER)) {
            Map<Direction,Position> pos = this.board.getNextPositions(this.players.get(actualPlayerIndex).getPosition());
            if (pos.size() > 1) {
                return pos.entrySet().stream().map(entry -> entry.getKey()).collect(Collectors.toSet());
            }
        }
        return Collections.emptySet();
    } */

    @Override
    public List<String> getPlayersNicknames() {
        List<String> output = new ArrayList<>();
        this.players.stream().forEach(p -> output.add(p.getUsername()));
        return Collections.unmodifiableList(output);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public Pair<String, Position> getActualPlayerInfo() {
        final Player pl = this.players.get(actualPlayerIndex);
        return new Pair<>(pl.getUsername(),pl.getPosition());
    }

    @Override
    public String getMessage() {
        String output = this.players.get(actualPlayerIndex).getUsername();
        switch (this.status) {
            case ROLL_DICE: output = output + " tira i dadi"; break;
            case MOVE_PLAYER: output = output + " muovi la pedina"; break;
            case ACTIVE_SLOT: output = output + " attiva l'effetto dello slot"; break;
            case END_TURN: output = output + " passa il turno"; break;
            default: break;
        }
        return output;
    }


}
