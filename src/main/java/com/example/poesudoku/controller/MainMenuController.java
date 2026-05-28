package com.example.poesudoku.controller;

import com.example.poesudoku.view.ButtonHoverEffect;
import com.example.poesudoku.view.ButtonHoverable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controls the main menu view.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public class MainMenuController {

    @FXML private Button btnStartGame;

    private final ButtonHoverable buttonHoverEffect = new ButtonHoverEffect();

    /**
     * Initializes button effects for the main menu.
     */
    @FXML
    public void initialize() {
        buttonHoverEffect.applyButtonHoverEffect(btnStartGame);
    }

    /**
     * Opens the Sudoku game view.
     */
    @FXML
    protected void onStartGame() {
        Stage stage = (Stage) btnStartGame.getScene().getWindow();
        SceneNavigator.showSudokuGame(stage);
    }
}
