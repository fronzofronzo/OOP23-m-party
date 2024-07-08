package it.unibo.mparty.model.minigames.secretCode;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.minigames.secretCode.api.SecretCodeModel;
import it.unibo.mparty.model.minigames.secretCode.impl.SecretCodeModelImpl;

public class TestSecretCode {

    private static SecretCodeModel model = new SecretCodeModelImpl();

    @BeforeEach
    public static void initialise() {
        List<String> players = List.of("P1","P2");
        model.setUpPlayers(players);
    }

    @Test
    public void Test() {
        //model.getGame().get
    }
    
}