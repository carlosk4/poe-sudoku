package com.example.poesudoku.view;

/**
 * Handles changes in editable Sudoku cells.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
@FunctionalInterface
public interface CellChangeHandler {
    /**
     * Receives a changed cell value.
     *
     * @param row changed row
     * @param col changed column
     * @param value entered value
     */
    void onCellChanged(int row, int col, String value);
}
