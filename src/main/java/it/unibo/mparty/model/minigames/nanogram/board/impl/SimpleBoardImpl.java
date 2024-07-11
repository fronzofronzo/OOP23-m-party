package it.unibo.mparty.model.minigames.nanogram.board.impl;

import it.unibo.mparty.model.minigames.nanogram.board.api.SimpleBoard;
import it.unibo.mparty.utilities.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Implementation of a simple board for a Nanogram game.
 * Extends {@link BoardImpl} and implements {@link SimpleBoard}.
 */
public class SimpleBoardImpl extends BoardImpl implements SimpleBoard {

    private final int size;

    /**
     * Constructs a SimpleBoardImpl object with the specified size and fill percentage.
     *
     * @param size           the size of the board (size x size).
     * @param fillPercentage the percentage of cells to be initially filled (true).
     */
    public SimpleBoardImpl(final int size, final double fillPercentage) {
        super(size);
        this.size = size;

        final List<Position> position = new ArrayList<>(IntStream.range(0, size * size)
                .mapToObj(i -> new Position(i / size, i % size))
                .toList());

        position.forEach(p -> getBoard().put(p, false));

        Collections.shuffle(position);

        position.stream().limit(Math.round(size * size * fillPercentage)).forEach(p -> getBoard().replace(p, true));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getState(final Position position) {
        return getBoard().get(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<Integer>> generateHints(final boolean isRow) {
        final List<List<Integer>> hintsList = new ArrayList<>();

        for (int i = 0; i < this.size; i++) {
            final List<Integer> hints = new ArrayList<>();
            int count = 0;

            for (int j = 0; j < this.size; j++) {
                final Position pos = isRow ? new Position(i, j) : new Position(j, i);
                final boolean currentState = getBoard().get(pos);

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
