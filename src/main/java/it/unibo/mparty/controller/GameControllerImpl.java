package it.unibo.mparty.controller;

import java.io.IOException;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.GameView;
import it.unibo.mparty.view.shop.api.ShopView;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implements the {@link GameController} interface. This class
 * provides an implementation for interface methods and handles the different
 * situation with view and model of the game.
 */
public class GameControllerImpl implements GameController {

    private GameView view;
    private GameModel model;

    /**
     * Constructor for a new {@link GameController} implementation.
     *
     * @param view to set like {@link GameView} reference to the game.
     */
    public GameControllerImpl(final GameView view) {
        this.setView(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame(final GameModel model) throws IOException {
        this.model = model;
        final List<String> usernames = this.model.getPlayers()
                .stream()
                .map(Player::getUsername)
                .toList();
        this.view.setUpBoard(this.model.getBoardDim(), this.model.getBoardConfig(), usernames);
        this.view.setBoardScene();
        this.updatePlayersView();
        this.updateCommandView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void useItem(final String item) {
        this.model.useItem(Arrays.stream(ItemName.values())
                .filter(i -> i.toString().equals(item))
                .findAny()
                .get());
        this.updateCommandView();
        this.updatePlayersView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rollDice() {
        this.view.showResultDice(this.model.rollDice());
        this.updateCommandView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePlayer(final Optional<Direction> dir) {
        this.model.movePlayer(dir);
        this.updateCommandView();
        this.updatePlayersView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action() throws IOException {
        this.model.action();
        if (this.model.getActiveMinigame().isPresent()) {
            this.view.setMinigameScene(this.model.getActiveMinigame().get(), this.model.getPlayersInGame());
        } else if (this.model.isShop()) {
            this.view.setShopScene();
        }
        this.updateCommandView();
        this.updatePlayersView();
        this.view.updateBoard(this.model.getModifiedSlots());
        this.checkEndGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpShop(final ShopView shopView) {
        final Map<ItemName, Pair<Integer, String>> itemMap = new LinkedHashMap<>();
        this.model.getItemsFromShop().forEach(it -> itemMap.put(it.getName(),
                new Pair<>(it.getCost(), it.getDescription())));
        itemMap.forEach((it, p) -> shopView.addItemView(it, p.getFirst(), p.getSecond()));
        this.updateCommandView();
        shopView.updateMoney(this.model.getActualPlayer().getNumCoins());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void buyItem(final ItemName itemName, final ShopView shopView) {
        if (this.model.buyItem(itemName)) {
            shopView.updateMoney(this.model.getActualPlayer().getNumCoins());
            this.updateCommandView();
            this.updatePlayersView();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveMinigameResult(final Pair<String, Integer> result) {
        this.model.endMinigame(result);
        this.updatePlayersView();
    }

    private void checkEndGame() throws IOException {
        if (this.model.isOver()) {
            final List<Player> players = this.model.getPlayers();
            final Map<String, Pair<Integer, Integer>> result = players.stream()
                    .sorted(Comparator
                            .comparingInt(Player::getNumStars)
                            .thenComparingInt(Player::getNumCoins)
                            .reversed())
                    .collect(Collectors.toMap(
                            Player::getUsername,
                            p -> new Pair<>(p.getNumStars(), p.getNumCoins()),
                            (e1, e2) -> e1,
                            LinkedHashMap::new
                    ));
            this.view.showResults(result);
        }
    }

    private void updatePlayersView() {
        final List<Player> players = this.model.getPlayers();
        players.forEach(p -> this.view.updatePlayer(p.getUsername(),
                p.getNumCoins(),
                p.getNumStars(),
                p.getPlayerBag()
                        .getItems()
                        .stream()
                        .map(Enum::name).toList(),
                p.getPosition()));
    }

    private void updateCommandView() {
        this.view.updateCommands(this.model.getActualPlayer()
                        .getPlayerBag()
                        .getItems()
                        .stream()
                        .map(ItemName::toString)
                        .toList(),
                this.model.getMessage(),
                this.model.getTurn());
    }

    private void setView(final GameView view) {
        this.view = view;
    }
}
