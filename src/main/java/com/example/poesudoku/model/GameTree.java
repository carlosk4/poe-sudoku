package com.example.poesudoku.model;

import static com.example.poesudoku.model.SudokuConstants.SIZE;

/**
 * Stores board states for undo history.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public class GameTree {

    private GameStateNode current;

    /**
     * Initializes history with the first board state.
     *
     * @param initialBoard initial board
     */
    public void init(int[][] initialBoard) {
        GameStateNode root;
        root = new GameStateNode(initialBoard);
        current = root;
    }

    /**
     * Adds a board state after the current node.
     *
     * @param boardState board state
     */
    public void push(int[][] boardState) {
        if (current == null) {
            init(boardState);
            return;
        }

        GameStateNode newNode = new GameStateNode(boardState);
        current.addChild(newNode);
        current = newNode;
    }

    /**
     * Moves to the previous board state.
     *
     * @return previous board state
     */
    public int[][] undo() {
        if (!canUndo()) {
            return getCurrentBoardState();
        }

        current = current.getParent();
        return current.getBoardState();
    }

    /**
     * Checks whether undo is available.
     *
     * @return true when a previous state exists
     */
    public boolean canUndo() {
        return current != null && current.getParent() != null;
    }

    /**
     * Returns the current board state.
     *
     * @return current board state
     */
    public int[][] getCurrentBoardState() {
        if (current == null) {
            return new int[SIZE][SIZE];
        }

        return current.getBoardState();
    }
}
