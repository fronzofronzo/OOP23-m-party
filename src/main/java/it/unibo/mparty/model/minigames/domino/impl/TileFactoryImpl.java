package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.model.minigames.domino.api.TileFactory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TileFactoryImpl implements TileFactory {

    @Override
    public Set<Tile> createDoubleSixSet() {
        return IntStream.rangeClosed(0, 6).boxed()
                .flatMap(i -> IntStream.rangeClosed(i, 6).mapToObj(j -> new TileImpl(i, j)))
                .collect(Collectors.toSet());
    }
}
