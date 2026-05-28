package com.example.poesudoku.controller;

import com.example.poesudoku.model.GameManagerInterface;
import com.example.poesudoku.view.CellChangeHandler;
import com.example.poesudoku.view.CellSelectionHandler;
import com.example.poesudoku.view.GridBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Refreshes Sudoku view components from game state.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public class SudokuViewRefresher {

    private final GridPane sudokuGrid;
    private final Button btnUndo;
    private final Label lblMessage;
    private final GridBuilder gridBuilder;

    /**
     * Creates a view refresher.
     *
     * @param sudokuGrid sudoku grid pane
     * @param btnUndo undo button
     * @param lblMessage message label
     * @param gridBuilder grid builder
     */
    public SudokuViewRefresher(GridPane sudokuGrid, Button btnUndo, Label lblMessage, GridBuilder gridBuilder) {
        this.sudokuGrid = sudokuGrid;
        this.btnUndo = btnUndo;
        this.lblMessage = lblMessage;
        this.gridBuilder = gridBuilder;
    }

    /**
     * Rebuilds the grid and updates controls.
     *
     * @param gameManager game state manager
     * @param invalidCells invalid cell flags
     * @param hintCell hinted cell data
     * @param selectedCell selected cell data
     * @param message status message
     * @param cellChangeHandler cell change callback
     * @param cellSelectionHandler cell selection callback
     */
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

    /**
     * Updates available action controls.
     *
     * @param gameManager game state manager
     */
    public void refreshActions(GameManagerInterface gameManager) {
        btnUndo.setDisable(!gameManager.canUndo());
    }
}
