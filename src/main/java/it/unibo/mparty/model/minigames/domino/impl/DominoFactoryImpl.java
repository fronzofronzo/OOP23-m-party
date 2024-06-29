package it.unibo.mparty.model.minigames.domino.impl;

import it.unibo.mparty.model.minigames.domino.api.Domino;
import it.unibo.mparty.model.minigames.domino.api.DominoFactory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DominoFactoryImpl implements DominoFactory {

    @Override
    public Set<Domino> createFullSet() {
        return IntStream.rangeClosed(0, 6).boxed()
                .flatMap(i -> IntStream.rangeClosed(i, 6).mapToObj(j -> new DominoImpl(i, j)))
                .collect(Collectors.toSet());
    }
}
