package com.example.poesudoku.model;

public interface GameManagerInterface {
    void startNewGame();
    void saveState();
    void undo();
    boolean canUndo();
    int[][] getBoard();
    boolean[][] getFixedCells();
}
