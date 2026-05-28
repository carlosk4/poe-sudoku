package com.example.poesudoku.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.poesudoku.model.SudokuConstants.BLOCK_COLUMNS;
import static com.example.poesudoku.model.SudokuConstants.BLOCK_ROWS;
import static com.example.poesudoku.model.SudokuConstants.EMPTY_CELL;
import static com.example.poesudoku.model.SudokuConstants.SIZE;

/**
 * Finds useful hints and corrections for a Sudoku board.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public class SudokuHintProvider {

    private final Random random = new Random();

    /**
     * Finds a hint for the current board.
     *
     * @param currentBoard current board
     * @param solutionBoard solution board
     * @param fixedCells fixed cell markers
     * @return hint data
     */
    public int[] findHint(int[][] currentBoard, int[][] solutionBoard, boolean[][] fixedCells) {
        if (!isValidBoardData(currentBoard, solutionBoard, fixedCells)) {
            return new int[0];
        }

        List<int[]> correctionCandidates = findCorrectionCandidates(currentBoard, solutionBoard, fixedCells);

        if (!isConsistent(currentBoard)) {
            return selectHint(correctionCandidates, solutionBoard);
        }

        int[][] solvedBoard = copyBoard(currentBoard);

        if (!solve(solvedBoard)) {
            return selectHint(correctionCandidates, solutionBoard);
        }

        List<int[]> candidates = findHintCandidates(currentBoard, fixedCells);

        if (candidates.isEmpty()) {
            return new int[0];
        }

        List<int[]> bestCandidates = findMostUsefulCandidates(currentBoard, candidates);
        int[] selectedCell = bestCandidates.get(random.nextInt(bestCandidates.size()));
        int row = selectedCell[0];
        int col = selectedCell[1];

        return new int[]{row, col, solvedBoard[row][col]};
    }

    /**
     * Finds empty editable cells that can receive hints.
     *
     * @param currentBoard current board
     * @param fixedCells fixed cell markers
     * @return hint candidates
     */
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

    /**
     * Checks whether a cell can receive a hint.
     *
     * @param currentBoard current board
     * @param fixedCells fixed cell markers
     * @param row target row
     * @param col target column
     * @return true when the cell can receive a hint
     */
    private boolean isHintCandidate(int[][] currentBoard, boolean[][] fixedCells, int row, int col) {
        return currentBoard[row][col] == EMPTY_CELL && !fixedCells[row][col];
    }

    /**
     * Finds editable cells that differ from the solution.
     *
     * @param currentBoard current board
     * @param solutionBoard solution board
     * @param fixedCells fixed cell markers
     * @return correction candidates
     */
    private List<int[]> findCorrectionCandidates(int[][] currentBoard, int[][] solutionBoard, boolean[][] fixedCells) {
        List<int[]> candidates = new ArrayList<>();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (!fixedCells[row][col]
                        && currentBoard[row][col] != EMPTY_CELL
                        && currentBoard[row][col] != solutionBoard[row][col]) {
                    candidates.add(new int[]{row, col});
                }
            }
        }

        return candidates;
    }

    /**
     * Selects one hint from candidates.
     *
     * @param candidates candidate cells
     * @param solvedBoard solved board
     * @return hint data
     */
    private int[] selectHint(List<int[]> candidates, int[][] solvedBoard) {
        if (candidates.isEmpty()) {
            return new int[0];
        }

        int[] selectedCell = candidates.get(random.nextInt(candidates.size()));
        int row = selectedCell[0];
        int col = selectedCell[1];

        return new int[]{row, col, solvedBoard[row][col]};
    }

    /**
     * Finds candidates with the fewest legal options.
     *
     * @param currentBoard current board
     * @param candidates candidate cells
     * @return most useful candidates
     */
    private List<int[]> findMostUsefulCandidates(int[][] currentBoard, List<int[]> candidates) {
        List<int[]> bestCandidates = new ArrayList<>();
        int bestOptionsCount = SIZE + 1;

        for (int[] candidate : candidates) {
            int row = candidate[0];
            int col = candidate[1];
            int optionsCount = countLegalOptions(currentBoard, row, col);

            if (optionsCount < bestOptionsCount) {
                bestCandidates.clear();
                bestOptionsCount = optionsCount;
            }

            if (optionsCount == bestOptionsCount) {
                bestCandidates.add(candidate);
            }
        }

        return bestCandidates;
    }

    /**
     * Counts legal values for a cell.
     *
     * @param board target board
     * @param row target row
     * @param col target column
     * @return legal option count
     */
    private int countLegalOptions(int[][] board, int row, int col) {
        int optionsCount = 0;

        for (int value = 1; value <= SIZE; value++) {
            if (isValueLegal(board, row, col, value)) {
                optionsCount++;
            }
        }

        return optionsCount;
    }

    /**
     * Solves a board copy.
     *
     * @param board target board
     * @return true when a solution is found
     */
    private boolean solve(int[][] board) {
        int[] emptyCell = findEmptyCellWithFewestOptions(board);

        if (emptyCell.length == 0) {
            return true;
        }

        int row = emptyCell[0];
        int col = emptyCell[1];

        for (int value = 1; value <= SIZE; value++) {
            if (isValueLegal(board, row, col, value)) {
                board[row][col] = value;

                if (solve(board)) {
                    return true;
                }

                board[row][col] = EMPTY_CELL;
            }
        }

        return false;
    }

    /**
     * Finds an empty cell with the fewest legal options.
     *
     * @param board target board
     * @return cell coordinates
     */
    private int[] findEmptyCellWithFewestOptions(int[][] board) {
        int[] bestCell = new int[0];
        int bestOptionsCount = SIZE + 1;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    int optionsCount = countLegalOptions(board, row, col);

                    if (optionsCount == 0) {
                        return new int[]{row, col};
                    }

                    if (optionsCount < bestOptionsCount) {
                        bestCell = new int[]{row, col};
                        bestOptionsCount = optionsCount;
                    }
                }
            }
        }

        return bestCell;
    }

    /**
     * Checks whether all filled cells are consistent.
     *
     * @param board target board
     * @return true when filled cells are legal
     */
    private boolean isConsistent(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int value = board[row][col];

                if (value != EMPTY_CELL && !isValueLegal(board, row, col, value)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks whether a value is legal at a cell.
     *
     * @param board target board
     * @param row target row
     * @param col target column
     * @param value target value
     * @return true when the value is legal
     */
    private boolean isValueLegal(int[][] board, int row, int col, int value) {
        if (value < 1 || value > SIZE) {
            return false;
        }

        for (int i = 0; i < SIZE; i++) {
            if (i != col && board[row][i] == value) {
                return false;
            }

            if (i != row && board[i][col] == value) {
                return false;
            }
        }

        int blockRowStart = (row / BLOCK_ROWS) * BLOCK_ROWS;
        int blockColStart = (col / BLOCK_COLUMNS) * BLOCK_COLUMNS;

        for (int r = blockRowStart; r < blockRowStart + BLOCK_ROWS; r++) {
            for (int c = blockColStart; c < blockColStart + BLOCK_COLUMNS; c++) {
                if (!(r == row && c == col) && board[r][c] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Copies a board matrix.
     *
     * @param board source board
     * @return copied board
     */
    private int[][] copyBoard(int[][] board) {
        int[][] copy = new int[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            System.arraycopy(board[row], 0, copy[row], 0, SIZE);
        }

        return copy;
    }

    /**
     * Checks whether all board inputs have valid shapes.
     *
     * @param currentBoard current board
     * @param solutionBoard solution board
     * @param fixedCells fixed cell markers
     * @return true when all inputs are valid
     */
    private boolean isValidBoardData(int[][] currentBoard, int[][] solutionBoard, boolean[][] fixedCells) {
        return isValidBoard(currentBoard)
                && isValidBoard(solutionBoard)
                && isValidFixedCells(fixedCells);
    }

    /**
     * Checks whether a board has valid shape and values.
     *
     * @param board target board
     * @return true when the board data is valid
     */
    private boolean isValidBoard(int[][] board) {
        if (board == null || board.length != SIZE) {
            return false;
        }

        for (int[] row : board) {
            if (row == null || row.length != SIZE) {
                return false;
            }

            for (int value : row) {
                if (value < EMPTY_CELL || value > SIZE) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks whether fixed cell data has a valid shape.
     *
     * @param fixedCells fixed cell markers
     * @return true when fixed cell data is valid
     */
    private boolean isValidFixedCells(boolean[][] fixedCells) {
        if (fixedCells == null || fixedCells.length != SIZE) {
            return false;
        }

        for (boolean[] row : fixedCells) {
            if (row == null || row.length != SIZE) {
                return false;
            }
        }

        return true;
    }
}
