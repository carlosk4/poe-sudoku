package com.example.poesudoku.view;

/**
 * Handles selection of Sudoku cells.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public interface CellSelectionHandler {
    /**
     * Receives the selected cell coordinates.
     *
     * @param row selected row
     * @param col selected column
     */
    void onCellSelected(int row, int col);
}
