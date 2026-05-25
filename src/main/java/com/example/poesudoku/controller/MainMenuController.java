package com.example.poesudoku.controller;

import com.example.poesudoku.view.ButtonHoverEffect;
import com.example.poesudoku.view.ButtonHoverable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML private Button btnStartGame;

    private final ButtonHoverable buttonHoverEffect = new ButtonHoverEffect();

    @FXML
    public void initialize() {
        buttonHoverEffect.applyButtonHoverEffect(btnStartGame);
    }

    @FXML
    protected void onStartGame() {
        Stage stage = (Stage) btnStartGame.getScene().getWindow();
        SceneNavigator.showSudokuGame(stage);
    }
}
