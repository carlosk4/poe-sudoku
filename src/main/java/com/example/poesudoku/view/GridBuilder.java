package com.example.poesudoku.view;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import static com.example.poesudoku.model.SudokuConstants.BLOCK_COLUMNS;
import static com.example.poesudoku.model.SudokuConstants.BLOCK_ROWS;
import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;
import static com.example.poesudoku.model.SudokuConstants.SIZE;

public class GridBuilder {

    private final Hoverable hoverEffect = new CellHoverEffect();

    public void build(GridPane grid, int[][] generatedBoard, boolean[][] fixedCells, CellChangeHandler cellChangeHandler) {
        grid.getChildren().clear();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                TextField cell = createCell(generatedBoard, fixedCells, row, col, cellChangeHandler);
                StackPane wrapper = createWrapper(cell, row, col);

                hoverEffect.applyHoverEffect(wrapper);
                grid.add(wrapper, col, row);
            }
        }
    }

    private TextField createCell(
            int[][] generatedBoard,
            boolean[][] fixedCells,
            int row,
            int col,
            CellChangeHandler cellChangeHandler
    ) {
        TextField cell = new TextField();
        cell.getStyleClass().add("cell");

        int value = generatedBoard[row][col];

        if (value != EMPTY_CELL) {
            cell.setText(String.valueOf(value));
        }

        if (fixedCells[row][col]) {
            cell.setEditable(false);
            cell.getStyleClass().add("cell-fixed");
        } else {
            configureEditableCell(cell, row, col, cellChangeHandler);
        }

        return cell;
    }

    private void configureEditableCell(TextField cell, int row, int col, CellChangeHandler cellChangeHandler) {
        cell.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[1-6]?")) {
                cell.setText(oldValue);
                return;
            }

            cellChangeHandler.onCellChanged(row, col, newValue);
        });
    }

    private StackPane createWrapper(TextField cell, int row, int col) {
        StackPane wrapper = new StackPane(cell);
        wrapper.getStyleClass().add("cell-wrapper");

        if (row == 0) {
            wrapper.getStyleClass().add("grid-top");
        }

        if (col == 0) {
            wrapper.getStyleClass().add("grid-left");
        }

        if (row == SIZE - 1) {
            wrapper.getStyleClass().add("grid-bottom");
        }

        if (col == SIZE - 1) {
            wrapper.getStyleClass().add("grid-right");
        }

        if (col == BLOCK_COLUMNS - 1) {
            wrapper.getStyleClass().add("block-right");
        }

        if (row == BLOCK_ROWS - 1 || row == (BLOCK_ROWS * 2) - 1) {
            wrapper.getStyleClass().add("block-bottom");
        }

        return wrapper;
    }
}