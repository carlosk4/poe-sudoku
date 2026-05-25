package com.example.poesudoku.controller;

import com.example.poesudoku.model.GameManagerInterface;
import com.example.poesudoku.view.CellChangeHandler;
import com.example.poesudoku.view.CellSelectionHandler;
import com.example.poesudoku.view.GridBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class SudokuViewRefresher {

    private final GridPane sudokuGrid;
    private final Button btnUndo;
    private final Label lblMessage;
    private final GridBuilder gridBuilder;

    public SudokuViewRefresher(GridPane sudokuGrid, Button btnUndo, Label lblMessage, GridBuilder gridBuilder) {
        this.sudokuGrid = sudokuGrid;
        this.btnUndo = btnUndo;
        this.lblMessage = lblMessage;
        this.gridBuilder = gridBuilder;
    }

    public void refresh(
            GameManagerInterface gameManager,
            boolean[][] invalidCells,
            int[] hintCell,
            int[] selectedCell,
            String message,
            CellChangeHandler cellChangeHandler,
            CellSelectionHandler cellSelectionHandler
    ) {
        gridBuilder.build(
                sudokuGrid,
                gameManager.getBoard(),
                gameManager.getFixedCells(),
                invalidCells,
                hintCell,
                selectedCell,
                cellChangeHandler,
                cellSelectionHandler
        );

        lblMessage.setText(message);
        refreshActions(gameManager);
    }

    public void refreshActions(GameManagerInterface gameManager) {
        btnUndo.setDisable(!gameManager.canUndo());
    }
}