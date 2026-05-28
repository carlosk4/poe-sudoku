package com.example.poesudoku.model;

import static com.example.poesudoku.model.SudokuConstants.SIZE;

public class GameTree {

    private GameStateNode current;

    public void init(int[][] initialBoard) {
        GameStateNode root;
        root = new GameStateNode(initialBoard);
        current = root;
    }

    public void push(int[][] boardState) {
        if (current == null) {
            init(boardState);
            return;
        }

        GameStateNode newNode = new GameStateNode(boardState);
        current.addChild(newNode);
        current = newNode;
    }

    public int[][] undo() {
        if (!canUndo()) {
            return getCurrentBoardState();
        }

        current = current.getParent();
        return current.getBoardState();
    }

    public boolean canUndo() {
        return current != null && current.getParent() != null;
    }

    public int[][] getCurrentBoardState() {
        if (current == null) {
            return new int[SIZE][SIZE];
        }

        return current.getBoardState();
    }
}