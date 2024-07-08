package it.unibo.mparty.model.minigames.domino.tile.impl;

import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.model.minigames.domino.tile.api.TileFactory;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implementation of the {@link TileFactory} interface. Creates sets of domino tiles.
 */
public class TileFactoryImpl implements TileFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tile> createDoubleSixSet() {
        List<Tile> tiles = IntStream.rangeClosed(0, 6).boxed()
                .flatMap(i -> IntStream.rangeClosed(i, 6).mapToObj(j -> new TileImpl(i, j)))
                .collect(Collectors.toList());
        Collections.shuffle(tiles);
        return new LinkedList<>(tiles);
    }
}
