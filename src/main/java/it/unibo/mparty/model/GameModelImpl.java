
package it.unibo.mparty.model;

import it.unibo.mparty.model.gameboard.api.GameBoard;
import it.unibo.mparty.model.gameboard.boards.BoardFactory;
import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.minigamehandler.MinigameHandler;
import it.unibo.mparty.model.minigamehandler.MinigameHandlerImplementation;
import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.shop.api.Shop;
import it.unibo.mparty.model.shop.impl.ShopImpl;
import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.GameStatus;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.RandomFromSet;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.SlotType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Random;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class provides an implementation for {@link GameModel} interface.
 */
public class GameModelImpl implements GameModel {

    private static final int TURNS_NUMBER = 15;
    private static final int MIN_COINS = 4;
    private static final int MAX_COINS = 10;
    private static final int STAR_COST = 20;
    private static final String MESSAGE_ROLL_DICE = " puoi usare un oggetto o tirare solo i dadi";
    private static final String MESSAGE_MOVE_PLAYER = " muovi la pedina";
    private static final String MESSAGE_MOVING_PLAYER = " muovi la pedina in una delle direzioni possibili:";
    private static final String MESSAGE_ACTIVE_SLOT = " attiva l'effetto dello slot: ";
    private static final String MESSAGE_END_TURN = " passa il turno";

    private final List<Player> players;
    private final GameBoard board;
    private final Shop shop;
    private boolean activateShop;
    private int turn = 1;
    private GameStatus status = GameStatus.ROLL_DICE;
    private int actualPlayerIndex;
    private int steps;
    private final MinigameHandler minigameHandler;
    private final Random random;

