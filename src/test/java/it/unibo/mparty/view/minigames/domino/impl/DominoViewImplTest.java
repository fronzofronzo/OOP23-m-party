package it.unibo.mparty.view.minigames.domino.impl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.awaitility.Durations;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import static org.awaitility.Awaitility.await;
import static org.testfx.assertions.api.Assertions.assertThat;

/**
 * Test class for {@link DominoViewImpl} class.
 */
@ExtendWith(ApplicationExtension.class)
class DominoViewImplTest extends ApplicationTest {

    private Parent root;

    @BeforeAll
    public static void setup() {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("java.awt.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("glass.platform", "Monocle");
        System.setProperty("monocle.platform", "Headless");
        WaitForAsyncUtils.checkAllExceptions = false;
        WaitForAsyncUtils.autoCheckException = false;
    }


    /**
     * Will be called with {@code @Before} semantics, i.e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/minigames/domino.fxml"));
        this.root = loader.load();
        final Scene scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Verifies that the draw button has the correct text.
     */
    @Test
    void drawButtonClicked() {
        final Button button = from(this.root).lookup("#drawButton").query();
        await().pollInterval(Durations.TWO_HUNDRED_MILLISECONDS).atMost(Durations.FIVE_SECONDS).untilAsserted(() -> {
            //FxAssert.verifyThat(button, hasText("Pesca Tessera"));
            assertThat(button).hasText("Pesca Tessera");
        });
    }

    /**
     * Verifies that the play button has the correct text.
     */
    @Test
    void playButtonClicked() {
        final Button button = from(this.root).lookup("#playButton").query();
        await().pollInterval(Durations.TWO_HUNDRED_MILLISECONDS).atMost(Durations.FIVE_SECONDS).untilAsserted(() -> {
            //FxAssert.verifyThat(button, hasText("Gioca Tessera"));
            assertThat(button).hasText("Gioca Tessera");
        });
    }

    /**
     * Verifies that the tutorial button has the correct text.
     */
    @Test
    void tutorialClicked() {
        final Button button = from(this.root).lookup("#tutorialButton").query();
        await().pollInterval(Durations.TWO_HUNDRED_MILLISECONDS).atMost(Durations.FIVE_SECONDS).untilAsserted(() -> {
            //FxAssert.verifyThat(button, hasText("Tutorial"));
            assertThat(button).hasText("Tutorial");
        });
    }

    /**
     * Verifies that the draw button is disabled.
     */
    @Test
    void playerCanDraw() {
        final Button button = from(this.root).lookup("#drawButton").query();
        await().pollInterval(Durations.TWO_HUNDRED_MILLISECONDS).atMost(Durations.FIVE_SECONDS).untilAsserted(() -> {
            FxAssert.verifyThat(button, Node::isDisabled);
        });
    }
}
