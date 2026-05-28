package com.example.poesudoku.model;

/**
 * Defines Sudoku solution and fixed cell generation.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public interface SudokuGeneratorInterface {
    /**
     * Generates a solved Sudoku board.
     */
    void generate();

    /**
     * Marks fixed cells in the generated board.
     */
    void placeFixedNumbers();

    /**
     * Returns the generated board.
     *
     * @return generated board
     */
    int[][] getGeneratedBoard();

    /**
     * Returns fixed cell markers.
     *
     * @return fixed cell markers
     */
    boolean[][] getFixedCells();
}
