package com.example.poesudoku.model;

public interface GameManagerInterface {
    void startNewGame();
    int[][] getBoard();
    boolean[][] getFixedCells();
}
