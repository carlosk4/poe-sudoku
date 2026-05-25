package com.example.poesudoku.controller;

import com.example.poesudoku.model.GameSessionResult;
import com.example.poesudoku.model.VictoryResult;
import com.example.poesudoku.view.ButtonHoverEffect;
import com.example.poesudoku.view.ButtonHoverable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VictoryController {

    @FXML private Label lblHintsUsed;
    @FXML private Label lblGrade;
    @FXML private Button btnMainMenu;
    @FXML private Button btnNewGame;

    private final ButtonHoverable buttonHoverEffect = new ButtonHoverEffect();

    @FXML
    public void initialize() {
        VictoryResult result = GameSessionResult.getCurrentResult();

        lblHintsUsed.setText("Pistas usadas: " + result.hintsUsed());
        lblGrade.setText(result.grade());

        buttonHoverEffect.applyButtonHoverEffect(btnMainMenu);
        buttonHoverEffect.applyButtonHoverEffect(btnNewGame);
    }

    @FXML
    protected void onMainMenu() {
        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        SceneNavigator.showMainMenu(stage);
    }

    @FXML
    protected void onNewGame() {
        Stage stage = (Stage) btnNewGame.getScene().getWindow();
        SceneNavigator.showSudokuGame(stage);
    }
}