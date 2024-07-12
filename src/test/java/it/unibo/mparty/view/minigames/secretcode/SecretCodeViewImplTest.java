package it.unibo.mparty.view.minigames.secretcode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This class test {@link SecreteCodeViewImpl}.
 */
@ExtendWith(ApplicationExtension.class)
class SecreteCodeViewImplTest extends ApplicationTest {

    private static final int COLUMNS = 4;
    private static final int ROWS = 8;

    private Parent root;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/minigames/secretCode.fxml"));
        this.root = loader.load();
        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Test that the initial set up is correct.
     */
    @Test
    void testInitialSetup() {
        this.checkButtons();
        this.checkGridPanes();
    }

    private void checkGridPanes() {
        final GridPane gridPaneGuessP1 = from(this.root).lookup("#gridPaneGuessP1").query();
        assertEquals(COLUMNS, gridPaneGuessP1.getColumnCount());
        assertEquals(ROWS, gridPaneGuessP1.getRowCount());
        final GridPane gridPaneResP1 = from(this.root).lookup("#gridPaneResP1").query();
        assertEquals(COLUMNS, gridPaneResP1.getColumnCount());
        assertEquals(ROWS, gridPaneResP1.getRowCount());
        final GridPane gridPaneGuessP2 = from(this.root).lookup("#gridPaneGuessP2").query();
        assertEquals(COLUMNS, gridPaneGuessP2.getColumnCount());
        assertEquals(ROWS, gridPaneGuessP2.getRowCount());
        final GridPane gridPaneResP2 = from(this.root).lookup("#gridPaneResP2").query();
        assertEquals(COLUMNS, gridPaneResP2.getColumnCount());
        assertEquals(ROWS, gridPaneResP2.getRowCount());
    }

    private void checkButtons() {
        final Button buttonBackToBoard = from(this.root).lookup("#buttonBackToBoard").query();
        verifyThat(buttonBackToBoard, hasText("TORNA ALLA SCHERMATA PRINCIPALE"));
        final Button buttonGreen = from(this.root).lookup("#buttonGreen").query();
        verifyThat(buttonGreen, hasText("VERDE"));
        final Button buttonPurple = from(this.root).lookup("#buttonPurple").query();
        verifyThat(buttonPurple, hasText("VIOLA"));
        final Button buttonBlu = from(this.root).lookup("#buttonBlu").query();
        verifyThat(buttonBlu, hasText("BLU"));
        final Button buttonPink = from(this.root).lookup("#buttonPink").query();
        verifyThat(buttonPink, hasText("ROSA"));
        final Button buttonRed = from(this.root).lookup("#buttonRed").query();
        verifyThat(buttonRed, hasText("ROSSO"));
        final Button buttonOrange = from(this.root).lookup("#buttonOrange").query();
        verifyThat(buttonOrange, hasText("ARANCIONE"));
        final Button buttonGuess = from(this.root).lookup("#buttonGuess").query();
        verifyThat(buttonGuess, hasText("INDOVINA"));
    }
}