    /**
     * Constructor of the Game Model: creates a new istance of game with players.
     * insert and the selected difficulty for the board.
     *
     * @param players    of the game.
     * @param difficulty of the game. It's related to the {@link BoardType}.
     */
    public GameModelImpl(final List<Player> players, final String difficulty) {
        this.activateShop = false;
        this.players = List.copyOf(players);
        this.minigameHandler = new MinigameHandlerImplementation();
        this.shop = new ShopImpl();
        this.board = BoardFactory.createBoard(Arrays.stream(BoardType.values())
                .filter(b -> b.toString().equals(difficulty))
                .findAny().get());
        this.board.initialiseBoard();
        this.players.forEach(p -> p.setPosition(this.board.getStrartingPosition()));
        this.random = new Random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePlayer(final Optional<Direction> dir) {
        // Optional<Direction> currentDir = dir;
        if (this.status.equals(GameStatus.MOVE_PLAYER)
                || this.status.equals(GameStatus.MOVING_PLAYER)) {
            if (this.status.equals(GameStatus.MOVE_PLAYER)) {
                this.status = this.status.switchStatus();
            }
            final int diceResult = this.players.get(actualPlayerIndex).getDice().getResult();
            while (this.steps < diceResult) {
                this.checkStartAcquisition();
                final Position playerPos = this.players.get(actualPlayerIndex).getPosition();
                final Map<Direction, Position> nextPlayerPos = this.board.getNextPositions(playerPos);
                if (nextPlayerPos.size() == 1 && dir.isEmpty()) {
                    this.players.get(actualPlayerIndex).setPosition(nextPlayerPos.entrySet()
                            .stream()
                            .findFirst()
                            .get()
                            .getValue());
                } else {
                    if (dir.isEmpty() || nextPlayerPos.isEmpty()
                            || !nextPlayerPos.containsKey(dir.get())) {
                        return;
                    } else {
                        this.players.get(actualPlayerIndex).setPosition(nextPlayerPos.get(dir.get()));
                        // dir = Optional.empty();
                        this.steps++;
                        this.movePlayer(Optional.empty());
                        return;
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
        if (this.status == GameStatus.ROLL_DICE) {
            this.players.get(actualPlayerIndex).getDice().rollDice();
            this.status = this.status.switchStatus();
        }
        return this.players.get(actualPlayerIndex).getDice().getResult();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void useItem(final ItemName itemName) {
        final Item item = this.players.get(actualPlayerIndex).getPlayerBag().useItem(itemName);
        final Optional<Position> position = item.needPosition()
                ? Optional.of(this.board.getStarPosition())
                : Optional.empty();
        final Set<Player> targets = this.players.stream()
                .filter(p -> !p.equals(this.players.get(actualPlayerIndex)))
                .collect(Collectors.toSet());
        final Optional<Player> target = item.isOnOthers()
                ? Optional.of(RandomFromSet.get(targets))
                : Optional.empty();
        item.activate(this.players.get(actualPlayerIndex), target, position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean buyItem(final ItemName itemName) {
        return this.shop.buyItem(this.players.get(actualPlayerIndex), itemName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endMinigame(final Pair<String, Integer> result) {
        final Player winner = this.players.stream()
                .filter(p -> p.getUsername().equals(result.getFirst()))
                .findAny()
                .get();
        winner.addCoins(result.getSecond());
        this.minigameHandler.stopMinigame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.turn > TURNS_NUMBER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShop() {
        if (this.activateShop) {
            this.activateShop = false;
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> getActiveMinigame() {
        return this.minigameHandler.isInGame()
                ? Optional.of(this.minigameHandler.getMinigame())
                : Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        final StringBuilder stringBuilder = new StringBuilder(this.players.get(actualPlayerIndex).getUsername());
        switch (this.status) {
            case ROLL_DICE:
                stringBuilder.append(MESSAGE_ROLL_DICE);
                break;
            case MOVE_PLAYER:
                stringBuilder.append(MESSAGE_MOVE_PLAYER);
                break;
            case MOVING_PLAYER:
                stringBuilder.append(MESSAGE_MOVING_PLAYER);
                stringBuilder.append(this.getDirections());
                break;
            case ACTIVE_SLOT:
                stringBuilder.append(MESSAGE_ACTIVE_SLOT);
                stringBuilder.append(this.board.getSlotType(this.players.get(actualPlayerIndex).getPosition()));
                break;
            case END_TURN:
                stringBuilder.append(MESSAGE_END_TURN);
                break;
            default:
                break;
        }
        return stringBuilder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Position, SlotType> getBoardConfig() {
        return this.board.getSlotTypeBoardConfiguration();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getBoardDim() {
        return this.board.getDimension();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> getItemsFromShop() {
        return this.shop.getItemList().stream().toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getActualPlayer() {
        return this.players.get(this.actualPlayerIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getPlayersInGame() {
        return this.minigameHandler.getUsersPlaying();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Position, SlotType> getModifiedSlots() {
        return this.board.getModifiedSlots();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<String, String> getTurn() {
        return new Pair<>(String.valueOf(this.turn), String.valueOf(TURNS_NUMBER));
    }

    private void nextPlayer() {
        this.actualPlayerIndex = (this.actualPlayerIndex + 1) % players.size();
        if (this.actualPlayerIndex == 0) {
            this.turn++;
        }
    }

    private String getDirections() {
        final Map<Direction, Position> pos = this.board
                .getNextPositions(this.players.get(actualPlayerIndex).getPosition());
        String output = "";
        for (final Map.Entry<Direction, Position> entry : pos.entrySet()) {
            if (!output.isBlank()) {
                output = output.concat(",");
            }
            output = output.concat(" " + entry.getKey().toString());
        }
        output = output.concat(".");
        return output;
    }

    private void activateSlot() {
        final Player actualPlayer = this.players.get(actualPlayerIndex);
        final SlotType slot = this.board.getSlotType(actualPlayer.getPosition());
        if (this.status.equals(GameStatus.ACTIVE_SLOT)) {
            switch (slot) {
                case SINGLEPLAYER ->
                    this.minigameHandler.startMinigame(List.of(actualPlayer), MinigameType.SINGLE_PLAYER);
                case MULTIPLAYER -> {
                    final Player otherPlayer = this.players.stream()
                            .filter(p -> !p.equals(actualPlayer))
                            .findAny()
                            .get();
                    this.minigameHandler.startMinigame(List.of(actualPlayer, otherPlayer),
                            MinigameType.MULTI_PLAYER);
                }
                case BONUS -> actualPlayer.addCoins(this.random.nextInt(MIN_COINS, MAX_COINS));
                case MALUS -> actualPlayer.removeCoins(this.random.nextInt(MIN_COINS, MAX_COINS));
                case SHOP -> this.activeShop();
                case ACTIVE_STAR -> this.checkStartAcquisition();
                default -> {
                }
            }
        }
    }

    private void checkStartAcquisition() {
        final Player actualPlayer = this.players.get(actualPlayerIndex);
        final Position starPosition = this.board.getStarPosition();
        if (actualPlayer.getPosition().equals(starPosition)
                && actualPlayer.getNumCoins() >= STAR_COST) {
            actualPlayer.addStar();
            actualPlayer.removeCoins(STAR_COST);
            this.board.changeStarPosition();
        }
    }

    private void activeShop() {
        this.activateShop = true;
    }

}
