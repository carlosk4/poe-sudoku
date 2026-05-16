package com.example.poesudoku.controller;

import com.example.poesudoku.model.GameManagerInterface;
import com.example.poesudoku.view.CellChangeHandler;
import com.example.poesudoku.view.GridBuilder;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SudokuViewRefresher {

    private final GridPane sudokuGrid;
    private final Button btnUndo;
    private final GridBuilder gridBuilder;

    public SudokuViewRefresher(GridPane sudokuGrid, Button btnUndo, GridBuilder gridBuilder) {
        this.sudokuGrid = sudokuGrid;
        this.btnUndo = btnUndo;
        this.gridBuilder = gridBuilder;
    }

    public void refresh(GameManagerInterface gameManager, CellChangeHandler cellChangeHandler) {
        gridBuilder.build(
                sudokuGrid,
                gameManager.getBoard(),
                gameManager.getFixedCells(),
                cellChangeHandler
        );

        refreshActions(gameManager);
    }

    public void refreshActions(GameManagerInterface gameManager) {
        btnUndo.setDisable(!gameManager.canUndo());
    }
}