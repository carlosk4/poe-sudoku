package com.example.poesudoku.model;

public class SudokuBoard implements SudokuBoardInterface {

    private final int[][] board = new int[6][6];

    @Override
    public int getCell(int row, int col) {
        return board[row][col];
    }

    @Override
    public void setCell(int row, int col, int value) {
        board[row][col] = value;
    }

    @Override
    public boolean isValid(int row, int col, int value) {
        for (int i = 0; i < 6; i++) {
            if (i != col && board[row][i] == value) return false;
            if (i != row && board[i][col] == value) return false;
        }

        int blockRowStart = (row / 2) * 2;
        int blockColStart = (col / 3) * 3;

        for (int r = blockRowStart; r < blockRowStart + 2; r++) {
            for (int c = blockColStart; c < blockColStart + 3; c++) {
                if (r != row && c != col && board[r][c] == value) return false;
            }
        }

        return true;
    }

    @Override
    public boolean isSolved() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (board[row][col] == 0) return false;
                if (!isValid(row, col, board[row][col])) return false;
            }
        }
        return true;
    }

    @Override
    public void reset() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                board[row][col] = 0;
            }
        }
    }

    @Override
    public int[][] getBoard() {
        return board;
    }
}
