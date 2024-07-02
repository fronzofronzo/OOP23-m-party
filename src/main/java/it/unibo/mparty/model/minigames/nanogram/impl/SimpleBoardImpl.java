package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.SimpleBoard;
import it.unibo.mparty.utilities.Position;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class SimpleBoardImpl extends BoardImpl implements SimpleBoard {

    private final int size;

    public SimpleBoardImpl(final int size, final double fillPercentage) {
        super(size);
        Random random = new Random();
        this.size = size;

        List<Position> position = new ArrayList<>(IntStream.range(0, size * size)
                .mapToObj(i -> new Position(i / size, i % size))
                .toList());

        position.forEach(p -> this.board.put(p, false));

        Collections.shuffle(position);

        position.stream().limit(Math.round(size * size * fillPercentage)).forEach(p -> this.board.replace(p, true));
    }

    @Override
    public boolean getState(Position position){
        return this.board.get(position);
    }

    @Override
    public List<List<Integer>> generateHints(boolean isRow){
        final List<List<Integer>> hintsList = new ArrayList<>();

        for (int i = 0; i < this.size; i++) {
            final List<Integer> hints = new ArrayList<>();
            int count = 0;

            for (int j = 0; j < this.size; j++) {
                final Position pos = isRow ? new Position(i, j) : new Position(j, i);
                final Boolean currentState = this.board.get(pos);

                if (currentState) {
                    count++;
                } else if (count > 0) {
                    hints.add(count);
                    count = 0;
                }
            }

            if (count > 0) {
                hints.add(count);
            } else if (hints.isEmpty()) {
                hints.add(0);
            }

            hintsList.add(hints);
        }
        return hintsList;
    }
}
