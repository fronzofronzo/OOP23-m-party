package it.unibo.mparty.model;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link GameModelBuilderImpl} class
 */
class GameModelBuilderImplTest {

    private GameModelBuilder builder;

    @BeforeEach
    public void init(){
        this.builder = new GameModelBuilderImpl();
    }

}