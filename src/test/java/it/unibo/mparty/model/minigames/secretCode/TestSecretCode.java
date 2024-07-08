package it.unibo.mparty.model.minigames.secretCode;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;

import it.unibo.mparty.model.minigames.secretCode.api.SecretCodeModel;
import it.unibo.mparty.model.minigames.secretCode.impl.SecretCodeModelImpl;

public class TestSecretCode {

    private static SecretCodeModel model = new SecretCodeModelImpl();

    @BeforeAll
    public static void initialise() {
        List<String> players = List.of("P1","P2");
        model.setUpPlayers(players);
    }

    
}