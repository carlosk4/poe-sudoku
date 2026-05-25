package com.example.poesudoku.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;
import static com.example.poesudoku.model.SudokuConstants.SIZE;

public class SudokuHintProvider {

    private final Random random = new Random();

    public int[] findHint(int[][] currentBoard, int[][] solutionBoard, boolean[][] fixedCells) {
        if (!isValidBoardData(currentBoard, solutionBoard, fixedCells)) {
            return new int[0];
        }

        List<int[]> candidates = findHintCandidates(currentBoard, fixedCells);

        if (candidates.isEmpty()) {
            return new int[0];
        }

        int[] selectedCell = candidates.get(random.nextInt(candidates.size()));
        int row = selectedCell[0];
        int col = selectedCell[1];

        return new int[]{row, col, solutionBoard[row][col]};
    }

    private List<int[]> findHintCandidates(int[][] currentBoard, boolean[][] fixedCells) {
        List<int[]> candidates = new ArrayList<>();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (isHintCandidate(currentBoard, fixedCells, row, col)) {
                    candidates.add(new int[]{row, col});
                }
            }
        }

        return candidates;
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
