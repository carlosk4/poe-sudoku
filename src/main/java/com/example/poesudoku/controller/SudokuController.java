package com.example.poesudoku.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import com.example.poesudoku.view.ButtonHoverable;
import com.example.poesudoku.view.ButtonHoverEffect;
import javafx.scene.control.Button;
import com.example.poesudoku.view.GridBuilder;

public class SudokuController {

    @FXML private GridPane sudokuGrid;
    @FXML private Button btnNewGame;
    @FXML private Button btnHint;
    @FXML private Button btnRestart;

    private final ButtonHoverable buttonHoverEffect = new ButtonHoverEffect();

    @FXML
    public void initialize() {
        gridBuilder.build(sudokuGrid);
        buttonHoverEffect.applyButtonHoverEffect(btnNewGame);
        buttonHoverEffect.applyButtonHoverEffect(btnHint);
        buttonHoverEffect.applyButtonHoverEffect(btnRestart);
    }

    private final GridBuilder gridBuilder = new GridBuilder();

    @FXML
    protected void onNewGame() {
        sudokuGrid.getChildren().clear();
        gridBuilder.build(sudokuGrid);
    }

    @FXML
    protected void onHint() {

    }

    @FXML
    protected void onRestart() {
        sudokuGrid.getChildren().clear();
        gridBuilder.build(sudokuGrid);
    }

}