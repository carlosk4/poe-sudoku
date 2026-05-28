package com.example.poesudoku.model;

/**
 * Defines core Sudoku board operations.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public interface SudokuBoardInterface {
    /**
     * Returns the value at a board cell.
     *
     * @param row target row
     * @param col target column
     * @return cell value
     */
    int getCell(int row, int col);

    /**
     * Sets the value at a board cell.
     *
     * @param row target row
     * @param col target column
     * @param value target value
     */
    void setCell(int row, int col, int value);

    /**
     * Checks whether a value follows Sudoku rules at a cell.
     *
     * @param row target row
     * @param col target column
     * @param value target value
     * @return true when the value is valid
     */
    boolean isValid(int row, int col, int value);

    /**
     * Checks whether the board is solved.
     *
     * @return true when the board is solved
     */
    boolean isSolved();

    /**
     * Clears the board.
     */
    void reset();

    /**
     * Returns a copy of the board.
     *
     * @return copied board
     */
    int[][] getBoard();
}
