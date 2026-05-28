package com.example.poesudoku.model;

import java.util.List;

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

    public int[][] redo(int childIndex) {
        if (!canRedo(childIndex)) {
            return getCurrentBoardState();
        }

        current = current.getChild(childIndex);
        return current.getBoardState();
    }

    public boolean canUndo() {
        return current != null && current.getParent() != null;
    }

    public boolean canRedo() {
        return current != null && current.hasChildren();
    }

    public boolean canRedo(int childIndex) {
        return current != null
                && childIndex >= 0
                && childIndex < current.getChildrenCount();
    }

    public List<GameStateNode> getCurrentBranches() {
        if (current == null) {
            return List.of();
        }

        return current.getChildren();
    }

    public int[][] getCurrentBoardState() {
        if (current == null) {
            return new int[SIZE][SIZE];
        }

        return current.getBoardState();
    }
}