package com.example.poesudoku.model;

public interface GameManagerInterface {
    void startNewGame();
    void saveState();
    void undo();
    boolean canUndo();

    void redo(int branchIndex);
    boolean canRedo();
    int getAvailableBranchesCount();

    int[][] getBoard();
    boolean[][] getFixedCells();
}
