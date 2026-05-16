package com.example.poesudoku.model;

public class GameTree {

    private GameStateNode root;
    private GameStateNode current;

    public void init(int[][] initialBoard) {
        root = new GameStateNode(initialBoard);
        current = root;
    }

    public void push(int[][] boardState) {
        GameStateNode newNode = new GameStateNode(boardState);
        current.addChild(newNode);
        current = newNode;
    }

    public int[][] undo() {
        if (current.getParent() == null) return current.getBoardState();
        current = current.getParent();
        return current.getBoardState();
    }

    public boolean canUndo() {
        return current.getParent() != null;
    }
}