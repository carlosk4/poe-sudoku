package com.example.poesudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.poesudoku.model.SudokuConstants.BLOCK_COLUMNS;
import static com.example.poesudoku.model.SudokuConstants.BLOCK_ROWS;
import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;
import static com.example.poesudoku.model.SudokuConstants.SIZE;

public class SudokuGenerator implements SudokuGeneratorInterface {

    private static final int FIXED_CELLS_PER_BLOCK = 2;

    private final int[][] generatedBoard = new int[SIZE][SIZE];
    private final boolean[][] fixedCells = new boolean[SIZE][SIZE];

    @Override
    public void generate() {
        clearBoard();
        solve(0, 0);
    }

    @Override
    public void placeFixedNumbers() {
        for (int blockRow = 0; blockRow < SIZE / BLOCK_ROWS; blockRow++) {
            for (int blockCol = 0; blockCol < SIZE / BLOCK_COLUMNS; blockCol++) {
                placeFixedInBlock(blockRow * BLOCK_ROWS, blockCol * BLOCK_COLUMNS);
            }
        }
    }

    @Override
    public int[][] getGeneratedBoard() {
        return copyBoard(generatedBoard);
    }

    @Override
    public boolean[][] getFixedCells() {
        return copyFixedCells();
    }

    private void clearBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                generatedBoard[row][col] = EMPTY_CELL;
                fixedCells[row][col] = false;
            }
        }
    }

    private boolean solve(int row, int col) {
        if (row == SIZE) {
            return true;
        }

        int nextRow = col == SIZE - 1 ? row + 1 : row;
        int nextCol = col == SIZE - 1 ? 0 : col + 1;

        for (int num : getShuffledNumbers()) {
            if (isValidPlacement(row, col, num)) {
                generatedBoard[row][col] = num;

                if (solve(nextRow, nextCol)) {
                    return true;
                }

                generatedBoard[row][col] = EMPTY_CELL;
            }
        }

        return false;
    }

    private boolean isValidPlacement(int row, int col, int value) {
        for (int i = 0; i < SIZE; i++) {
            if (generatedBoard[row][i] == value) {
                return false;
            }

            if (generatedBoard[i][col] == value) {
                return false;
            }
        }

        int blockRowStart = (row / BLOCK_ROWS) * BLOCK_ROWS;
        int blockColStart = (col / BLOCK_COLUMNS) * BLOCK_COLUMNS;

        for (int r = blockRowStart; r < blockRowStart + BLOCK_ROWS; r++) {
            for (int c = blockColStart; c < blockColStart + BLOCK_COLUMNS; c++) {
                if (generatedBoard[r][c] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    private void placeFixedInBlock(int startRow, int startCol) {
        List<int[]> cells = new ArrayList<>();

        for (int row = startRow; row < startRow + BLOCK_ROWS; row++) {
            for (int col = startCol; col < startCol + BLOCK_COLUMNS; col++) {
                cells.add(new int[]{row, col});
            }
        }

        Collections.shuffle(cells);

        for (int i = 0; i < FIXED_CELLS_PER_BLOCK; i++) {
            int row = cells.get(i)[0];
            int col = cells.get(i)[1];
            fixedCells[row][col] = true;
        }
    }

    private List<Integer> getShuffledNumbers() {
        List<Integer> numbers = new ArrayList<>(SIZE);

        for (int number = 1; number <= SIZE; number++) {
            numbers.add(number);
        }

        Collections.shuffle(numbers);
        return numbers;
    }

    private int[][] copyBoard(int[][] original) {
        int[][] copy = new int[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            System.arraycopy(original[row], 0, copy[row], 0, SIZE);
        }

        return copy;
    }

    private boolean[][] copyFixedCells() {
        boolean[][] copy = new boolean[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            System.arraycopy(fixedCells[row], 0, copy[row], 0, SIZE);
        }

        return copy;
    }
}
