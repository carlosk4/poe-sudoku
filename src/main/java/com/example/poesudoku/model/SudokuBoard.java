package com.example.poesudoku.model;

import static com.example.poesudoku.model.SudokuConstants.BLOCK_COLUMNS;
import static com.example.poesudoku.model.SudokuConstants.BLOCK_ROWS;
import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;
import static com.example.poesudoku.model.SudokuConstants.SIZE;

/**
 * Stores and validates a Sudoku board.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public class SudokuBoard implements SudokuBoardInterface {

    private final int[][] board = new int[SIZE][SIZE];

    /**
     * Returns the value at a board cell.
     *
     * @param row target row
     * @param col target column
     * @return cell value
     */
    @Override
    public int getCell(int row, int col) {
        return board[row][col];
    }

    /**
     * Sets the value at a board cell.
     *
     * @param row target row
     * @param col target column
     * @param value target value
     */
    @Override
    public void setCell(int row, int col, int value) {
        board[row][col] = value;
    }

    /**
     * Checks whether a value follows Sudoku rules at a cell.
     *
     * @param row target row
     * @param col target column
     * @param value target value
     * @return true when the value is valid
     */
    @Override
    public boolean isValid(int row, int col, int value) {
        if (value == EMPTY_CELL) {
            return true;
        }

        for (int i = 0; i < SIZE; i++) {
            if (i != col && board[row][i] == value) {
                return false;
            }

            if (i != row && board[i][col] == value) {
                return false;
            }
        }

        int blockRowStart = (row / BLOCK_ROWS) * BLOCK_ROWS;
        int blockColStart = (col / BLOCK_COLUMNS) * BLOCK_COLUMNS;

        for (int r = blockRowStart; r < blockRowStart + BLOCK_ROWS; r++) {
            for (int c = blockColStart; c < blockColStart + BLOCK_COLUMNS; c++) {
                if (!(r == row && c == col) && board[r][c] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks whether the board is solved.
     *
     * @return true when the board is solved
     */
    @Override
    public boolean isSolved() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    return false;
                }

                if (!isValid(row, col, board[row][col])) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Clears the board.
     */
    @Override
    public void reset() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = EMPTY_CELL;
            }
        }
    }

    /**
     * Returns a copy of the board.
     *
     * @return copied board
     */
    @Override
    public int[][] getBoard() {
        return copyBoard(board);
    }

    /**
     * Copies a board matrix.
     *
     * @param original source board
     * @return copied board
     */
    private int[][] copyBoard(int[][] original) {
        int[][] copy = new int[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            System.arraycopy(original[row], 0, copy[row], 0, SIZE);
        }

        return copy;
    }
}
