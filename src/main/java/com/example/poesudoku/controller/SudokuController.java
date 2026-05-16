package com.example.poesudoku.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import com.example.poesudoku.view.ButtonHoverable;
import com.example.poesudoku.view.ButtonHoverEffect;
import com.example.poesudoku.view.GridBuilder;
import com.example.poesudoku.model.GameManagerInterface;
import com.example.poesudoku.model.GameManager;

public class SudokuController {

    @FXML private GridPane sudokuGrid;
    @FXML private Button btnUndo;
    @FXML private Button btnNewGame;
    @FXML private Button btnHint;
    @FXML private Button btnRestart;

    private final GameManagerInterface gameManager = new GameManager();
    private final GridBuilder gridBuilder = new GridBuilder();
    private final ButtonHoverable buttonHoverEffect = new ButtonHoverEffect();

    @FXML
    public void initialize() {
        startNewGame();
        buttonHoverEffect.applyButtonHoverEffect(btnUndo);
        buttonHoverEffect.applyButtonHoverEffect(btnNewGame);
        buttonHoverEffect.applyButtonHoverEffect(btnHint);
        buttonHoverEffect.applyButtonHoverEffect(btnRestart);
    }

    private void startNewGame() {
        gameManager.startNewGame();
        gridBuilder.build(sudokuGrid, gameManager.getBoard(), gameManager.getFixedCells());
    }

    @FXML
    protected void onUndo() {
        gameManager.undo();
        gridBuilder.build(sudokuGrid, gameManager.getBoard(), gameManager.getFixedCells());
    }

    @FXML
    protected void onNewGame() {
        startNewGame();
    }

    @FXML
    protected void onHint() {

    }

    @FXML
    protected void onRestart() {
        startNewGame();
    }

}