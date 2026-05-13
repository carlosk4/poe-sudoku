package com.example.poesudoku.model;

public interface SudokuBoardInterface {
    int getCell(int row, int col);
    void setCell(int row, int col, int value);
    boolean isValid(int row, int col, int value);
    boolean isSolved();
    void reset();
    int[][] getBoard();
}
