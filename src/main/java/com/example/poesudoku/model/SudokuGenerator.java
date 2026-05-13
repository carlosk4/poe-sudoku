package com.example.poesudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuGenerator implements SudokuGeneratorInterface {

    private final int[][] generatedBoard = new int[6][6];
    private final boolean[][] fixedCells = new boolean[6][6];

    @Override
    public void generate() {
        clearBoard();
        solve(0, 0);
    }

    @Override
    public void placeFixedNumbers() {
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 2; blockCol++) {
                placeFixedInBlock(blockRow * 2, blockCol * 3);
            }
        }
    }

    @Override
    public int[][] getGeneratedBoard() {
        return generatedBoard;
    }

    @Override
    public boolean[][] getFixedCells() {
        return fixedCells;
    }

    private void clearBoard() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                generatedBoard[row][col] = 0;
                fixedCells[row][col] = false;
            }
        }
    }

    private boolean solve(int row, int col) {
        if (row == 6) return true;

        int nextRow = (col == 5) ? row + 1 : row;
        int nextCol = (col == 5) ? 0 : col + 1;

        List<Integer> numbers = getShuffledNumbers();

        for (int num : numbers) {
            if (isValidPlacement(row, col, num)) {
                generatedBoard[row][col] = num;
                if (solve(nextRow, nextCol)) return true;
                generatedBoard[row][col] = 0;
            }
        }

        return false;
    }

    private boolean isValidPlacement(int row, int col, int value) {
        for (int i = 0; i < 6; i++) {
            if (generatedBoard[row][i] == value) return false;
            if (generatedBoard[i][col] == value) return false;
        }

        int blockRowStart = (row / 2) * 2;
        int blockColStart = (col / 3) * 3;

        for (int r = blockRowStart; r < blockRowStart + 2; r++) {
            for (int c = blockColStart; c < blockColStart + 3; c++) {
                if (generatedBoard[r][c] == value) return false;
            }
        }

        return true;
    }

    private void placeFixedInBlock(int startRow, int startCol) {
        List<int[]> cells = new ArrayList<>();
        for (int r = startRow; r < startRow + 2; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                cells.add(new int[]{r, c});
            }
        }
        Collections.shuffle(cells);

        for (int i = 0; i < 2; i++) {
            int row = cells.get(i)[0];
            int col = cells.get(i)[1];
            fixedCells[row][col] = true;
        }
    }

    private List<Integer> getShuffledNumbers() {
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.shuffle(numbers);
        return numbers;
    }
}
