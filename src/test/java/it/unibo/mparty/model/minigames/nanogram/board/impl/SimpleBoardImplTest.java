package it.unibo.mparty.model.minigames.nanogram.board.impl;

import it.unibo.mparty.model.minigames.nanogram.board.api.SimpleBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBoardImplTest {

    private static final int SIZE = 5;
    private static final int NUM_TEST = 20;
    private static final double PROBABILITY = 0.6;
    private SimpleBoard simpleBoard;
    private Random random = new Random();

    @BeforeEach
    void init() {

    }

    @Test
    public void testSimpleBoard(){
        random.doubles(NUM_TEST).forEach(p -> {
            this.simpleBoard = new SimpleBoardImpl(SIZE, p);
            assertEquals(Math.round(SIZE * SIZE * p), IntStream.range(0, SIZE * SIZE).mapToObj(i -> simpleBoard.getCell(i / SIZE, i % SIZE)).filter(pos -> pos).count());

        });
    }

    @Test
    public void testGetHint() {
        this.simpleBoard = new SimpleBoardImpl(5, PROBABILITY);;
        this.simpleBoard.getHints(true);
    }
}