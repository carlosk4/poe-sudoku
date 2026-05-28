package com.example.poesudoku.model;

/**
 * Defines operations for managing Sudoku game state.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public interface GameManagerInterface {
    /**
     * Starts a new game.
     */
    void startNewGame();

    /**
     * Saves the current board state.
     */
    void saveState();

    /**
     * Restores the previous board state.
     */
    void undo();

    /**
     * Checks whether undo is available.
     *
     * @return true when a previous state exists
     */
    boolean canUndo();

    /**
     * Sets a cell value.
     *
     * @param row target row
     * @param col target column
     * @param value target value
     * @return true when the value can be set
     */
    boolean setCellValue(int row, int col, int value);

    /**
     * Checks whether a cell is fixed.
     *
     * @param row target row
     * @param col target column
     * @return true when the cell is fixed
     */
    boolean isFixedCell(int row, int col);

    /**
     * Checks whether a cell is marked invalid.
     *
     * @param row target row
     * @param col target column
     * @return true when the cell is invalid
     */
    boolean isCellValid(int row, int col);

    /**
     * Checks whether the puzzle is solved.
     *
     * @return true when the board is solved
     */
    boolean isSolved();

    /**
     * Returns a copy of the board.
     *
     * @return copied board
     */
    int[][] getBoard();

    /**
     * Returns fixed cell markers.
     *
     * @return fixed cell markers
     */
    boolean[][] getFixedCells();

    /**
     * Finds a hint for the current board.
     *
     * @return hint data
     */
    int[] getHint();

    /**
     * Counts one used hint.
     */
    void registerHintUsed();

    /**
     * Returns the number of used hints.
     *
     * @return used hint count
     */
    int getHintsUsed();
}
