package com.example.poesudoku.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.example.poesudoku.view.ButtonHoverable;
import com.example.poesudoku.view.ButtonHoverEffect;
import com.example.poesudoku.view.GridBuilder;
import com.example.poesudoku.model.GameManagerInterface;
import com.example.poesudoku.model.GameManager;

/**
 * Controls the Sudoku game view.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public class SudokuController {

    @FXML private GridPane sudokuGrid;
    @FXML private Button btnUndo;
    @FXML private Button btnNewGame;
    @FXML private Button btnHint;
    @FXML private Button btnRestart;
    @FXML private Label lblMessage;

    private final GameManagerInterface gameManager = new GameManager();
    private final GridBuilder gridBuilder = new GridBuilder();
    private final ButtonHoverable buttonHoverEffect = new ButtonHoverEffect();

    private SudokuGamePresenter presenter;

    /**
     * Initializes game services, visual effects, and the first game.
     */
    @FXML
    public void initialize() {
        SudokuViewRefresher viewRefresher = new SudokuViewRefresher(
                sudokuGrid,
                btnUndo,
                lblMessage,
                gridBuilder
        );
        presenter = new SudokuGamePresenter(
                gameManager,
                viewRefresher,
                this::showVictoryScreen
        );

        applyButtonEffects();
        presenter.startNewGame();
    }

    /**
     * Applies hover effects to game action buttons.
     */
    private void applyButtonEffects() {
        buttonHoverEffect.applyButtonHoverEffect(btnUndo);
        buttonHoverEffect.applyButtonHoverEffect(btnNewGame);
        buttonHoverEffect.applyButtonHoverEffect(btnHint);
        buttonHoverEffect.applyButtonHoverEffect(btnRestart);
    }

    /**
     * Handles the undo action.
     */
    @FXML
    protected void onUndo() {
        presenter.undo();
    }

    /**
     * Returns to the main menu.
     */
    @FXML
    protected void onNewGame() {
        Stage stage = (Stage) btnNewGame.getScene().getWindow();
        SceneNavigator.showMainMenu(stage);
    }

    /**
     * Handles the hint action.
     */
    @FXML
    protected void onHint() {
        presenter.showHint();
    }

    /**
     * Restarts the current game.
     */
    @FXML
    protected void onRestart() {
        presenter.restart();
    }

    /**
     * Opens the victory screen.
     */
    private void showVictoryScreen() {
        Stage stage = (Stage) btnNewGame.getScene().getWindow();
        SceneNavigator.showVictory(stage);
    }
}
