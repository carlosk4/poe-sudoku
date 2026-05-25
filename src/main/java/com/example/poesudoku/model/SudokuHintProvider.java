package com.example.poesudoku.model;

import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;
import static com.example.poesudoku.model.SudokuConstants.SIZE;

public class SudokuHintProvider {


    public int[] findHint(int[][] currentBoard, int[][] solutionBoard, boolean[][] fixedCells) {
        if (!isValidBoardData(currentBoard, solutionBoard, fixedCells)) {
            return new int[0];
        }

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (isHintCandidate(currentBoard, fixedCells, row, col)) {
                    return new int[]{row, col, solutionBoard[row][col]};
                }
            }
        }

        return new int[0];
    }

    private boolean isHintCandidate(int[][] currentBoard, boolean[][] fixedCells, int row, int col) {
        return currentBoard[row][col] == EMPTY_CELL && !fixedCells[row][col];
    }

    private boolean isValidBoardData(int[][] currentBoard, int[][] solutionBoard, boolean[][] fixedCells) {
        return currentBoard != null
                && solutionBoard != null
                && fixedCells != null
                && currentBoard.length == SIZE
                && solutionBoard.length == SIZE
                && fixedCells.length == SIZE;
    }
}
