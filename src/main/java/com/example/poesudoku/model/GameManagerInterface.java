package com.example.poesudoku.model;

public interface GameManagerInterface {
    void startNewGame();
    void saveState();
    void undo();
    boolean canUndo();

    void redo(int branchIndex);
    boolean canRedo();
    int getAvailableBranchesCount();

    boolean setCellValue(int row, int col, int value);
    boolean isFixedCell(int row, int col);
    boolean isCellValid(int row, int col);
    boolean isSolved();

    int[][] getBoard();
    boolean[][] getFixedCells();
}
